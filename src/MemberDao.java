

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//Dao -> Data Assess Object -> DML
public class MemberDao {
	private Connection getConnection() {
	Connection conn = null;
	try {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
		conn = ds.getConnection();
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	return conn;
}
	public int insert(MemberDto member) throws SQLException {
		// 데이터 혼동을 주지 않기 위해서 초기값을 null로 잡음
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into member1 values(?,?,?,sysdate,?)";
		try {
			//context.xml에서 연결할 db 아이디 애스워드 url 다 설정 완료.
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getImage());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			//작성완료가 되면 무조건 닫아주기
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return result;
	}
}
