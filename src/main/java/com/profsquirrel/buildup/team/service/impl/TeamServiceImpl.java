package com.profsquirrel.buildup.team.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profsquirrel.buildup.team.dao.TeamDAO;
import com.profsquirrel.buildup.team.service.TeamService;
import com.profsquirrel.buildup.team.vo.TeamVO;

@Service
public class TeamServiceImpl implements TeamService{

	@Autowired
	private TeamDAO teamDAO;

	@Override
	public int teamCodeGenerator() {
		return teamDAO.teamCodeGenerator();
	}

	@Override
	public TeamVO selectTeam(int teamCode) {
		return teamDAO.selectTeam(teamCode);
	}

	@Override
	public int increaseMemberCnt(int teamCode) {
		return teamDAO.increaseMemberCnt(teamCode);
	}

	@Override
	public int insertMember(Map<String, Object> joinMemberInfo) {
		return teamDAO.insertMember(joinMemberInfo);
	}

	@Override
	public int addTeam(TeamVO teamVO) {
		return teamDAO.addTeam(teamVO);
	}

	@Override
	public int isExistLeader(String teamLeader) {
		return teamDAO.isExistLeader(teamLeader);
	}

	@Override
	public int switchLeader(Map<String, Object> swLeader) {
		return teamDAO.switchLeader(swLeader);
	}

	@Override
	public int switchLeaderOnTeam(Map<String, Object> swLeader) {
		return teamDAO.switchLeaderOnTeam(swLeader);
	}

	@Override
	public int quitTeam(Map<String, Object> quitTeamMap) {
		return teamDAO.quitTeam(quitTeamMap);
	}

	@Override
	public int decreaseMemberCnt(Map<String, Object> quitTeamMap) {
		return teamDAO.decreaseMemberCnt(quitTeamMap);
	}

	@Override
	public int modTeam(TeamVO teamVO) {
		return teamDAO.modTeam(teamVO);
	}

	@Override
	public int disband(TeamVO teamVO) {
		return teamDAO.disband(teamVO);
	}

	@Override
	public int isExistTeamCode(int teamCode) {
		return teamDAO.isExistTeamCode(teamCode);
	}

}
