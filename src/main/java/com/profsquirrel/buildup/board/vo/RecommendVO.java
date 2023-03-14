package com.profsquirrel.buildup.board.vo;

public class RecommendVO {
	private String id;
	private int postNo;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	@Override
	public String toString() {
		return "RecommendVO [id=" + id + ", postNo=" + postNo + ", getId()=" + getId() + ", getPostNo()=" + getPostNo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public RecommendVO() {
	}
}
