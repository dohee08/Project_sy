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
				//���� �����
				fos = new FileOutputStream(file);
				fis = new FileInputStream(file);
				
				//���
				fos.write(new String("�ڹ� ����� �׽�Ʈ~").getBytes());
				fos.flush(); //�ٷ� ����
				System.out.println("----------> ���� ��� �Ϸ�");
				
				//�Է� : �����͸� ��� b ��ü�� ����� ������ �����ͺ��� �������� �ݺ�����
				//		b��ü�� ����� ���� �����ͺ��� ū ���� �ݺ����� ����
				byte[] b = new byte[100];
				fis.read(b);
				System.out.println("-----------> ���� �Է� ����");
				System.out.println(new String(b));
				System.out.println("-----------> ���� �Է� ����");
			}else {
				if(file.createNewFile()) {
					System.out.println("-----------> ���� ���� �Ϸ�!!");
				}
			}
		}catch(Exception e) {
			
		}

	}//main method

}//class
