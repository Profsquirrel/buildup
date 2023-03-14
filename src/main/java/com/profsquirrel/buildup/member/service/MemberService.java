package com.profsquirrel.buildup.member.service;

import java.util.List;

import com.profsquirrel.buildup.member.vo.MemberVO;

public interface MemberService {
	public int chkID(String id);
	public MemberVO userLogin(String id);
	public MemberVO getUserInfo(MemberVO memberVO);
	public List<MemberVO> getTeamMember(int teamCode);
	public int addMember(MemberVO memberVO);
	public int modMember(MemberVO memberVO);
	public int quitMember(MemberVO memberVO);
}
