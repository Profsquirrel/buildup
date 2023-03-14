package com.profsquirrel.buildup.team.service;

import java.util.Map;

import com.profsquirrel.buildup.team.vo.TeamVO;

public interface TeamService {
	public int teamCodeGenerator();
	public int isExistLeader(String teamLeader);
	public int isExistTeamCode(int teamCode);
	public TeamVO selectTeam(int teamCode);
	public int increaseMemberCnt(int teamCode);
	public int decreaseMemberCnt(Map<String,Object> quitTeamMap);
	public int insertMember(Map<String, Object> joinMemberInfo);
	public int addTeam(TeamVO teamVO);
	public int switchLeader(Map<String,Object> swLeader);
	public int switchLeaderOnTeam(Map<String,Object> swLeader);
	public int quitTeam(Map<String,Object> quitTeamMap);
	public int modTeam(TeamVO teamVO);
	public int disband(TeamVO teamVO);
}
