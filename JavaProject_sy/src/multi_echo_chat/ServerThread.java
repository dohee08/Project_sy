package multi_echo_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerThread extends Thread{
	//Field
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	//Constructor
	public ServerThread(Socket socket) {
		try {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			System.out.println("-------> ServerThread �����Ϸ�~!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	public void run() {
		try {
			//2.3
			boolean flag = true;
			while(flag) {
				//���� �޽����� ���� ������ broadcasting
				String msg = dis.readUTF();
				
				if(!msg.equals("quit")) {
					//dos.writeUTF(msg);  //�ڱ� �ڽ����׸�
					//������ Ŭ���̾�Ʈ ��ο��� �޽��� ����
					broadCasting(msg);
				}else {
					flag = false;
				}
			}
			System.out.println("-------> ���� ����");
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** ������ Ŭ���̾�Ʈ ��ο��� �޽��� ���� **/
	public void broadCasting(String msg) {
		try {
			for(ServerThread st : MultiEchoServer.st_list) {
				st.dos.writeUTF(msg);
				System.out.println("st_list ========> 2,3");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}//class
