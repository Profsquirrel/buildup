package buildUp.teams;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import buildUp.members.MemberVO;

@WebServlet("/team/*")
public class TeamController extends HttpServlet {
	TeamService teamService;
	TeamVO teamVO;
	HttpSession session;
	public void init(ServletConfig config) throws ServletException {
		teamService = new TeamService();
		teamVO = new TeamVO();
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
		session=request.getSession(false);
		String toGo="";
		String path=request.getPathInfo();
		if(session.getAttribute("s_id")==null) {
			toGo="/index.jsp";
		}else {
		if(path==null||path.equals("/main")) {
			toGo="/jsp_page/main.jsp";
		}else if(path.equals("/addTeamForm")) {
			toGo="/jsp_team/addTeam.jsp";
		}else if(path.equals("/addTeam.do")){
			String id=(String)session.getAttribute("s_id");
			String teamName=request.getParameter("teamName");
			String teamGoal=request.getParameter("teamGoal");
			teamVO.setTeamName(teamName);
			teamVO.setTeamGoal(teamGoal);
			teamVO.setTeamLeader(id);
			MemberVO memberVO=new MemberVO();
			memberVO.setTeamCode(teamService.addTeam(teamVO, id));
			request.setAttribute("member", memberVO);
			request.setAttribute("job", (String)session.getAttribute("s_job"));
			toGo="/board/main";
		}
		else if(path.equals("/insertMember.do")) {
			String id=(String)session.getAttribute("s_id");
			int teamCode=Integer.parseInt(request.getParameter("teamCode"));
			System.out.println(id+"  "+teamCode);
			String teamName=teamService.insertMember(id,teamCode);
			if(teamName=="" || teamName.isEmpty()) {
				PrintWriter out=response.getWriter();
				out.print("<html><body><script>alert('팀 등록 중 오류 발생');parent.location.href='"+request.getContextPath()+"/team/addTeamForm.do';</script></body></html>");
				out.close();
			}else{
				session.setAttribute("s_teamCode", teamCode);
				session.setAttribute("s_teamName", teamName);
			}
			toGo="/jsp_page/main.jsp";
		}else if(path.equals("/teamMemberForm")) {
			int teamCode=(int)session.getAttribute("s_teamCode");
			List<MemberVO> memberList=new ArrayList<MemberVO>();
			memberList=teamService.selectTeamMember(teamCode);
			request.setAttribute("member", memberList);
			request.setAttribute("job", (String)session.getAttribute("s_job"));
			toGo="/jsp_team/teamMember.jsp";
		}
		else if(path.equals("/modTeamForm")) {
			int teamCode=(int)session.getAttribute("s_teamCode");
			TeamVO team=new TeamVO();
			team=teamService.selectTeam(teamCode);
			request.setAttribute("team", team);
			toGo="/jsp_team/modTeam.jsp";
		}else if(path.equals("/modTeam.do")) {
			TeamVO team=new TeamVO();
			team.setTeamCode((int)session.getAttribute("s_teamCode"));
			team.setTeamName(request.getParameter("teamName"));
			team.setTeamGoal(request.getParameter("teamGoal"));
			teamService.modTeamInfo(team);
			toGo="/board/main";
		}else if(path.equals("/teamInfo")) {
			int teamCode=(int)session.getAttribute("s_teamCode");
			TeamVO team=new TeamVO();
			team=teamService.selectTeam(teamCode);
			request.setAttribute("team", team);
			toGo="/jsp_team/teamInfo.jsp";
		}else if(path.equals("/quitTeam.do")) {
			if(session.getAttribute("s_job").equals("leader")) {
				PrintWriter out=response.getWriter();
				out.print("<html><body><script>alert('팀장은 탈퇴할 수 없습니다!');parent.location.href='"+request.getContextPath()+"/board/main';</script></body></html>");
				out.close();
			}else {
				String id=(String)session.getAttribute("s_id");
				int teamCode=(int)session.getAttribute("s_teamCode");
				teamService.quitTeam(id,teamCode);
				session.removeAttribute("s_teamCode");
				session.removeAttribute("s_teamName");
				toGo="/board/main";
			}
		}
		else {
			toGo="/board/main";
		}
	}
		RequestDispatcher dispatcher=request.getRequestDispatcher(toGo);
		dispatcher.forward(request, response);
	}
}
