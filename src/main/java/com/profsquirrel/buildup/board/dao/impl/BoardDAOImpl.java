package com.profsquirrel.buildup.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.profsquirrel.buildup.board.dao.BoardDAO;
import com.profsquirrel.buildup.board.vo.BoardVO;
import com.profsquirrel.buildup.board.vo.RecommendVO;
import com.profsquirrel.buildup.board.vo.ReplyVO;
@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession session;

	private String namespace = "com.profsquirrel.buildup.board.";

	@Override
	public List<BoardVO> selectCategoryList(Map<String,Object> postPaging) {
		return session.selectList(namespace+"selectCategoryList",postPaging);
	}

	@Override
	public List<BoardVO> selectAllPostList(int teamCode) {
		return session.selectList(namespace+"selectAllPostList",teamCode);
	}
	@Override
	public BoardVO selectPost(BoardVO boardVO) {
		return session.selectOne(namespace+"selectPost",boardVO);
	}

	@Override
	public List<ReplyVO> selectReply(int postNo) {
		return session.selectList(namespace+"selectReply",postNo);
	}

	@Override
	public int modifyPost(BoardVO boardVO) {
		return session.update(namespace+"modifyPost",boardVO);
	}

	@Override
	public int deletePost(int postNo) {
		return session.delete(namespace+"deletePost",postNo);
	}

	@Override
	public int writePost(BoardVO boardVO) {
		return session.insert(namespace+"writePost",boardVO);
	}

	@Override
	public Integer getPostNo() {
		return session.selectOne(namespace+"getPostNo");
	}

	@Override
	public int writeReply(ReplyVO replyVO) {
		return session.insert(namespace+"writeReply",replyVO);
	}

	@Override
	public Integer getReplyNo() {
		return session.selectOne(namespace+"getReplyNo");
	}

	@Override
	public int incViews(int postNo) {
		return session.update(namespace+"incViews",postNo);
	}

	@Override
	public int incRecommend(int postNo) {
		return session.update(namespace+"incRecommend",postNo);
	}

	@Override
	public int chkRecommend(RecommendVO recommendVO) {
		return session.selectOne(namespace+"chkRecommend",recommendVO);
	}

	@Override
	public int id_chkRecommend(RecommendVO recommendVO) {
		return session.insert(namespace+"id_chkRecommend",recommendVO);
	}

	@Override
	public int removeReply(ReplyVO replyVO) {
		return session.delete(namespace+"removeReply",replyVO);
	}

	@Override
	public List<BoardVO> searchPost(Map<String, Object> searchData) {
		return session.selectList(namespace+"searchPost",searchData);
	}

	@Override
	public int returnNumberOfPosts(Map<String, Object> postPaging) {
		return session.selectOne(namespace+"returnNumberOfPosts",postPaging);
	}

	@Override
	public int searchNumberOfPosts(Map<String, Object> postPaging) {
		return session.selectOne(namespace+"searchNumberOfPosts",postPaging);
	}


}
