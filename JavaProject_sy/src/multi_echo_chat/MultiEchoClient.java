package multi_echo_chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiEchoClient extends WindowAdapter implements ActionListener{
	//Field
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	JTextArea content;
	JTextField input;
	JButton send;
	
	//Constructor
	public MultiEchoClient() {
		try {
			socket = new Socket("localhost", 7000);
			
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			//순서 주의
			chatUI();
			
			//클라이언트로 전송되는 메시지를 항상 읽을 수 있도록 Thread 객체로 생성
			ClientThread ct = new ClientThread(dis, content, input);
			ct.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method
	public void chatUI() {
		JFrame jf = new JFrame("메인화면");
		JPanel jp = new JPanel();
		content = new JTextArea(50,50);
		JLabel label = new JLabel("입력");
		input = new JTextField(20);
		send = new JButton("전송");
		Font font = new Font("맑은 고딕", Font.BOLD, 12);
		
		content.setEditable(false);
		content.setBackground(Color.CYAN);
		
		label.setFont(font);
		content.setFont(font);
		input.setFont(font);
		send.setFont(font);
		
		jp.add(label); jp.add(input); jp.add(send);
		jf.add(content, BorderLayout.CENTER);
		jf.add(jp, BorderLayout.SOUTH);
		
		jf.setSize(350,300);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		//이벤트 핸들러 추가
		jf.addWindowListener(this);
		input.addActionListener(this);
		send.addActionListener(this);
		
	}
	/** 윈도우 종료 이벤트 처리 **/
	public void windowClosing(WindowEvent we) {
		try {
			dos.writeUTF("quit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/** 액션 이벤트 처리 **/
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj == input || obj == send) {
			String send_msg = input.getText().trim();
			
			if(send_msg.equals("")) {
				JOptionPane.showMessageDialog(null, "메시지를 입력해주세요");
				input.requestFocus();
			}else {
				try {
					//1. 메시지 입력 후 선택적으로 전송~
					dos.writeUTF(send_msg);
					System.out.println("========> 1");
					
					input.setText("");
					input.requestFocus();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**main method **/
	public static void main(String[] args) {
		new MultiEchoClient();
	}
	

}
