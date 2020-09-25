package multi_echo_chat;

import java.io.DataInputStream;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientThread extends Thread{
	//Field
	DataInputStream dis;
	JTextArea content;
	JTextField input;
	
	//Constructor
	public ClientThread(DataInputStream dis, JTextArea content, JTextField input) {
		this.dis = dis;
		this.content = content;
		this.input = input;
	}
	
	//Method
	public void run() {
		try {
			while(true) {	//계속 듣기(무한 반복)
				//4.다른 클라이언트의 메시지도 수신
				content.append("수신 : " + dis.readUTF() + "\n");
				System.out.println("========> 4");
				
				input.setText("");
				input.requestFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
