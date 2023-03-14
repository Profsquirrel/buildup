package com.profsquirrel.buildup.board.vo;

import java.sql.Date;

public class BoardVO {
	private int visibleNo;
	private int postNo;
	private int parentNo;
	private int rNum;
	private String name;
	private String title;
	private String content;
	private int views;
	private int recommend;
	private int teamCode;
	private Date writeDate;
	private String category;
	private String fileName;
	private int cntReply;
	private String id;

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public int getVisibleNo() {
		return visibleNo;
	}

	public void setVisibleNo(int visibleNo) {
		this.visibleNo = visibleNo;
	}

	public int getCntReply() {
		return cntReply;
	}

	public void setCntReply(int cntReply) {
		this.cntReply = cntReply;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(int teamCode) {
		this.teamCode = teamCode;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BoardVO [visibleNo=" + visibleNo + ", postNo=" + postNo + ", parentNo=" + parentNo + ", name=" + name
				+ ", title=" + title + ", content=" + content + ", views=" + views + ", recommend=" + recommend
				+ ", teamCode=" + teamCode + ", writeDate=" + writeDate + ", category=" + category + ", fileName="
				+ fileName + ", cntReply=" + cntReply + ", id=" + id + "]";
	}

	public BoardVO() {
	}

}
