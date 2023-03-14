package com.profsquirrel.buildup.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.profsquirrel.buildup.member.service.MemberService;
import com.profsquirrel.buildup.member.vo.MemberVO;

@Controller
public class MemberController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String showLogin() throws Exception{
		logger.info("MemberController, showLogin");
		
		return "/index";
	}
		
	@PostMapping("/member/login.do")
	public String userLogin(
			@RequestParam(name="id") String id,
			@RequestParam(name="pw") String pw
			,HttpServletRequest req) {

		String path="";
		
		MemberVO memberVO = memberService.userLogin(id);
		
		if(memberVO == null) {
			req.setAttribute("msg", "아이디 혹은 비밀번호가 잘못되었습니다.");
			req.setAttribute("url", "/");
			return "/jsp_page/redirect";
		}

		logger.info("MemberController, userLogin.");
		
		if(!passwordEncoder.matches(pw, memberVO.getPw())) {
			req.setAttribute("msg", "아이디 혹은 비밀번호가 잘못되었습니다.");
			req.setAttribute("url", "/");
			return "/jsp_page/redirect";
		}else {
			memberVO = memberService.getUserInfo(memberVO);

			session.setAttribute("s_id", memberVO.getId());
			session.setAttribute("s_name", memberVO.getName());

			if(memberVO.getTeamCode()==0) {

				path="/jsp_member/signin_complete";

			}else {
				session.setAttribute("s_teamCode", memberVO.getTeamCode());
				session.setAttribute("s_teamName", memberVO.getTeamName());
				session.setAttribute("s_job", memberVO.getJob());
				path = "redirect:/main";
			}
		}
		return path;
	}

	@GetMapping("/member/signin")
	public String signIn() {
		return "/jsp_member/signin";
	}

	@PostMapping("/member/addMember.do")
	public String addMember(MemberVO memberVO, HttpServletRequest req, HttpServletResponse resp) {
		logger.info("MemberController, addMember.");
		
		String encodedPassword = passwordEncoder.encode(memberVO.getPw());
		memberVO.setPw(encodedPassword);
		
		String id = memberVO.getId();
		
		int id_chk = memberService.chkID(id);
		
		if(id_chk != 0) {
			req.setAttribute("msg", "중복되는 아이디가 있습니다.");
			req.setAttribute("url", "/member/signin");
			return "/jsp_page/redirect";
		}
		
		int result=memberService.addMember(memberVO);

		logger.info("가입실행결과 : {}",result);

		session.setAttribute("s_id", memberVO.getId());
		session.setAttribute("s_name", memberVO.getName());

		return "/jsp_member/signin_complete";
	}

	@GetMapping("/member/teamMemberForm")
	private String showTeamMemberForm(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		logger.info("MemberController, showTeamMemberForm");

		int teamCode=session.getAttribute("s_teamCode")==null ? 0 : Integer.parseInt(String.valueOf(session.getAttribute("s_teamCode")));

		List<MemberVO> result = memberService.getTeamMember(teamCode);

		req.setAttribute("member", result);

		return "/jsp_team/teamMember";
	}

	@GetMapping("/member/logout.do")
	public String logout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@GetMapping("/member/modMemberForm")
	public String showModMemberForm(HttpServletRequest req,HttpServletResponse resp,String id) throws Exception{
		logger.info("MemberController, showModMemberForm.");

		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO = memberService.getUserInfo(memberVO);
		logger.info("memberVO : {}" , memberVO.toString());
		
		req.setAttribute("member", memberVO);
		return "/jsp_member/modMember";
	}

	@PostMapping("/member/modMember.do")
	public String modMember(HttpServletRequest req,HttpServletResponse resp, MemberVO memberVO) throws Exception{
		logger.info("MemberController, modMember.");
		
		String encodedPassword=passwordEncoder.encode(memberVO.getPw());
		
		memberVO.setPw(encodedPassword);
		
		int result = memberService.modMember(memberVO);
		if(result==0) {
			req.setAttribute("msg", "회원정보 수정에 실패하였습니다.");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}
		return "redirect:/main";
	}

	@GetMapping("/member/quitMember")
	public String showQuitMemberForm(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		logger.info("MemberController, showQuitMemberForm");

		if(session==null) {
			req.setAttribute("msg", "로그인이 필요합니다.");
			req.setAttribute("url", "/");
			return "/jsp_page/redirect";
		}

		return "/jsp_member/quitMember";
	}

	@PostMapping("/member/quitMember.do")
	public String quitMember(HttpServletRequest req,HttpServletResponse resp,MemberVO memberVO) throws Exception{
		logger.info("MemberController, quitMember");

		if(session==null) {
			req.setAttribute("msg", "로그인이 필요합니다.");
			req.setAttribute("url", "/");
			return "/jsp_page/redirect";
		}

		memberVO.setId(session.getAttribute("s_teamCode")==null?null:(String)session.getAttribute("s_id"));

		int result=memberService.quitMember(memberVO);

		if(result==0) {
			req.setAttribute("msg", "아이디 혹은 비밀번호를 확인해주세요");
			req.setAttribute("url", "/main");
		}else {
			req.setAttribute("msg", "이용해주셔서 감사합니다.");
			req.setAttribute("url", "/");
		}
		session.invalidate();
		return "/jsp_page/redirect";
	}
}
