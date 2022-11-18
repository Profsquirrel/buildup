package buildUp.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private DataSource sql;
	MemberVO memberVO;
	//MemberDAO를 호출하면 SQL에 연결해주기
	public MemberDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			sql=(DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("DB 연결 중 오류 발생 : " + e.getMessage());
		}
	}
	public MemberVO selectMember(String id) {
		memberVO=new MemberVO();
		try {
			conn=sql.getConnection();
			String query="select * from buildup_member where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				memberVO.setId(rs.getString("id"));
				memberVO.setPw(rs.getString("pw"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJob(rs.getString("job"));
				memberVO.setTeamName(rs.getString("teamName"));
				memberVO.setTeamCode(rs.getInt("teamCode"));
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("회원 정보 가져오는 중 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return memberVO;
	}
	public MemberVO login(String id,String pw) {
		try {
			conn=sql.getConnection();
			String query="select * from buildup_member where id=? and pw=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				memberVO=new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setName(rs.getString("name"));
				memberVO.setJob(rs.getString("job"));
				memberVO.setTeamCode(rs.getInt("teamCode"));
				memberVO.setTeamName(rs.getString("teamName"));
				System.out.println(memberVO);
			}else {
				memberVO=new MemberVO();
				memberVO=null;
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("로그인 프로세스 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return memberVO;
	}
	//회원가입
	public int addMember(MemberVO member) {
		try {
			conn=sql.getConnection();
			String query="select id from buildup_member where id=?";
			pstmt=conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setString(1, member.getId());
			ResultSet rs=pstmt.executeQuery();
			System.out.println(rs.next());
			if(!rs.next()) {
				query="insert into buildup_member(id,pw,name,email) values(?,?,?,?)";
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1,member.getId());
				pstmt.setString(2,member.getPw());
				pstmt.setString(3,member.getName());
				pstmt.setString(4,member.getEmail());
				pstmt.executeQuery();
				return 0;
			}
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원가입 중 오류 발생 : " + e.getMessage());
		}
		finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return 1;
	}
	
	public void modMember(MemberVO member) {
		try {
			conn=sql.getConnection();
			String query="update buildup_member set pw=?,name=?,email=? where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원정보 수정 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
	}
}