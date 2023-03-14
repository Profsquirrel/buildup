package com.profsquirrel.buildup.team.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.profsquirrel.buildup.team.service.TeamService;
import com.profsquirrel.buildup.team.vo.TeamVO;


@Controller
public class TeamController {

	private static Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Autowired
	private TeamService teamService;

	@Autowired
	private HttpSession session;

	@GetMapping("/team/addTeam")
	private String showAddTeamForm(HttpServletRequest req) throws Exception{
		return "/jsp_member/signin_complete";
	}

	@PostMapping("/team/addTeam.do")
	private String addTeam(HttpServletRequest req,TeamVO teamVO) throws Exception{

		String id = session.getAttribute("s_id")==null?null:(String)session.getAttribute("s_id");
		logger.info("id:{}",id);
		int isExistLeader=teamService.isExistLeader(id);
			if(isExistLeader==0) {
				int teamCode=teamCodeGenerator();
				teamVO.setTeamLeader(id);
				teamVO.setTeamCode(teamCode);
				teamVO.setMembercnt(1);
				int result = teamService.addTeam(teamVO);
				logger.info("result : {} ", teamVO.toString());
				if(result!=1) {
					req.setAttribute("msg", "잘못된 접근입니다.");
					req.setAttribute("url", "/team/addTeam");
					return "/jsp_page/redirect";
				}
				Map<String,Object> joinMemberInfo = new HashMap<>();
				joinMemberInfo.put("id",id);
				joinMemberInfo.put("teamCode",teamVO.getTeamCode());
				joinMemberInfo.put("teamName", teamVO.getTeamName());
				joinMemberInfo.put("job", "leader");
				teamService.insertMember(joinMemberInfo);

				session.setAttribute("s_teamCode", teamVO.getTeamCode());
				session.setAttribute("s_teamName", teamVO.getTeamName());
				teamVO=teamService.selectTeam(teamVO.getTeamCode());

				if(teamVO.getTeamLeader().equals(id)) {
					session.setAttribute("s_job", "leader");
				}else {
					req.setAttribute("msg", "잘못된 접근입니다.");
					req.setAttribute("url", "/team/addTeam");
					return "/jsp_page/redirect";
				}

				logger.info("TeamController, addTeam--result : {}",teamVO.toString());

			return "redirect:/main";
		}
		return "redirect:/main";
	}

	@PostMapping("/team/insertMember.do")
	private String insertMember(HttpServletRequest req,int teamCode) throws Exception{
		int result=teamCodeGenerator();
		if(result==0) {
			req.setAttribute("msg", "해당 팀이 존재하지 않습니다.");
			req.setAttribute("url", "/team/addTeam");
			return "/jsp_page/redirect";
		}
		TeamVO teamVO = new TeamVO();
		teamVO=teamService.selectTeam(teamCode);
		Map<String, Object> joinMemberInfo = new HashMap<>();
		joinMemberInfo.put("id", session.getAttribute("s_id"));
		joinMemberInfo.put("teamCode",teamVO.getTeamCode());
		joinMemberInfo.put("teamName", teamVO.getTeamName());
		joinMemberInfo.put("job", "member");
		teamService.insertMember(joinMemberInfo);
		teamService.increaseMemberCnt(teamCode);
		session.setAttribute("s_teamCode", joinMemberInfo.get("teamCode"));
		session.setAttribute("s_teamName", joinMemberInfo.get("teamName"));
		session.setAttribute("s_job",joinMemberInfo.get("job"));
		return "redirect:/main";
	}

	@GetMapping("/team/switchLeader.do")
	private String switchLeader(HttpServletRequest req,String id, int teamCode) throws Exception{
		logger.info("TeamController, switchLeader");

        session = req.getSession(false);

        if (session == null || session.getAttribute("s_teamCode") == null) {
            return "redirect:/";  // 로그인 페이지로 리다이렉트
        }

		int s_teamCode=session.getAttribute("s_teamCode")==null?0:Integer.parseInt(session.getAttribute("s_teamCode").toString());
		String s_job=session.getAttribute("s_teamCode")==null?null:(String)session.getAttribute("s_job");

		if(!s_job.equals("leader") || s_teamCode != teamCode) {
			req.setAttribute("msg", "권한이 없습니다.");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}

		Map<String, Object> swLeader = new HashMap<>();
		swLeader.put("id",id);
		swLeader.put("teamCode", session.getAttribute("s_teamCode"));

		teamService.switchLeader(swLeader);
		teamService.switchLeaderOnTeam(swLeader);

		session.setAttribute("s_job", "member");
		req.setAttribute("msg", "리더가 변경되었습니다.");
		req.setAttribute("url", "/main");
		return "/jsp_page/redirect";
	}

	@GetMapping("/team/teamInfo")
	private String showTeamInfo(HttpServletRequest req) throws Exception{
		logger.info("TeamController, showTeamInfo");

		TeamVO teamVO = new TeamVO();
		int teamCode=session.getAttribute("s_teamCode")==null?0:Integer.parseInt(session.getAttribute("s_teamCode").toString());

		teamVO=teamService.selectTeam(teamCode);

		req.setAttribute("team", teamVO);

		return "/jsp_team/teamInfo";
	}

