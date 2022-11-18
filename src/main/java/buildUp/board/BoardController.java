package buildUp.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardService boardService;
	BoardVO boardVO;
	HttpSession session;

	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		boardVO = new BoardVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		session = request.getSession(false);
		String toGo = "";
		String path = request.getPathInfo();
		if(session.getAttribute("s_id")==null) {
			toGo="/index.jsp";
		}else if(session.getAttribute("s_teamCode")==null){
			request.setAttribute("id", (String)session.getAttribute("s_id"));
			toGo="/jsp_member/signin_complete.jsp";
		}else {
		if (path == null || path.equals("/postList.do")) {
			String category = request.getParameter("category");
			if (category == null || category == "") {
				category = "notice";
			}
			List<BoardVO> postList = new ArrayList<BoardVO>();
			int teamCode=(int)session.getAttribute("s_teamCode");
			postList = boardService.postList(teamCode, category);
			request.setAttribute("postList", postList);
			toGo = "/jsp_page/board.jsp";
		} else if(path.equals("/main")){
			List<BoardVO> allPostList=new ArrayList<BoardVO>();
			List<BoardVO> noticeList=new ArrayList<BoardVO>();
			List<BoardVO> assignmentList=new ArrayList<BoardVO>();
			List<BoardVO> suggestionList=new ArrayList<BoardVO>();
			List<BoardVO> freeList=new ArrayList<BoardVO>();
			int teamCode=(int)session.getAttribute("s_teamCode");
			allPostList=boardService.allPostList(teamCode);
			for(int i=0; i<allPostList.size(); i++) {
				if(allPostList.get(i).getCategory().equals("notice")) {
					noticeList.add(allPostList.get(i));
				}else if(allPostList.get(i).getCategory().equals("assignment")) {
					assignmentList.add(allPostList.get(i));
				}else if(allPostList.get(i).getCategory().equals("suggestion")) {
					suggestionList.add(allPostList.get(i));
				}else if(allPostList.get(i).getCategory().equals("free")) {
					freeList.add(allPostList.get(i));
				}
			}
			request.setAttribute("noticeList", noticeList);
			request.setAttribute("assignmentList", assignmentList);
			request.setAttribute("suggestionList", suggestionList);
			request.setAttribute("freeList", freeList);
			request.setAttribute("job", (String)session.getAttribute("s_job"));
			toGo="/jsp_page/main.jsp";
		}else if (path.equals("/writeForm")) {
			request.setAttribute("userName", (String)session.getAttribute("s_userName"));
			toGo="/jsp_page/writePost.jsp";
		} else if(path.equals("/writePost.do")) {
			String name=(String)session.getAttribute("s_name");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String category=request.getParameter("category");
			String fileName=request.getParameter("fileName");
			boardVO.setName(name);
			boardVO.setTitle(title);
			boardVO.setContent(content);
			boardVO.setCategory(category);
			if(fileName!="" || fileName!=null) {
				boardVO.setFileName(fileName);
			}
			int teamCode=(int)session.getAttribute("s_teamCode");
			boardVO.setTeamCode(teamCode);
			boardService.addPost(boardVO);
			toGo="/board/postList.do";
		} else if(path.equals("/viewPost.do")) {
			int postNo=Integer.parseInt(request.getParameter("postNo"));
			boardVO=boardService.viewPost(postNo);
			List<ReplyVO> reply=new ArrayList<ReplyVO>();
			reply=boardService.getReply(postNo);
			if(boardVO.getTeamCode()!=(int)session.getAttribute("s_teamCode")) {
				PrintWriter out=response.getWriter();
				out.print("<html><body><script>alert('잘못된 접근입니다!'); location.href='"+request.getContextPath()+"/board/main'</script></body></html>");
				out.close();
			}else{
			request.setAttribute("reply", reply);
			request.setAttribute("post", boardVO);
			request.setAttribute("name", (String)session.getAttribute("s_name"));
			toGo="/jsp_page/viewPost.jsp";
			}
		} else if(path.equals("/writeReply.do")) {
			int parentNo=Integer.parseInt(request.getParameter("postNo"));
			ReplyVO reply=new ReplyVO();
			reply.setParentNo(parentNo);
			reply.setName((String)session.getAttribute("s_name"));
			reply.setContent(request.getParameter("replyContent"));
			boolean result=boardService.writeReply(reply);
			if(!result) {
				PrintWriter out=response.getWriter();
				out.print("<html><body><script>alert('댓글 작성에 실패했습니다');location.href='"+request.getContextPath()+"/board/main'</script></body></html>");
				out.close();
			}else {
				toGo="/board/viewPost.do?postNo="+parentNo;
			}
		}else if(path.equals("/removeReply.do")){
			int replyNo=Integer.parseInt(request.getParameter("replyNo"));
			int postNo=Integer.parseInt(request.getParameter("postNo"));
			String name=(String)session.getAttribute("s_name");
			String reqName=request.getParameter("name");
			System.out.println((String)session.getAttribute("s_name")+" "+request.getParameter("name"));
			if(!name.equals(reqName)) {
				PrintWriter out=response.getWriter();
				out.print("<html><body><script>alert('댓글 작성자 외엔 삭제할 수 없습니다!');location.href='"+request.getContextPath()+"/board/viewPost.do?postNo="+postNo+"'</script></body></html>");
				out.close();
			}else {
			boardService.removeReply(replyNo);
			toGo="/board/viewPost.do?postNo="+postNo;
			}
		}else {
			toGo="/board/main";
		}
	}
		RequestDispatcher dispatcher = request.getRequestDispatcher(toGo);
		dispatcher.forward(request, response);
	}
}