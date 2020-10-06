package java_1006_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection2 {

	public static void main(String[] args) {
		try {
			//0. ����̹� �غ�
			//1. ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1------->> ����̹� �ε� �Ϸ�");
			
			//2. Connection ��ü ����
			String url = "jdbc:oracle:thin:@127.0.0.1:1521";
			String user = "scott";
			String pass = "tiger";
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("2------->> Connection ��ü ����");
			
			//3. Statement ��ü ����
			Statement stmt = conn.createStatement();
			System.out.println("3------->> Statement ��ü ����");
			
			//4. ResultSet ��ü ����
			String sql = "SELECT EMPNO, ENMAE FROM EMP";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("4------->> ResultSet ��ü ����");
			
			//5. ResultSet ��ü���� ������ �������� ---> SQL ������ select�� ��쿡�� ����
			System.out.println("-----------------------------");
			System.out.println("�����ȣ \t �����");
			System.out.println("-----------------------------");
			while(rs.next()) {
				System.out.println(rs.getInt(1) + "\t");
				System.out.println(rs.getString(2) + "\n");
			}
			
			//6. close
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
			System.out.println("6------->> ��ü close");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
