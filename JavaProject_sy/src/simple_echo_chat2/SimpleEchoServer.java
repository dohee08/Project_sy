package simple_echo_chat2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleEchoServer {
	//Field
	ServerSocket server;
	Socket socket;
//	DataInputStream dis;
//	DataOutputStream dos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	//Constructor
	public SimpleEchoServer() {
		try {
			server = new ServerSocket(7000);
			System.out.println("서버 실행중~~");
			
			socket = server.accept();
			
//			dis = new DataInputStream(socket.getInputStream());
//			dos = new DataOutputStream(socket.getOutputStream());
			
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			//2.3
			boolean flag = true;
			while(flag) {	//while문을 쓰지 앟으면 Connection reset -> 서버다운
//				String msg = dis.readUTF();
				MessageVO msgVO = (MessageVO)ois.readObject();
				if(!msgVO.getMsg().equals("quit")) {	////SocketException
//					dos.writeUTF(msg);	
					oos.writeObject(msgVO);
					System.out.println("2,3------->");
				}else {
					flag = false;
				}
			}
			System.out.println("서버 종료~~!!");
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method

	public static void main(String[] args) {
		new SimpleEchoServer();
	}

}
