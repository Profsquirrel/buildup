package com.profsquirrel.buildup.team.vo;

public class TeamVO {
	private String teamName;
	private int teamCode;
	private String teamGoal;
	private int memberCnt;
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

	public int getMemberCnt() {
		return memberCnt;
	}

	public void setMembercnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}

	@Override
	public String toString() {
		return "TeamVO [teamName=" + teamName + ", teamCode=" + teamCode + ", teamGoal=" + teamGoal + ", membercnt="
				+ memberCnt + ", teamLeader=" + teamLeader + "]";
	}


}
