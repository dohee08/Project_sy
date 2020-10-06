package java_1006_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnTest {

	public static void main(String[] args) {
		try {
			//step 0 : ����̹� �غ� - ��Ŭ���� ������Ʈ�� Oracle ����̹� �߰�, ��ġ�� Build path
			//step 1 : ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1------------> ����̹� �ε� �Ϸ�!!");
			
			//step 2 : Connection ��ü ����
			String url = "jdbc:oracle:thin:@localhost:1521";
			String user = "scott";
			String password = "tiger";
			Connection db = DriverManager.getConnection(url, user, password);
			System.out.println("2------------> Connection ��ü ����!!");
			
			//step 3 : Statement ��ü ����
			Statement stmt = db.createStatement();
			System.out.println("3------------> Statement ��ü ����!!");
			
			//step 4 : ResultSet ��ü ����
			String sql = "SELECT EMPNO, ENAME, SAL, TO_CHAR(HIREDATE, 'YY-MM-DD') FROM EMP WHERE SAL>=2000";
			ResultSet rs = stmt.executeQuery(sql);
			
			//step 5 : ResultSet ��ü���� ������ ��������
			while(rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getInt(3) + "\t");
				System.out.print(rs.getString(4) + "\n");
			}
			
			//step 6 : ������ ��ü ����
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(db != null) db.close();
			
			System.out.println("6------------> ���α׷� ����~!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
