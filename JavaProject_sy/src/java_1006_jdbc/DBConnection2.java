package java_1006_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection2 {

	public static void main(String[] args) {
		try {
			//0. 드라이버 준비
			//1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1------->> 드라이버 로딩 완료");
			
			//2. Connection 객체 생성
			String url = "jdbc:oracle:thin:@127.0.0.1:1521";
			String user = "scott";
			String pass = "tiger";
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("2------->> Connection 객체 생성");
			
			//3. Statement 객체 생성
			Statement stmt = conn.createStatement();
			System.out.println("3------->> Statement 객체 생성");
			
			//4. ResultSet 객체 생성
			String sql = "SELECT EMPNO, ENMAE FROM EMP";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("4------->> ResultSet 객체 생성");
			
			//5. ResultSet 객체에서 데이터 가져오기 ---> SQL 구문이 select인 경우에만 실행
			System.out.println("-----------------------------");
			System.out.println("사원번호 \t 사원명");
			System.out.println("-----------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1) + "\t");
				System.out.println(rs.getString(2) + "\n");
			}
			
			//6. close
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			System.out.println("6------->> 객체 close");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
