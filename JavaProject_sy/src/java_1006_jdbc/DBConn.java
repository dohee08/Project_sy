package java_1006_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {
	//Field
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521";
	private String user = "scott";
	private String pass = "tiger";
	
	Connection conn;
	public Statement stmt;   //아무것도 붙지 않으면 같은 package안에서만 사용가능, 다른 패키지에서 쓰기 위해서 public
	public ResultSet rs;
	
	//Constructor
	public DBConn() {
		try {
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 객체 생성
			conn = DriverManager.getConnection(url, user, pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	//3. Statement 객체 생성
	public void getStatement() {
		try {
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//6. close
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}//class
