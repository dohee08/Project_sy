package java_0918_io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderWriterTest {

	public static void main(String[] args) {
		File file = new File("C:/Users/User/eclipse-workspace/test2.txt");
		
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			if(file.exists()) {
				fr = new FileReader(file);
				fw = new FileWriter(file);
				
				//출력
				String str = "자바 입출력 테스트 입니다";
				fw.write(str, 0, str.length());
				fw.flush();
				System.out.println("---------> 파일 전송 완료");
				
				//입력
				System.out.println("---------> 파일 수신 시작");
				char[] c = new char[100];
				fr.read(c, 0, c.length);
				System.out.println(new String(c));
				System.out.println("---------> 파일 수신 종료");
			}else {
				if(file.createNewFile()) {
					System.out.println("--------> 파일 생성 완료");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class
