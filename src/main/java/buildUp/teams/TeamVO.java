package buildUp.teams;

public class TeamVO {
	private String teamName;
	private int teamCode;
	private String teamGoal;
	private int membercnt;
	private String teamLeader;
	
	public String getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}

	public TeamVO() {
		
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(int teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamGoal() {
		return teamGoal;
	}

	public void setTeamGoal(String teamGoal) {
		this.teamGoal = teamGoal;
	}

	public int getMembercnt() {
		return membercnt;
	}

	public void setMembercnt(int membercnt) {
		this.membercnt = membercnt;
	}
}
