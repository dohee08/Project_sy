package multi_echo_chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiEchoServer {
	//Field
	ServerSocket server;
	Socket socket;
	//������ ��ü�̱� ������ static���� 
	static ArrayList<ServerThread> st_list = new ArrayList<ServerThread>();
	
	//Constructor
	public MultiEchoServer() {
		try {
			server = new ServerSocket(7000);
			System.out.println("------> ���� ���� ��~");
			
			while(true) {	//Ŭ���̾�Ʈ �������� �ޱ� ���ؼ� ��� �ݺ�
				socket = server.accept();
				
				//������ socket Ŭ�������� �����ؾ� �� -> main method���� ����
				ServerThread st = new ServerThread(socket);  //������ ���·� ����, socket���� ����
				st.start();  //���� ����
				
				st_list.add(st);
				System.out.println("------> ���� ������ �� : " + st_list.size());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	
	/** main method **/
	public static void main(String[] args) {
		new MultiEchoServer();
	}

}
