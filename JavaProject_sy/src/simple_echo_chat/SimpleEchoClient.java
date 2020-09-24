package simple_echo_chat;

import java.awt.BorderLayout;
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

public class SimpleEchoClient extends WindowAdapter implements ActionListener {
	//Field
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	//UI
	JFrame jf;
	JPanel jp;
	JTextArea jta;
	JTextField jtf;
	JButton send;
	
	//Constructor
	public SimpleEchoClient() {
		try {
			socket = new Socket("localhost", 7000);
			System.out.println("클라이언트 --> 서버 접속 성공~");
			
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			
			//UI
			jf = new JFrame("Echo Client");
			jp = new JPanel();
			jta = new JTextArea(50,50);
			jtf = new JTextField(15);
			send = new JButton("전송");
			
			jp.add(new JLabel("입력 > "));  jp.add(jtf);	jp.add(send);
			jf.add(jta, BorderLayout.CENTER);
			jf.add(jp, BorderLayout.SOUTH);
			
			jf.setSize(300,300);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			
			jtf.requestFocus();
			
			//이벤트 핸들러
			jf.addWindowListener(this);
			jtf.addActionListener(this);
			send.addActionListener(this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method
	public void winodwClosing(WindowEvent e) {
		System.exit(0);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jtf || obj == send) {
			try {
				String send_msg = jtf.getText().trim();
				if(send_msg.equals("")) {
					JOptionPane.showMessageDialog(null, "메시지를 입력해주세요");
					jtf.requestFocus();
				}else {
					//1. 서버로 데이터 송신
					dos.writeUTF(send_msg);
					jtf.setText("");
					System.out.println("1. 클라이언트 --> 서버");
					
					//4. 서버의 메시지 수신
					String msg = dis.readUTF();
					jta.append("수신 메시지: " + msg +"\n");
					System.out.println("4. msg --> " + msg);
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
}
