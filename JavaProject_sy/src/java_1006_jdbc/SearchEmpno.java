package java_1006_jdbc;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchEmpno {

	public static void main(String[] args) {
		//�����ȣ �˻�
		Scanner scan = new Scanner(System.in);
		//DBConn db = new DBConn();
		EmpDAO dao = new EmpDAO();
		
		//�����ȣ ---> �������
		System.out.print("�����ȣ >> ");
		String empno = scan.next();
		if(empno.equals("all")) {
			ArrayList<EmpVO> list = dao.getResultList();
			
			System.out.println("----------------------------------");
			System.out.print("�����ȣ\t�����\t����\t�Ի���\n");
			System.out.println("----------------------------------");
			for(EmpVO vo : list) {
				System.out.print(vo.getEmpno() + "\t");
				System.out.print(vo.getEname() + "\t");
				System.out.print(vo.getJob() + "\t");
				System.out.print(vo.getHiredate() + "\n");
			}
			System.out.println("----------------------------------");
		}else{
			EmpVO vo = dao.getResultList(Integer.parseInt(empno));
			if(vo.getEmpno() != 0) {
				System.out.println("----------------------------------");
				System.out.print("�����ȣ\t�����\t����\t�Ի���\n");
				System.out.println("----------------------------------");
				System.out.print(vo.getEmpno() + "\t");
				System.out.print(vo.getEname() + "\t");
				System.out.print(vo.getJob() + "\t");
				System.out.print(vo.getHiredate() + "\n");
				System.out.println("----------------------------------");
			}else {
				System.out.println("�ش� �����Ͱ� �������� �ʽ��ϴ�.");
			}
		}
		
		dao.close();	//EmpDAO�� �θ� DBConn�� �ִ� close()�޼ҵ� ��� ����
	}

}
