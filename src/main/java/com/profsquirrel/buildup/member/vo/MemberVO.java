package com.profsquirrel.buildup.member.vo;

public class MemberVO {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String teamName;
	private String job;
	private int teamCode;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTeamName() {
		return teamName;
	}


	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public int getTeamCode() {
		return teamCode;
	}


	public void setTeamCode(int teamCode) {
		this.teamCode = teamCode;
	}


	public MemberVO(String id, String pw, String name, String email, String job) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.job = job;
	}

	public MemberVO() {
	}


	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", teamName=" + teamName
				+ ", job=" + job + ", teamCode=" + teamCode + "]";
	}


}
