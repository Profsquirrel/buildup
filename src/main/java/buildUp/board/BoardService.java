package buildUp.board;

import java.util.ArrayList;
import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO=new BoardDAO();
	}
	
	public List<BoardVO> allPostList(int teamCode){
		List<BoardVO> allPostList=new ArrayList<BoardVO>();
		allPostList=boardDAO.allPostList(teamCode);
		return allPostList;
	}
	
	public List<BoardVO> postList(int teamCode, String category) {
		List<BoardVO> postList=new ArrayList<BoardVO>();
		postList=boardDAO.postList(teamCode, category);
		return postList;
	}
	
	public void addPost(BoardVO boardVO) {
		boardDAO.addPost(boardVO);
	}
	
	public BoardVO viewPost(int postNo) {
		return boardDAO.viewPost(postNo);
	}
	
	public List<ReplyVO> getReply(int postNo){
		List<ReplyVO> reply=new ArrayList<ReplyVO>();
		reply=boardDAO.getReply(postNo);
		return reply;
	}
	
	public boolean writeReply(ReplyVO reply) {
		boolean result=boardDAO.writeReply(reply);
		return result;
	}
	
	public void removeReply(int replyNo) {
		boardDAO.removeReply(replyNo);
	}
}
