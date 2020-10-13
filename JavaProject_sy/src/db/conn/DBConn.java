package db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {	//DB���� ������ ����
	//Field
	private String url = "jdbc:oracle:thin:@127.0.0.1:1521";
	private String user = "scott";
	private String pass = "tiger";
	
	public Connection conn;		//�ٸ� ��Ű������ ȣ���� �� �ֵ��� public
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	//Constructor
	public DBConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1-> ����̹� �ε� ����");
			
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("2-> Connection��ü ����");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	//Statement ��ü ����
	public void getStatement() {
		try {
			stmt = conn.createStatement();
			System.out.println("3-> Statement��ü ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//PreparedStatement ��ü ����
	public void getPreparedStatement(String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("3-> PreparedStatement��ü ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ü close
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			System.out.println("6-> ��ü close �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}//class
