package db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {	//DB연동 공통모듈 생성
	//Field
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521";
	private String user = "scott";
	private String pass = "tiger";
	
	public Connection conn;		//다른 패키지에서 호출할 수 있도록 public
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	//Constructor
	public DBConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1-> 드라이버 로딩 성공");
			
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("2-> Connection객체 생성");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	//Statement 객체 생성
	public void getStatement() {
		try {
			stmt = conn.createStatement();
			System.out.println("3-> Statement객체 생성");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//PreparedStatement 객체 생성
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("3-> PreparedStatement객체 생성");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//객체 close
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			System.out.println("6-> 객체 close 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}//class
