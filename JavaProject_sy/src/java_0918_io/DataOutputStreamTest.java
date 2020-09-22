package java_0918_io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class DataOutputStreamTest {

	public static void main(String[] args) {
		//1. 네트워크의 최종 목적지 File, 여러가지 형태의 데이터를 저장하기 위해 확장자 'dat'
		File file = new File("C:/Users/User/eclipse-workspace/sample.dat");
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			if(file.exists()) {
				//2. File - OutputStream과 연결
				fos = new FileOutputStream(file);
				//3. OutputStream - DataoutputStream 연결
				dos = new DataOutputStream(fos);
				
				//4. DataOutputStream은 다양한 데이터 전송 가능
				dos.writeUTF("홍길동"); 		//문자전송
				dos.writeInt(100);	  		//정수 100
				dos.writeDouble(100.345); 	//실수 100.345
				dos.writeBoolean(true);		//boolean타입 전송
				
				System.out.println("데이터 전송 완료~~~");
			}else {
				if(file.createNewFile()) System.out.println("파일 생성 완료~");
			}
		} catch (Exception e) {
			//Exception발생 시 Stack에 출력
			e.printStackTrace();
		}
	}

}
