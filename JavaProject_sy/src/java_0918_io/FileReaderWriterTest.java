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
				
				//���
				String str = "�ڹ� ����� �׽�Ʈ �Դϴ�";
				fw.write(str, 0, str.length());
				fw.flush();
				System.out.println("---------> ���� ���� �Ϸ�");
				
				//�Է�
				System.out.println("---------> ���� ���� ����");
				char[] c = new char[100];
				fr.read(c, 0, c.length);
				System.out.println(new String(c));
				System.out.println("---------> ���� ���� ����");
			}else {
				if(file.createNewFile()) {
					System.out.println("--------> ���� ���� �Ϸ�");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class
