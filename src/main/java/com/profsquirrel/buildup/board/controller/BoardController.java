package com.profsquirrel.buildup.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.profsquirrel.buildup.board.service.BoardService;
import com.profsquirrel.buildup.board.vo.BoardVO;
import com.profsquirrel.buildup.board.vo.RecommendVO;
import com.profsquirrel.buildup.board.vo.ReplyVO;

@Controller
public class BoardController {

	private static Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private HttpSession session;
	@Autowired
	private BoardService boardService;
	/*
	 * 메인화면 출력
	*/


	@GetMapping("/main")
	public String showMain(HttpServletRequest req) throws Exception{

		logger.info("BoardController, showMain.");
		
		int teamCode = session.getAttribute("s_teamCode") == null ? 0 : Integer.parseInt(String.valueOf(session.getAttribute("s_teamCode")));
		
		List<BoardVO> allPostList=boardService.selectAllPostList(teamCode);

		List<BoardVO> noticeList=new ArrayList<>();
		List<BoardVO> assignmentList=new ArrayList<>();
		List<BoardVO> suggestionList=new ArrayList<>();
		List<BoardVO> freeList=new ArrayList<>();
		logger.info("allPostListSize:{} & s_teamCode:{}",allPostList,teamCode);
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
		req.setAttribute("noticeList", noticeList);
		req.setAttribute("assignmentList", assignmentList);
		req.setAttribute("suggestionList", suggestionList);
		req.setAttribute("freeList", freeList);
		return "/jsp_page/main";
	}

	@GetMapping("/board/postList.do")
	public String showBoard(
			HttpServletRequest req,
			@RequestParam String category,
			@RequestParam(name="page", required=false, defaultValue = "1") Integer currentPage,
			@RequestParam(name="listNum", required=false, defaultValue="10") Integer listNum,
			@RequestParam(name="searchType", required=false) String searchType,
			@RequestParam(name="keyword", required=false) String keyword
			) throws Exception{

		logger.info("BoardController, showBoard.");
		
		int startPost = (currentPage-1)*listNum+1;
		int endPost =  startPost+(listNum-1);
		int teamCode = session.getAttribute("s_teamCode") == null ? 0 : Integer.parseInt(String.valueOf(session.getAttribute("s_teamCode")));
		int lastPage=0;
		int numberOfPosts = 0;
		List<BoardVO> result = null;
		
		Map<String,Object> postPaging = new HashMap<String,Object>();
		
		postPaging.put("category", category);
		postPaging.put("startPost", startPost);
		postPaging.put("endPost", endPost);
		postPaging.put("teamCode", teamCode);
		
		if(searchType != null && keyword != null) {
			postPaging.put("keyword", keyword);
			postPaging.put("searchType", searchType);
			postPaging.put("keyword", keyword);
			postPaging.put("searchType", searchType);
			req.setAttribute("keyword", keyword);
			req.setAttribute("searchType", searchType);
			result = boardService.searchPost(postPaging);
			numberOfPosts=boardService.searchNumberOfPosts(postPaging);
		}else {
			numberOfPosts=boardService.returnNumberOfPosts(postPaging);
			result = boardService.selectCategoryList(postPaging);
		}
		
		if(numberOfPosts%10 != 0) {
			lastPage = (numberOfPosts/listNum)+1;
		}else {
			lastPage = numberOfPosts/listNum;
		}
		
		logger.info("result : {} ", result.toString());

		req.setAttribute("numberOfPosts", numberOfPosts);
		req.setAttribute("startPost", startPost);
		req.setAttribute("endPost", endPost);
		req.setAttribute("category", category);
		req.setAttribute("postList", result);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPage", lastPage);
		
		return "/jsp_page/board";
	}

	@GetMapping("/board/viewPost.do")
	public String showPost(HttpServletRequest req,int postNo) throws Exception{
		logger.info("BoardController, viewPost.");

		int teamCode = session.getAttribute("s_teamCode") == null ? 0 : Integer.parseInt(String.valueOf(session.getAttribute("s_teamCode")));

		incViews(postNo);

		BoardVO boardVO = new BoardVO();
		List<ReplyVO> replyVO = new ArrayList<>();
		boardVO.setTeamCode(teamCode);
		boardVO.setPostNo(postNo);

		boardVO = boardService.selectPost(boardVO);
		replyVO = boardService.selectReply(postNo);

		req.setAttribute("post", boardVO);
		req.setAttribute("reply", replyVO);

		return "/jsp_page/viewPost";
	}

	@GetMapping("/board/writeForm")
	public String showWriteForm() throws Exception{
		logger.info("BoardController, showWriteForm");

		return "/jsp_page/writePost";
	}

