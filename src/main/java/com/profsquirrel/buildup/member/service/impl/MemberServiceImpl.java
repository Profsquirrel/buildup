package com.profsquirrel.buildup.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profsquirrel.buildup.member.dao.MemberDAO;
import com.profsquirrel.buildup.member.service.MemberService;
import com.profsquirrel.buildup.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public MemberVO userLogin(String id) {
		return memberDAO.userLogin(id);
	}

	@Override
	public MemberVO getUserInfo(MemberVO memberVO) {
		return memberDAO.getUserInfo(memberVO);
	}

	@Override
	public int addMember(MemberVO memberVO) {
		return memberDAO.addMember(memberVO);
	}

	@Override
	public List<MemberVO> getTeamMember(int teamCode) {
		return memberDAO.getTeamMember(teamCode);
	}

	@Override
	public int modMember(MemberVO memberVO) {
		return memberDAO.modMember(memberVO);
	}

	@Override
	public int quitMember(MemberVO memberVO) {
		return memberDAO.quitMember(memberVO);
	}

	@Override
	public int chkID(String id) {
		return memberDAO.chkID(id);
	}


}
