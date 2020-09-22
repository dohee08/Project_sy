package java_0918_io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class DataOutputStreamTest {

	public static void main(String[] args) {
		//1. ��Ʈ��ũ�� ���� ������ File, �������� ������ �����͸� �����ϱ� ���� Ȯ���� 'dat'
		File file = new File("C:/Users/User/eclipse-workspace/sample.dat");
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			if(file.exists()) {
				//2. File - OutputStream�� ����
				fos = new FileOutputStream(file);
				//3. OutputStream - DataoutputStream ����
				dos = new DataOutputStream(fos);
				
				//4. DataOutputStream�� �پ��� ������ ���� ����
				dos.writeUTF("ȫ�浿"); 		//��������
				dos.writeInt(100);	  		//���� 100
				dos.writeDouble(100.345); 	//�Ǽ� 100.345
				dos.writeBoolean(true);		//booleanŸ�� ����
				
				System.out.println("������ ���� �Ϸ�~~~");
			}else {
				if(file.createNewFile()) System.out.println("���� ���� �Ϸ�~");
			}
		} catch (Exception e) {
			//Exception�߻� �� Stack�� ���
			e.printStackTrace();
		}
	}

}
