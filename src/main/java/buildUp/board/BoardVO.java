package buildUp.board;

import java.sql.Date;

public class BoardVO {
	private int visibleNo;
	private int postNo;
	private int parentNo;
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
	
	public BoardVO(int postNo, int parentNo, String name, String title, String content, int views, int recommend,
			int teamCode, Date writeDate, String category, String fileName,int cntReply,int visibleNo) {
		this.postNo = postNo;
		this.parentNo = parentNo;
		this.name = name;
		this.title = title;
		this.content = content;
		this.views = views;
		this.recommend = recommend;
		this.teamCode = teamCode;
		this.writeDate = writeDate;
		this.category = category;
		this.fileName = fileName;
		this.cntReply = cntReply;
		this.visibleNo = visibleNo;
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

	public BoardVO() {
		System.out.println("boardVO 호출");
	}

}
