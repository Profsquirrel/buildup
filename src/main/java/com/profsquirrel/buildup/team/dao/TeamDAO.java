package com.profsquirrel.buildup.team.dao;

import java.util.Map;

import com.profsquirrel.buildup.team.vo.TeamVO;

public interface TeamDAO {
	public int teamCodeGenerator();
	public int isExistTeamCode(int teamCode);
	public int isExistLeader(String teamLeader);
	public TeamVO selectTeam(int teamCode);
	public int increaseMemberCnt(int teamCode);
	public int decreaseMemberCnt(Map<String,Object> quitTeamMap);
	public int switchLeader(Map<String,Object> swLeader);
	public int switchLeaderOnTeam(Map<String,Object> swLeader);
	public int insertMember(Map<String, Object> joinMemberInfo);
	public int addTeam(TeamVO teamVO);
	public int quitTeam(Map<String,Object> quitTeamMap);
	public int modTeam(TeamVO teamVO);
	public int disband(TeamVO teamVO);
}
