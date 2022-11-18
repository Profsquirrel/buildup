package buildUp.teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import buildUp.members.MemberVO;

public class TeamDAO {
	private PreparedStatement pstmt;
	private Connection conn;
	private DataSource sql;
	
	//TeamDAO를 호출하면 SQL에 연결해주기
	public TeamDAO() {
		try {
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			sql=(DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			System.out.println("DB 연결 중 오류 발생 : " + e.getMessage());
		}
	}
	
	public TeamVO selectTeam(int teamCode) {
		TeamVO team = new TeamVO();
		try {
			conn=sql.getConnection();
			String query="select * from buildup_team where teamCode=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, teamCode);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				team.setTeamName(rs.getString("teamName"));
				team.setTeamCode(rs.getInt("teamCode"));
				team.setTeamGoal(rs.getString("teamGoal"));
				team.setMembercnt(rs.getInt("membercnt"));
				team.setTeamLeader(rs.getString("teamLeader"));
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("팀 조회 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return team;
	}
	
	public List<MemberVO> selectTeamMember(int teamCode) {
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		try {
			conn=sql.getConnection();
			String query="select * from buildup_member where teamCode=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, teamCode);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member=new MemberVO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setJob(rs.getString("job"));
				memberList.add(member);
			}
			rs.close();
		}catch(Exception e) {
			System.out.println("팀원 정보 받아오는 중 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return memberList;
	}
	//팀 추가 메서드
	public int addTeam(TeamVO team, String id) {
		int teamCode=code_gen();
		try {
			conn=sql.getConnection();
			//팀 정보 DB에 등록
			String query="insert into buildup_team(teamName,teamCode,teamGoal,teamleader,membercnt) values(?,?,?,?,1)";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, team.getTeamName());
			pstmt.setInt(2, teamCode);
			pstmt.setString(3, team.getTeamGoal());
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			//member 정보 중 teamName와 teamCode, job을 요청받은 아이디에 저장
			query="update buildup_member set teamName=?, teamCode=?, job='leader' where id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,team.getTeamName());
			pstmt.setInt(2, teamCode);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원가입 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return teamCode;
	}
	//팀에 멤버 추가하는 메서드
	public String insertMember(String id, int teamCode) {
		String teamName="";
		try {
			conn=sql.getConnection();
			String query="select teamName from buildup_team where (select teamCode from buildup_member where id=?) is null and teamCode=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setInt(2, teamCode);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				query="update buildup_member set teamName=?, teamCode=? ,job='member' where id=?";
				System.out.println(query);
				pstmt=conn.prepareStatement(query);
				teamName=rs.getString("teamName");
				pstmt.setString(1, teamName);
				pstmt.setInt(2, teamCode);
				pstmt.setString(3,id);
				pstmt.executeUpdate();
			}
			updateTeamInfo(teamCode);
			rs.close();
		}catch(Exception e) {
			System.out.println("그룹 회원 추가 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return teamName;
	}
	//팀 정보 수정
	public void modTeamInfo(TeamVO team) {
		try {
			conn=sql.getConnection();
			String query="update buildup_team set teamName=? teamGoal=? where teamCode=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, team.getTeamName());
			pstmt.setString(2, team.getTeamGoal());
			pstmt.setInt(3, team.getTeamCode());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("팀 정보 수정 중 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
	}
	//팀 탈퇴
	public void quitTeam(String id,int teamCode) {
		try {
			conn=sql.getConnection();
			String query="update buildup_member set teamName='', teamCode='' where id=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			updateTeamInfo(teamCode);
		}catch(Exception e) {
			System.out.println("팀 탈퇴 중 오류 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
	}
	//입력한 코드가 있는지 확인하기
	public boolean codeExist(int teamCode) {
		boolean isExist=false;
		try {
			conn=sql.getConnection();
			String query="select teamName from buildup_team where teamCode=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, teamCode);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				isExist=true;
			}
			rs.close();
		}catch(Exception e){
			System.out.println("코드 판별 중 오류 발생 :" + e.getMessage() );
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return isExist;
	}
	//팀 코드 생성
	private int code_gen() {
		int code=(int)(Math.random()*100000)+100000;
		try {
			conn=sql.getConnection();
			String query="select teamCode from buildup_member where teamCode=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, code);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				code=code_gen();
				return code;
			}
			rs.close();
		}catch(Exception e){
			System.out.println("팀 번호 생성 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
		return code;
	}
	//팀원 수 업데이트
	public void updateTeamInfo(int teamCode) {
		try {
			conn=sql.getConnection();
			String query="update buildup_team set membercnt=(select count(id) from buildup_member where teamCode=?) where teamCode=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, teamCode);
			pstmt.setInt(2, teamCode);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("팀 정보 업데이트 중 오류 발생 : " + e.getMessage());
		}finally {
		    if(pstmt != null) try { pstmt.close();} catch(SQLException ex) {}
		    if(conn != null) try { conn.close();} catch(SQLException ex) {}
		}
	}
}