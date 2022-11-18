package buildUp.teams;

import java.util.ArrayList;
import java.util.List;

import buildUp.members.MemberVO;

public class TeamService {
	TeamDAO teamDAO;
	
	public TeamService() {
		teamDAO=new TeamDAO();
	}
	
	public int addTeam(TeamVO team, String id) {
		int result = teamDAO.addTeam(team,id);
		return result;
	}
	
	public String insertMember(String id,int teamCode) {
		String result="";
		boolean isExist=teamDAO.codeExist(teamCode);
		if(isExist) {
			result=teamDAO.insertMember(id, teamCode);
			return result;
		}
		return result;
	}
	
	public TeamVO selectTeam(int teamCode) {
		TeamVO team=new TeamVO();
		team=teamDAO.selectTeam(teamCode);
		return team;
	}

	public List<MemberVO> selectTeamMember(int teamCode) {
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		memberList=teamDAO.selectTeamMember(teamCode);
		return memberList;
	}
	
	public void modTeamInfo(TeamVO team) {
		teamDAO.modTeamInfo(team);
	}
	
	public void quitTeam(String id, int teamCode) {
		teamDAO.quitTeam(id,teamCode);
	}
}
