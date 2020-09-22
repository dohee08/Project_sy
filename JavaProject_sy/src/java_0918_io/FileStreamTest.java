package java_0918_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStreamTest {

	public static void main(String[] args) {
		File file = new File("C:/Users/User/eclipse-workspace/test1.txt");
		
		FileOutputStream fos = null;
		FileInputStream fis = null;
		
		try {
			if(file.exists()) {
				//파일 입출력
				fos = new FileOutputStream(file);
				fis = new FileInputStream(file);
				
				//출력
				fos.write(new String("자바 입출력 테스트~").getBytes());
				fos.flush(); //바로 전송
				System.out.println("----------> 파일 출력 완료");
				
				//입력 : 데이터를 담는 b 객체의 사이즈가 파일의 데이터보다 작은경우는 반복실행
				//		b객체의 사이즈가 파일 데이터보다 큰 경우는 반복실행 안함
				byte[] b = new byte[100];
				fis.read(b);
				System.out.println("-----------> 파일 입력 시작");
				System.out.println(new String(b));
				System.out.println("-----------> 파일 입력 종료");
			}else {
				if(file.createNewFile()) {
					System.out.println("-----------> 파일 생성 완료!!");
				}
			}
		}catch(Exception e) {
			
		}

	}//main method

}//class
