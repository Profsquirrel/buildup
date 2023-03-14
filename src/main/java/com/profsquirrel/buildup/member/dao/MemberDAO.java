package com.profsquirrel.buildup.member.dao;

import java.util.List;

import com.profsquirrel.buildup.member.vo.MemberVO;

public interface MemberDAO {
	public int chkID(String id);
	public MemberVO userLogin(String id);
	public MemberVO getUserInfo(MemberVO memberVO);
	public int addMember(MemberVO memberVO);
	public List<MemberVO> getTeamMember(int teamCode);
	public int modMember(MemberVO memberVO);
	public int quitMember(MemberVO memberVO);
}