	@PostMapping("/board/writePost.do")
	public String writePost(BoardVO boardVO,String category) throws Exception{
		logger.info("BoardController, writePost");
		
		boardVO.setPostNo(getPostNo());
		
		boardService.writePost(boardVO);
		
		return "redirect:/board/viewPost.do?postNo="+boardVO.getPostNo();
	}

	@GetMapping("/board/deletePost.do")
	public String deletePost(HttpServletRequest req,int postNo, String category) throws Exception{
		logger.info("BoardController, deletePost");

		boardService.deletePost(postNo);
		
		req.setAttribute("msg", "삭제되었습니다.");
		req.setAttribute("url", "/board/postList.do?category="+category);
		return "/jsp_page/redirect";
	}

	@GetMapping("/board/modifyPost")
	public String showModifyPost(HttpServletRequest req,int postNo) throws Exception{
		logger.info("BoardController, showModifyPost.");

		int teamCode = session.getAttribute("s_teamCode") == null ? 0 : Integer.parseInt(String.valueOf(session.getAttribute("s_teamCode")));

		BoardVO boardVO = new BoardVO();

		boardVO.setPostNo(postNo);
		boardVO.setTeamCode(teamCode);
		boardVO = boardService.selectPost(boardVO);

		req.setAttribute("post", boardVO);

		return "/jsp_page/modPost";
	}

	@PostMapping("/board/modPost.do")
	public String modifyPost(BoardVO boardVO,HttpServletRequest req) throws Exception{
		logger.info("BoardController, modifyPost");
		
		logger.info("boardVO in modPost : {}", boardVO.toString());
		int result = boardService.modifyPost(boardVO);
		
		if(result==0) {
			req.setAttribute("msg", "수정에 실패하였습니다.");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}
		
		return "redirect:/board/viewPost.do?postNo="+boardVO.getPostNo();
	}

	@PostMapping("/board/writeReply.do")
	public String writeReply(ReplyVO replyVO) throws Exception{
		logger.info("BoardController, writeReply");

		replyVO.setReplyNo(getReplyNo());

		boardService.writeReply(replyVO);

		logger.info("Reply result : {}", replyVO.toString());

		return "redirect:/board/viewPost.do?postNo="+replyVO.getParentNo();
	}

	@GetMapping("/board/incRecommend.do")
	public String incRecommend(HttpServletRequest req,int postNo) throws Exception{
		logger.info("BoardController, incRecommend");

		RecommendVO recommendVO = new RecommendVO();

		recommendVO.setId(session.getAttribute("s_teamCode")==null ? null :(String)session.getAttribute("s_id"));
		recommendVO.setPostNo(postNo);

		if(chkRecommend(recommendVO)!=0) {
			req.setAttribute("msg","이미 추천하셨습니다.");
			req.setAttribute("url","/board/viewPost.do?postNo="+postNo);
			return "/jsp_page/redirect";
		}

		req.setAttribute("msg","추천되었습니다.");
		req.setAttribute("url","/board/viewPost.do?postNo="+postNo);

		boardService.id_chkRecommend(recommendVO);
		boardService.incRecommend(postNo);

		return "/jsp_page/redirect";
	}

	@PostMapping("/board/removeReply.do")
	public String removeReply(HttpServletRequest req,ReplyVO replyVO) {

		logger.info("replyVO : {} ", replyVO.toString());

		String s_id=session.getAttribute("s_teamCode")==null ? null :(String)session.getAttribute("s_id");
		if(!s_id.equals(replyVO.getId())) {
			req.setAttribute("msg", "권한이 없습니다.");
			req.setAttribute("url", "/main");
			return "/jsp_page/redirect";
		}

		int result=boardService.removeReply(replyVO);
		if(result==0) {
			req.setAttribute("msg", "댓글 삭제에 실패하였습니다.");
			req.setAttribute("url", "/board/viewPost.do?postNo="+replyVO.getParentNo());
		}

		req.setAttribute("msg", "댓글이 삭제되었습니다.");
		req.setAttribute("url", "/board/viewPost.do?postNo="+replyVO.getParentNo());
		return "/jsp_page/redirect";
	}
	//글번호 매겨주는 메서드
	public int getPostNo() {
		logger.info("BoardController, getPostNo");
		
		if(boardService.getPostNo() == null) {
			return 1;
		}
		
		return boardService.getPostNo()+1;
	}

	public int getReplyNo() {
		logger.info("BoardController, getReplyNo");
		
		if(boardService.getReplyNo() == null) {
			return 1;
		}
		
		return boardService.getReplyNo()+1;
	}
	//조회수 올려주는 메서드
	public void incViews(int postNo) {
		logger.info("BoardController, incViews");

		int result = boardService.incViews(postNo);

		logger.info("viewResult : {}" , result);
	}
	//추천 중복검사 메서드
	public int chkRecommend(RecommendVO recommendVO) {
		return boardService.chkRecommend(recommendVO);
	}

}
