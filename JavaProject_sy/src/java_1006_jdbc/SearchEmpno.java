package java_1006_jdbc;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchEmpno {

	public static void main(String[] args) {
		//사원번호 검색
		Scanner scan = new Scanner(System.in);
		//DBConn db = new DBConn();
		EmpDAO dao = new EmpDAO();
		
		//사원번호 ---> 결과리턴
		System.out.print("사원번호 >> ");
		String empno = scan.next();
		if(empno.equals("all")) {
			ArrayList<EmpVO> list = dao.getResultList();
			
			System.out.println("----------------------------------");
			System.out.print("사원번호\t사원명\t직급\t입사일\n");
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
				System.out.print("사원번호\t사원명\t직급\t입사일\n");
				System.out.println("----------------------------------");
				System.out.print(vo.getEmpno() + "\t");
				System.out.print(vo.getEname() + "\t");
				System.out.print(vo.getJob() + "\t");
				System.out.print(vo.getHiredate() + "\n");
				System.out.println("----------------------------------");
			}else {
				System.out.println("해당 데이터가 존재하지 않습니다.");
			}
		}
		
		dao.close();	//EmpDAO의 부모 DBConn에 있는 close()메소드 사용 가능
	}

}