	@GetMapping("/team/quitTeam.do")
	private String quitTeam(HttpServletRequest req,String id) {
		logger.info("TeamController, quitTeam");
		
		int teamCode=session.getAttribute("s_teamCode")==null?0:Integer.parseInt(session.getAttribute("s_teamCode").toString());
		String s_job=session.getAttribute("s_teamCode")==null?null:(String)session.getAttribute("s_job");
		String s_id=session.getAttribute("s_teamCode")==null?null:(String)session.getAttribute("s_id");

		if((!s_job.equals("leader"))&&(!s_id.equals(id))) {
			req.setAttribute("msg", "잘못된 접근입니다!");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}else if(s_job.equals("leader")&&s_id.equals(id)) {
			req.setAttribute("msg", "팀 리더는 탈퇴할 수 없습니다!");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}

		Map<String, Object> quitTeamMap = new HashMap<>();
		quitTeamMap.put("id", id);
		quitTeamMap.put("teamCode",teamCode);

		teamService.quitTeam(quitTeamMap);
		teamService.decreaseMemberCnt(quitTeamMap);

		session.removeAttribute("s_teamCode");
		session.removeAttribute("s_teamName");
		session.removeAttribute("s_job");

		req.setAttribute("msg", "탈퇴처리 되었습니다.");
		req.setAttribute("url", "/team/addTeam");
		return "/jsp_page/redirect";
	}

	@GetMapping("/team/modTeam")
	public String showModTeamForm(HttpServletRequest req) throws Exception{
		logger.info("MemberController, showModTeamForm.");
		
		int teamCode=session.getAttribute("s_teamCode")==null?0:Integer.parseInt(session.getAttribute("s_teamCode").toString());

		TeamVO teamVO = new TeamVO();
        String id = session.getAttribute("s_teamCode")==null?null:(String)session.getAttribute("s_id");

		teamVO=teamService.selectTeam(teamCode);

		if(!id.equals(teamVO.getTeamLeader())) {
			req.setAttribute("msg", "권한이 없습니다");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}

		req.setAttribute("team", teamVO);

		return "/jsp_team/modTeam";
	}

	@PostMapping("/team/modTeam.do")
	public String modTeam(HttpServletRequest req, TeamVO teamVO) throws Exception{
		logger.info("MemberController, modTeam.");
		
		int result=teamService.modTeam(teamVO);

		if(result==0) {
			req.setAttribute("msg", "팀 정보 수정에 실패하였습니다");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}

		req.setAttribute("msg", "팀 정보를 수정했습니다.");
		req.setAttribute("url", "/main");
		return "/jsp_page/redirect";
	}

	@PostMapping("/team/disband.do")
	public String disbandTeam(HttpServletRequest req , TeamVO teamVO) throws Exception{
		logger.info("TeamController, disband.");
		
        int s_teamCode=session.getAttribute("s_teamCode")==null?0:Integer.parseInt(session.getAttribute("s_teamCode").toString());
        String s_job=session.getAttribute("s_teamCode")==null?null:(String)session.getAttribute("s_job");
        String s_id=session.getAttribute("s_teamCode")==null?null:(String)session.getAttribute("s_id");

        teamVO = teamService.selectTeam(s_teamCode);

        if(!s_job.equals("leader") || s_teamCode != teamVO.getTeamCode() || !s_id.equals(teamVO.getTeamLeader())) {
        	req.setAttribute("msg", "잘못된 접근입니다!");
        	req.setAttribute("url", "/main");
        	return "/jsp_page/redirect";
        }

        if(teamVO.getMemberCnt()>1) {
        	req.setAttribute("msg", "팀원이 있는 경우엔 탈퇴가 불가능합니다.");
        	req.setAttribute("url", "/main");
        	return "/jsp_page/redirect";
        }
        Map<String,Object> quitTeamMap = new HashMap<>();
        quitTeamMap.put("id", s_id);
        quitTeamMap.put("teamCode", teamVO.getTeamCode());
        teamService.quitTeam(quitTeamMap);

        int result = teamService.disband(teamVO);

        if(result != 1) {
        	req.setAttribute("msg", "팀 삭제에 실패했습니다.");
        	req.setAttribute("url", "/main");
        	return "/jsp_page/redirect";
        }

        session.removeAttribute("s_job");
        session.removeAttribute("s_teamCode");
        session.removeAttribute("s_teamName");

		logger.info("teamVO : {}" , teamVO.toString());
		req.setAttribute("msg", "팀이 해체되었습니다.");
    	req.setAttribute("url", "/");
		return "/jsp_page/redirect";
	}

//	팀 코드 생성 메서드
	private int teamCodeGenerator() throws Exception{
		int result = teamService.teamCodeGenerator();
		if(result<100000) {
			result = 100000;
		}
		return result;
	}
}
