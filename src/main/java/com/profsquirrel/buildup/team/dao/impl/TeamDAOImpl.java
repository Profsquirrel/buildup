package com.profsquirrel.buildup.team.dao.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.profsquirrel.buildup.team.dao.TeamDAO;
import com.profsquirrel.buildup.team.vo.TeamVO;

@Repository
public class TeamDAOImpl implements TeamDAO {

	String namespace = "com.profsquirrel.buildup.team.";

	@Autowired
	private SqlSession session;

	@Override
	public int teamCodeGenerator() {
		return session.selectOne(namespace+"teamCodeGenerator");
	}

	@Override
	public TeamVO selectTeam(int teamCode) {
		return session.selectOne(namespace+"selectTeam",teamCode);
	}

	@Override
	public int increaseMemberCnt(int teamCode) {
		return session.update(namespace+"increaseMemberCnt",teamCode);
	}

	@Override
	public int insertMember(Map<String, Object> joinMemberInfo) {
		return session.update(namespace+"insertMember",joinMemberInfo);
	}

	@Override
	public int addTeam(TeamVO teamVO) {
		return session.insert(namespace+"addTeam",teamVO);
	}

	@Override
	public int isExistLeader(String teamLeader) {
		return session.selectOne(namespace+"isExistLeader",teamLeader);
	}

	@Override
	public int switchLeader(Map<String,Object> swLeader) {
		return session.update(namespace+"switchLeader", swLeader);
	}

	@Override
	public int switchLeaderOnTeam(Map<String, Object> swLeader) {
		return session.update(namespace+"switchLeaderOnTeam",swLeader);
	}

	@Override
	public int quitTeam(Map<String, Object> quitTeamMap) {
		return session.update(namespace+"quitTeam",quitTeamMap);
	}

	@Override
	public int decreaseMemberCnt(Map<String, Object> quitTeamMap) {
		return session.update(namespace+"decreaseMemberCnt",quitTeamMap);
	}

	@Override
	public int modTeam(TeamVO teamVO) {
		return session.update(namespace+"modTeam",teamVO);
	}

	@Override
	public int disband(TeamVO teamVO) {
		return session.delete(namespace+"disband",teamVO);
	}

	@Override
	public int isExistTeamCode(int teamCode) {
		return session.selectOne(namespace+"isExistTeamCode",teamCode);
	}

}
