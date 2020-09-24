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
			System.out.println("서버 실행 중~");
			
			socket = server.accept();
			System.out.println("서버 --> 클라이언트 연결 성공`");
			
			//객체 생성 순서 중요!
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			//2.3 클라이언트로 부터 수신 후 송신  - 반복적으로 클라이언트 정보 수신 while
			while(true) {
				dos.writeUTF(dis.readUTF());
				System.out.println("2.3. 서버 ----> 클라이언트");
			}
//			//2. 클라이언트로 부터 수신
//			String msg = dis.readUTF();
//			System.out.println("2. 데이터 수신 완료");
			
//			//3. 클라이언트로 전송
//			dos.writeUTF(dis.readUTF());
//			System.out.println("3. 서버 ----> 클라이언트");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method
}
