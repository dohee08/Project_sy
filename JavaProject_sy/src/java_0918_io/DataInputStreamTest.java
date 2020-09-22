package java_0918_io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class DataInputStreamTest {

	public static void main(String[] args) {
		File file = null;
		FileInputStream fis = null;
		DataInputStream dis = null;
		
		try {
			file = new File("C:/Users/User/eclipse-workspace/sample.dat");
			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			
			String str = dis.readUTF();
			int i = dis.readInt();
			double d = dis.readDouble();
			boolean b = dis.readBoolean();
			
			System.out.println("String ---> "+str);
			System.out.println("Int ---> " + i);
			System.out.println("Double ---> "+ d);
			System.out.println("Boolean ---> "+ b);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
