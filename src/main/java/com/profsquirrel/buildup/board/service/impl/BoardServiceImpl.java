package com.profsquirrel.buildup.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profsquirrel.buildup.board.dao.BoardDAO;
import com.profsquirrel.buildup.board.service.BoardService;
import com.profsquirrel.buildup.board.vo.BoardVO;
import com.profsquirrel.buildup.board.vo.RecommendVO;
import com.profsquirrel.buildup.board.vo.ReplyVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardVO> selectCategoryList(Map<String,Object> postPaging) {
		return boardDAO.selectCategoryList(postPaging);
	}

	@Override
	public List<BoardVO> selectAllPostList(int teamCode) {
		return boardDAO.selectAllPostList(teamCode);
	}

	@Override
	public BoardVO selectPost(BoardVO boardVO) {
		return boardDAO.selectPost(boardVO);
	}

	@Override
	public List<ReplyVO> selectReply(int postNo) {
		return boardDAO.selectReply(postNo);
	}

	@Override
	public int modifyPost(BoardVO boardVO) {
		return boardDAO.modifyPost(boardVO);
	}

	@Override
	public int deletePost(int postNo) {
		return boardDAO.deletePost(postNo);
	}

	@Override
	public int writePost(BoardVO boardVO) {
		return boardDAO.writePost(boardVO);
	}

	@Override
	public Integer getPostNo() {
		return boardDAO.getPostNo();
	}

	@Override
	public int writeReply(ReplyVO replyVO) {
		return boardDAO.writeReply(replyVO);
	}

	@Override
	public Integer getReplyNo() {
		return boardDAO.getReplyNo();
	}

	@Override
	public int incViews(int postNo) {
		return boardDAO.incViews(postNo);
	}

	@Override
	public int incRecommend(int postNo) {
		return boardDAO.incRecommend(postNo);
	}

	@Override
	public int chkRecommend(RecommendVO recommendVO) {
		return boardDAO.chkRecommend(recommendVO);
	}

	@Override
	public int id_chkRecommend(RecommendVO recommendVO) {
		return boardDAO.id_chkRecommend(recommendVO);
	}

	@Override
	public int removeReply(ReplyVO replyVO) {
		return boardDAO.removeReply(replyVO);
	}

	@Override
	public List<BoardVO> searchPost(Map<String, Object> searchData) {
		return boardDAO.searchPost(searchData);
	}

	@Override
	public int returnNumberOfPosts(Map<String, Object> postPaging) {
		return boardDAO.returnNumberOfPosts(postPaging);
	}

	@Override
	public int searchNumberOfPosts(Map<String, Object> postPaging) {
		return boardDAO.searchNumberOfPosts(postPaging);
	}


}
