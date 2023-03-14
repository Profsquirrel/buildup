package com.profsquirrel.buildup.board.dao;

import java.util.List;
import java.util.Map;

import com.profsquirrel.buildup.board.vo.BoardVO;
import com.profsquirrel.buildup.board.vo.RecommendVO;
import com.profsquirrel.buildup.board.vo.ReplyVO;

public interface BoardDAO {
	public List<BoardVO> selectCategoryList(Map<String,Object> postPaging);
	public List<BoardVO> selectAllPostList(int teamCode);
	public List<BoardVO> searchPost(Map<String,Object> searchData);
	public BoardVO selectPost(BoardVO boardVO);
	public List<ReplyVO> selectReply(int postNo);
	public int writePost(BoardVO boardVO);
	public int writeReply(ReplyVO replyVO);
	public int returnNumberOfPosts(Map<String,Object> postPaging);
	public int searchNumberOfPosts(Map<String,Object> postPaging);
	public int incViews(int postNo);
	public int incRecommend(int postNo);
	public int chkRecommend(RecommendVO recommendVO);
	public int id_chkRecommend(RecommendVO recommendVO);
	public Integer getPostNo();
	public Integer getReplyNo();
	public int modifyPost(BoardVO boardVO);
	public int removeReply(ReplyVO replyVO);
	public int deletePost(int postNo);
}
