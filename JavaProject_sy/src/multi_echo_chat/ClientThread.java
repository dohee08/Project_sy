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
			while(true) {	//��� ���(���� �ݺ�)
				//4.�ٸ� Ŭ���̾�Ʈ�� �޽����� ����
				content.append("���� : " + dis.readUTF() + "\n");
				System.out.println("========> 4");
				
				input.setText("");
				input.requestFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
