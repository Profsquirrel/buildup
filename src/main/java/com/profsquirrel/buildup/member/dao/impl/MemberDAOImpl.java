package com.profsquirrel.buildup.member.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.profsquirrel.buildup.member.dao.MemberDAO;
import com.profsquirrel.buildup.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	/*
	 * 로그인 처리
	*/
	@Autowired
	private SqlSession session;

	private String namespace = "com.profsquirrel.buildup.member.";

	@Override
	public MemberVO userLogin(String id) {
		return session.selectOne(namespace+"isExistUserInfo",id);
	}

	@Override
	public MemberVO getUserInfo(MemberVO memberVO) {
		return session.selectOne(namespace+"getUserInfo",memberVO);
	}

	@Override
	public int addMember(MemberVO memberVO) {
		return session.insert(namespace+"addMember",memberVO);
	}

	@Override
	public List<MemberVO> getTeamMember(int teamCode) {
		return session.selectList(namespace+"getTeamMember",teamCode);
	}

	@Override
	public int modMember(MemberVO memberVO) {
		return session.update(namespace+"modMember",memberVO);
	}

	@Override
	public int quitMember(MemberVO memberVO) {
		return session.delete(namespace+"quitMember",memberVO);
	}

	@Override
	public int chkID(String id) {
		return session.selectOne(namespace+"chkID",id);
	}

}
