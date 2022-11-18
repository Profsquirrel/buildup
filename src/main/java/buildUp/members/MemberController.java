package buildUp.members;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberService memberService;
	MemberVO memberVO;
	public void init(ServletConfig config) throws ServletException {
		memberService = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession(false);
		String toGo="";
		String path=request.getPathInfo();
		//메인
		if(path==null||path.equals("/main")) {
			if(session.getAttribute("s_id")==null) {
				toGo="/index.jsp";
			}else if(session.getAttribute("s_teamCode")==null){
				request.setAttribute("id", (String)session.getAttribute("s_id"));
				toGo="/jsp_member/signin_complete.jsp";
			}else {
				request.setAttribute("job", (String)session.getAttribute("s_job"));
				toGo="/board/main";
			}
		//회원가입 페이지로 이동
		}else if(path.equals("/signin")) {
				toGo="/jsp_member/signin.jsp";
		//회원가입
		}else if(path.equals("/addMember.do")) {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			MemberVO member=new MemberVO();
			member.setId(id);
			member.setPw(pw);
			member.setName(name);
			member.setEmail(email);
			int result=memberService.addMember(member);
			if(result==0) {
				session.setAttribute("s_id",id);
				request.setAttribute("id", id);
				toGo="/jsp_member/signin_complete.jsp";
			}else {
				PrintWriter out=response.getWriter();
				out.println("<html>");
				out.println("<body>");
				out.println("<script>");
				out.println("alert('회원 등록 중 오류가 발생하였습니다.');");
				out.println("parent.location.href='"+request.getContextPath()+"/jsp_member/signin.jsp';");
				out.println("</script>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			}
		//로그인했을때
		}else if(path.equals("/login.do")){
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			memberVO=memberService.login(id, pw);
			session=request.getSession();
			if(memberVO!=null) {
				session.setAttribute("s_id",memberVO.getId());
				session.setAttribute("s_name", memberVO.getName());
				session.setAttribute("s_job", memberVO.getJob());
				if(memberVO.getTeamName()!=null) {
					session.setAttribute("s_teamName", memberVO.getTeamName());
					session.setAttribute("s_teamCode", memberVO.getTeamCode());
					request.setAttribute("job", (String)session.getAttribute("s_job"));
					toGo="/board/main";
				}else {
					request.setAttribute("id", memberVO.getId());
					toGo="/jsp_member/signin_complete.jsp";
				}
			}else {
				PrintWriter out=response.getWriter();
				out.print("<html><body><script>alert('로그인 정보를 확인할 수 없습니다!');parent.location.href='"+request.getContextPath()+"/index.jsp';</script></body></html>");
				out.close();
			}
		}else if(path.equals("/modMemberForm")){
			MemberVO member=new MemberVO();
			if(request.getParameter("id")!=null && session.getAttribute("s_job").equals("leader")) {
				member=memberService.selectMember(request.getParameter("id"));
				request.setAttribute("member", member);
				toGo="/jsp_member/modMember.jsp";
			}else {
				String id=(String)session.getAttribute("s_id");
				member=memberService.selectMember(id);
				request.setAttribute("member", member);
				toGo="/jsp_member/modMember.jsp";
			}
		}else if(path.equals("/modMember.do")) {
			MemberVO member=new MemberVO();
			if(session.getAttribute("s_job").equals("leader")) {
				
			}else {
			member.setId((String)session.getAttribute("s_id"));
			member.setPw(request.getParameter("pw"));
			member.setName(request.getParameter("name"));
			member.setEmail(request.getParameter("email"));
			memberService.modMember(member);
			session.setAttribute("s_name", member.getName());
			request.setAttribute("job", (String)session.getAttribute("s_job"));
			toGo="/board/main";
			}
		}else if(path.equals("/logout.do")){
			if(session.getAttribute("s_id")!=null) {
				session.invalidate();
				toGo="/index.jsp";
			}else {
				toGo="/index.jsp";
			}
		}else {
			toGo="/index.jsp";
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(toGo);
		dispatcher.forward(request, response);
	}
}
