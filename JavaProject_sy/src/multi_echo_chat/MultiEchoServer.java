package multi_echo_chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiEchoServer {
	//Field
	ServerSocket server;
	Socket socket;
	//공유할 객체이기 때문에 static으로 
	static ArrayList<ServerThread> st_list = new ArrayList<ServerThread>();
	
	//Constructor
	public MultiEchoServer() {
		try {
			server = new ServerSocket(7000);
			System.out.println("------> 서버 실행 중~");
			
			while(true) {	//클라이언트 여러개를 받기 위해서 계속 반복
				socket = server.accept();
				
				//독립된 socket 클래스에서 진행해야 함 -> main method에서 독립
				ServerThread st = new ServerThread(socket);  //쓰레드 형태로 독립, socket으로 연결
				st.start();  //독립 실행
				
				st_list.add(st);
				System.out.println("------> 현재 접속자 수 : " + st_list.size());
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
