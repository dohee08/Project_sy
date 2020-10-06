package java_1006_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnTest {

	public static void main(String[] args) {
		try {
			//step 0 : 드라이버 준비 - 이클립스 프로젝트에 Oracle 드라이버 추가, 위치는 Build path
			//step 1 : 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1------------> 드라이버 로딩 완료!!");
			
			//step 2 : Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521";
			String user = "scott";
			String password = "tiger";
			Connection db = DriverManager.getConnection(url, user, password);
			System.out.println("2------------> Connection 객체 생성!!");
			
			//step 3 : Statement 객체 생성
			Statement stmt = db.createStatement();
			System.out.println("3------------> Statement 객체 생성!!");
			
			//step 4 : ResultSet 객체 생성
			String sql = "SELECT EMPNO, ENAME, SAL, TO_CHAR(HIREDATE, 'YY-MM-DD') FROM EMP WHERE SAL>=2000";
			ResultSet rs = stmt.executeQuery(sql);
			
			//step 5 : ResultSet 객체에서 데이터 가져오기
			while(rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getInt(3) + "\t");
				System.out.print(rs.getString(4) + "\n");
			}
			
			//step 6 : 생성한 객체 종료
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(db != null) db.close();
			
			System.out.println("6------------> 프로그램 종료~!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
