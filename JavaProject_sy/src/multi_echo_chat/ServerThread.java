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
			System.out.println("-------> ServerThread 생성완료~!");
			
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
				//받은 메시지가 있을 때마다 broadcasting
				String msg = dis.readUTF();
				
				if(!msg.equals("quit")) {
					//dos.writeUTF(msg);  //자기 자신한테만
					//접속한 클라이언트 모두에게 메시지 전송
					broadCasting(msg);
				}else {
					flag = false;
				}
			}
			System.out.println("-------> 서버 종료");
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 접속한 클라이언트 모두에게 메시지 전송 **/
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
