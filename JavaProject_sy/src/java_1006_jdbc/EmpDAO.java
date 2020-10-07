package java_1006_jdbc;

import java.util.ArrayList;

public class EmpDAO extends DBConn{
	
	//ResultSet rs;
	
	//4~5. ResultSet ��ü ���� �� ��� ����  -- ��ü ������
	public ArrayList<EmpVO> getResultList() {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			String sql = "select empno, ename, job, to_char(hiredate, 'yyyy/mm/dd') from emp";
			getStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				EmpVO vo = new EmpVO();		//���پ� �޾ƾ� �ϱ� ����
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getString(4));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//4~5. ResultSet ��ü ���� �� ��� ����  -- �Ѹ��� ������
	public EmpVO getResultList(int empno) {
		EmpVO vo = new EmpVO();
		
		try {
			String sql = "select empno, ename, job, to_char(hiredate, 'yyyy/mm/dd') from emp where empno = " + empno;
			getStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getString(4));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}	
}
