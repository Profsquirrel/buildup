package buildUp.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private DataSource sql;
	
	//TeamDAO를 호출하면 SQL에 연결해주기
	public BoardDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			sql=(DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("DB 연결 중 오류 발생 : " + e.getMessage());
		}
	}
	//게시글 리스트 반환(팀 코드에 따른 전체 글 목록)
	public List<BoardVO> allPostList(int teamCode){
		List<BoardVO> allPostList=new ArrayList<BoardVO>();
		try {
			conn=sql.getConnection();
			String query="select postNo,parentNo,category,title,name,views,recommend,writeDate from buildup_board where teamCode=? order by postNo desc";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, teamCode);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int postNo=rs.getInt("postNo");
				int parentNo=rs.getInt("parentNo");
				String title=rs.getString("title");
				String name=rs.getString("name");
				String category=rs.getString("category");
				int views=rs.getInt("views");
				int recommend=rs.getInt("recommend");
				Date writeDate=rs.getDate("writeDate");
				BoardVO board=new BoardVO();
				board.setPostNo(postNo);
				board.setParentNo(parentNo);
				board.setTitle(title);
				board.setName(name);
				board.setCategory(category);
				board.setViews(views);
				board.setRecommend(recommend);
				board.setWriteDate(writeDate);
				allPostList.add(board);
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("전체 글 목록 가져오는 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return allPostList;
	}
	//카테고리에 따른 글 목록 반환
	public List<BoardVO> postList(int teamCode,String category) {
		List<BoardVO> postList = new ArrayList<BoardVO>();
		try {
			int i=0;
			conn=sql.getConnection();
			String query="select (select count(*) from buildup_reply reply where reply.parentNo=buildup_board.postNo)";
			query+= " as cnt, (select count(*) from buildup_board where teamCode=? and Category=?) as postcnt, postNo, title, name, views, recommend, writeDate from buildup_board where teamCode=? and Category=? order by writeDate desc";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, teamCode);
			pstmt.setString(2, category);
			pstmt.setInt(3, teamCode);
			pstmt.setString(4, category);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int visibleNo=rs.getInt("postcnt")-i;
				int postNo=rs.getInt("postNo");
				String title=rs.getString("title");
				String name=rs.getString("name");
				int views=rs.getInt("views");
				int recommend=rs.getInt("recommend");
				int cnt=rs.getInt("cnt");
				Date writeDate=rs.getDate("writeDate");
				BoardVO boardVO=new BoardVO();
				boardVO.setVisibleNo(visibleNo);
				boardVO.setPostNo(postNo);
				boardVO.setTitle(title);
				boardVO.setName(name);
				boardVO.setViews(views);
				boardVO.setRecommend(recommend);
				boardVO.setCntReply(cnt);
				boardVO.setWriteDate(writeDate);
				postList.add(boardVO);
				i++;
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("게시글 목록 가져오는 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return postList;
	}
	//게시글 추가
	public void addPost(BoardVO boardVO) {
		try {
			conn=sql.getConnection();
			String query="insert into buildup_board(postno,name,title,content,teamcode,category";
			if(boardVO.getFileName()!="") {
				query+=",fileName) values(?,?,?,?,?,?,?)";
			}else {
				query+=") values(?,?,?,?,?,?)";
			}
			int postNo=getPostNum();
			pstmt=conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setInt(1, postNo);
			pstmt.setString(2, boardVO.getName());
			pstmt.setString(3, boardVO.getTitle());
			pstmt.setString(4, boardVO.getContent());
			pstmt.setInt(5, boardVO.getTeamCode());
			pstmt.setString(6, boardVO.getCategory());
			if(boardVO.getFileName()!="") {
				pstmt.setString(7, boardVO.getFileName());
			}
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시글 작성 중 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
	}
	//글 열람
	public BoardVO viewPost(int postNo) {
		BoardVO board=new BoardVO();
		try {
			conn=sql.getConnection();
			String query="select * from buildup_board where postNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				board.setPostNo(rs.getInt("postNo"));
				board.setName(rs.getString("name"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setViews(rs.getInt("views"));
				board.setRecommend(rs.getInt("recommend"));
				board.setTeamCode(rs.getInt("teamCode"));
				board.setWriteDate(rs.getDate("writeDate"));
				board.setCategory(rs.getString("category"));
				board.setFileName(rs.getString("fileName"));
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("게시글 가져오는 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return board;
	}
	
	//조회수 증가
	public void incViews(int postNo) {
		try {
			conn=sql.getConnection();
			String query="update buildup_board set views=views+1 where postNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("조회수 증가 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
	}
	
	public void incRecommend(int postNo) {
		try {
			conn=sql.getConnection();
			String query="update buildup_board set recommend=recommend+1 where postNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("추천 중 오류 : " + e.getMessage());
		}
	}
	
	public int getPostNum() {
		int result=0;
		try {
			conn=sql.getConnection();
			String query="select max(postNo) from buildup_board";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1)+1;
				System.out.println("글번호 = " + result);
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("게시글 번호 생성 중 오류 " + e.getMessage());
		}
		return result;
	}
	
	public List<ReplyVO> getReply(int postNo) {
		List<ReplyVO> replyList=new ArrayList<ReplyVO>();
		try {
			conn=sql.getConnection();
			String query="select * from buildup_reply where parentNo=? order by replyNo";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, postNo);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				ReplyVO reply=new ReplyVO();
				reply.setParentNo(rs.getInt("parentNo"));
				reply.setReplyNo(rs.getInt("replyNo"));
				reply.setContent(rs.getString("content"));
				reply.setName(rs.getString("name"));
				reply.setWriteDate(rs.getDate("writeDate"));
				replyList.add(reply);
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("댓글 목록 가져오는 중 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return replyList;
	}
	
	public void removeReply(int replyNo) {
		try {
			conn=sql.getConnection();
			String query="delete from buildup_reply where replyNo=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("댓글 삭제 중 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
	}
	
	public boolean writeReply(ReplyVO reply) {
		boolean result=true;
		try {
			int replyNo=genReplyNo();
			int parentNo=reply.getParentNo();
			String name=reply.getName();
			String content=reply.getContent();
			conn=sql.getConnection();
			String query="insert into buildup_reply(replyNo,parentNo,name,content) values(?,?,?,?)";
			pstmt=conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setInt(1, replyNo);
			pstmt.setInt(2, parentNo);
			pstmt.setString(3, name);
			pstmt.setString(4, content);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			result=false;
			System.out.println("댓글 작성 중 오류 : " + e.getMessage());
		}
		return result;
	}
	
	public int genReplyNo() {
		int replyNo=0;
		try {
			conn=sql.getConnection();
			String query="select count(*) as cnt from buildup_reply";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				replyNo=rs.getInt("cnt")+1;
			}
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println("댓글 번호 생성 중 오류 : " + e.getMessage());
		}
		return replyNo;
	}
}