package simple_echo_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleEchoServer {
	//Field
	ServerSocket server;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	//Constructor
	public SimpleEchoServer() {
		try {
			server = new ServerSocket(7000);
			System.out.println("���� ���� ��~");
			
			socket = server.accept();
			System.out.println("���� --> Ŭ���̾�Ʈ ���� ����`");
			
			//��ü ���� ���� �߿�!
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			//2.3 Ŭ���̾�Ʈ�� ���� ���� �� �۽�  - �ݺ������� Ŭ���̾�Ʈ ���� ���� while
			while(true) {
				dos.writeUTF(dis.readUTF());
				System.out.println("2.3. ���� ----> Ŭ���̾�Ʈ");
			}
//			//2. Ŭ���̾�Ʈ�� ���� ����
//			String msg = dis.readUTF();
//			System.out.println("2. ������ ���� �Ϸ�");
			
//			//3. Ŭ���̾�Ʈ�� ����
//			dos.writeUTF(dis.readUTF());
//			System.out.println("3. ���� ----> Ŭ���̾�Ʈ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method
}
