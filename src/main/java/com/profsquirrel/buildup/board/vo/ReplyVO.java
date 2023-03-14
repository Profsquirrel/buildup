package com.profsquirrel.buildup.board.vo;

import java.sql.Date;

public class ReplyVO {
	private int replyNo;
	private int parentNo;
	private String name;
	private String content;
	private String id;
	private Date writeDate;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", parentNo=" + parentNo + ", name=" + name + ", content=" + content
				+ ", id=" + id + ", writeDate=" + writeDate + "]";
	}

	public ReplyVO() {

	}

}
