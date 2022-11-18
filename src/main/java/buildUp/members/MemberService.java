package buildUp.members;

import javax.servlet.http.HttpSession;


public class MemberService {
	MemberDAO memberDAO;
	MemberVO memberVO;
	public MemberService() {
		memberDAO=new MemberDAO();
	}
	
	public int addMember(MemberVO member) {
		int result=memberDAO.addMember(member);
		return result;
	}
	
	public MemberVO login(String id, String pw) {
		memberVO=memberDAO.login(id, pw);
		return memberVO;
	}
	
	public MemberVO selectMember(String id) {
		memberVO=memberDAO.selectMember(id);
		return memberVO;
	}
	
	public void modMember(MemberVO member) {
		memberDAO.modMember(member);
	}
}
