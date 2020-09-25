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
			
			//���� ����
			chatUI();
			
			//Ŭ���̾�Ʈ�� ���۵Ǵ� �޽����� �׻� ���� �� �ֵ��� Thread ��ü�� ����
			ClientThread ct = new ClientThread(dis, content, input);
			ct.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Method
	public void chatUI() {
		JFrame jf = new JFrame("����ȭ��");
		JPanel jp = new JPanel();
		content = new JTextArea(50,50);
		JLabel label = new JLabel("�Է�");
		input = new JTextField(20);
		send = new JButton("����");
		Font font = new Font("���� ���", Font.BOLD, 12);
		
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
		
		//�̺�Ʈ �ڵ鷯 �߰�
		jf.addWindowListener(this);
		input.addActionListener(this);
		send.addActionListener(this);
		
	}
	/** ������ ���� �̺�Ʈ ó�� **/
	public void windowClosing(WindowEvent we) {
		try {
			dos.writeUTF("quit");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/** �׼� �̺�Ʈ ó�� **/
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj == input || obj == send) {
			String send_msg = input.getText().trim();
			
			if(send_msg.equals("")) {
				JOptionPane.showMessageDialog(null, "�޽����� �Է����ּ���");
				input.requestFocus();
			}else {
				try {
					//1. �޽��� �Է� �� ���������� ����~
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
