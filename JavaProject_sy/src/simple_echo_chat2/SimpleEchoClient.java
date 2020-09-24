package simple_echo_chat2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SimpleEchoClient extends WindowAdapter implements ActionListener{
	//Field
	Socket socket;
//	DataInputStream dis;
//	DataOutputStream dos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	JFrame jf;
	JPanel jp;
	JTextArea jta;
	JTextField jtf;
	JButton send;
	
	//Constructor
	public SimpleEchoClient() {
		try {
			socket = new Socket("localhost", 7000);
//			dos = new DataOutputStream(socket.getOutputStream());
//			dis = new DataInputStream(socket.getInputStream());
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			//화면구성
			jf = new JFrame("체크 클라이언트");		
			jp = new JPanel();
			jta = new JTextArea(50,50);
			jtf = new JTextField(15);
			send = new JButton("send");
			
			Font font = new Font("맑은 고딕", Font.BOLD, 12);
			jta.setFont(font);
			jtf.setFont(font);
			send.setFont(font);
			
			jta.setEditable(false);
			jta.setBackground(Color.LIGHT_GRAY);
			
			jp.add(new JLabel("입력 >"));
			jp.add(jtf); jp.add(send);
			jf.add(jta, BorderLayout.CENTER);
			jf.add(jp, BorderLayout.SOUTH);
			
			jf.setSize(350,300);
			jf.setLocationRelativeTo(null);
			jf.setVisible(true);
			
			//이벤트 핸들러
			jf.addWindowListener(this);
			jtf.addActionListener(this);
			send.addActionListener(this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method
	/** 윈도우 종료 이벤트 처리 **/
	public void windowClosing(WindowEvent we) {
		//SocketException
		try {
//			dos.writeUTF("quit");
			MessageVO msgVO = new MessageVO();
			msgVO.setMsg("quit");
			oos.writeObject(msgVO);
			System.out.println("종료 메시지 전달----");
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/** 액션 이벤트 처리 **/
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj == jtf || obj == send) {
			String send_msg = jtf.getText().trim();
			if(send_msg.equals("")) {
				JOptionPane.showMessageDialog(null, "메시지를 입력해주세요");
				jtf.requestFocus();
			}else {
				try {
					//1. 서버로 메시지 송신 : MessageVO 객체 생성 및 전송 (이름, 메시지)
//					dos.writeUTF(send_msg);
					MessageVO msgVO = new MessageVO();
					msgVO.setName("홍길동");
					msgVO.setMsg(send_msg);
					oos.writeObject(msgVO); //서버로 msgVO 전송
					System.out.println("1.-------->");
					
					//4. 서버의 메시지 수신
					msgVO = (MessageVO)ois.readObject();
					jta.append(msgVO.getName() + " : " + msgVO.getMsg() + "\n");
					System.out.println("4.-------->");
					
					jtf.setText("");
					jtf.requestFocus();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	/** 메인 메소드 **/
	public static void main(String[] args) {
		new SimpleEchoClient();
	}

}
