package socre_mgm_interface2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ScoreUI {
	//Field
	ScoreEvent score_event = new ScoreEvent(this); //�̺�Ʈ ó�� ��ü ����
	//ScoreEvent���� ScoreUI Ŭ������ �ּҸ� ������ �ڱ� ��ó�� �̿��� �� ����
	ScoreMgmSystem sms;
	Frame f;
	Panel intro_panel, menu_panel, content_panel, insert_panel, 
			select_panel, update_panel, delete_panel, update_bottom;
	String[] menu_names = {"		��� 		", "��ȸ", "����", "����", "����"};
	String[] form_names = {"�л���", "����", "����", "����"};
	ArrayList<TextField> tf_insert_list; //��ϸ޴� ���� �� ���ο� ���� �������� ���� ���� �� ����
	ArrayList<TextField> tf_update_list; //�����޴� ���� �� ���ο� ���� �������� ���� ���� �� ����
	TextField tf_update, tf_delete, tf_insert_last, tf_update_last;
	JTextField tf_id;
	JPasswordField tf_pass;
	Button update_search, delete_search;
	int idx;
	
	public static final int INSERT = 1;
	public static final int SELECT = 2;
	public static final int UPDATE = 3;
	public static final int DELETE = 4;
	
	//JTable
	Object[] columns = {"��ȣ","�й�", "�л���", "����", "����", "����", "����", "���"};
	DefaultTableModel model = new DefaultTableModel(columns, 0);
	Object[] row = new Object[8];
	JTable table = new JTable(model);
	
	//Constructor
	public ScoreUI() {
		//��Ʈ�� ������ ����
		sms = new ScoreMgmSystem();
		f = new Frame("���������ý���");
		intro_panel = new Panel(new BorderLayout());
		ImageIcon img = new ImageIcon("Images/logo_img.png");
		JLabel logo  = new JLabel(img);
		
		Panel login_panel = new Panel();
		Label label_id = new Label("���̵�");
		Label label_pass = new Label("�н�����");
		tf_id = new JTextField(10);
		tf_pass = new JPasswordField(10);
		JButton btn_login = new JButton("�α���");
		
		login_panel.add(label_id);
		login_panel.add(tf_id);
		login_panel.add(new Label());
		login_panel.add(label_pass);
		login_panel.add(tf_pass);
		login_panel.add(btn_login);
			
		intro_panel.add(BorderLayout.NORTH, logo);
		intro_panel.add(BorderLayout.CENTER, login_panel);
		f.add(intro_panel);
		f.setSize(500,400);
		f.setLocationRelativeTo(null); //ȭ���� ��� ��� �޼ҵ�
		f.setVisible(true);
		
		f.addWindowListener(score_event);
		btn_login.addActionListener(score_event);
		tf_pass.addActionListener(score_event);
	}
	public void init() {	
//		selectForm(); // �α��� ���� �� ���ȭ���� �⺻�������� ������ -> null point exception : ��ü ���� �ϱ� ���� ���� ȣ�� �Ǿ��� ����
		
		menu_panel = new Panel(new GridLayout(1,5)); //panel�� �⺻ ���̾ƿ� FlowLayout --> GridLayout
		content_panel = new Panel();
		insert_panel = new Panel();
		select_panel = new Panel();
		update_panel = new Panel();
		delete_panel = new Panel();
		update_bottom = new Panel(new BorderLayout());
		
		//�޴���ư ���� �� �̺�Ʈ �߰�
		for(String name : menu_names) {
			Button menu = new Button(name);
			menu_panel.add(menu);
			menu.addActionListener(score_event);
		}
		
		f.add(BorderLayout.NORTH, menu_panel);
		f.add(BorderLayout.CENTER, content_panel);
		insertForm(); //�α��� ���� �� ���ȭ���� �⺻�������� ������
//		f.setSize(500,400);
//		f.setVisible(true);
		
		f.addWindowListener(score_event);
	}
	//Method
	
	
	/** �α��� ó�� �޼ҵ� **/
	public void loginProc() {
		//�α��� ó�� ����~~
		String id = tf_id.getText().trim();
		String pass = tf_pass.getText().trim();
		
		if(id.equals("")) {
			JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���");
			tf_id.requestFocus();
		}else if(pass.equals("")) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���");
			tf_pass.requestFocus();
		}else {
			//�α��� ó�� : ���̵�, �н����� �� = test/1234
			String d_id = "test";
			String d_pass = "1234";
			if(d_id.equals(id) && d_pass.equals(pass)) {
				JOptionPane.showMessageDialog(null, "�α��� ����~");	
				intro_panel.setVisible(false);
				init();
			}else {
				JOptionPane.showMessageDialog(null, "���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�");
				tf_id.setText(""); tf_pass.setText("");
				tf_id.requestFocus();
			}
			
//			if(d_id.equals(id)) {
//				//�н����� ��
//				if(d_pass.equals(pass)) {
//					//�α��� ����
//					JOptionPane.showMessageDialog(null, "�α��� ����~");
//				}else {
//					JOptionPane.showMessageDialog(null, "�н����尡 ��ġ���� �ʽ��ϴ�");
//					tf_pass.setText("");
//					tf_pass.requestFocus();
//				}
//			}else {
//				JOptionPane.showMessageDialog(null, "���̵� ��ġ���� �ʽ��ϴ�");
//				tf_id.setText("");
//				tf_id.requestFocus();
//			}
		}
		
//		JOptionPane.showMessageDialog(null, "�α��� ����~");
		
	}//loginProc Method
	
	/** ��� ó�� �޼ҵ� **/
	public void insertProc() {
		//������ ��ȿ��üũ > �����͸� VO ���� > ����ý��ۿ� ����
		if(insertFormCheck()) {
			//1. �����͸� VO ���� 
			ArrayList<String> dataList = new ArrayList<String>();
			for(TextField tf : tf_insert_list) {
				dataList.add(tf.getText().trim());
			}
			ScoreVO score = new ScoreVO();
			Random rd = new Random();
			score.setSno("S_"+rd.nextInt(10000));
//			score.setSno(String.valueOf(rd.nextInt(10000))); //�й��� �ڵ� ���� �� �Է�(4�ڸ� ��)
			score.setName(dataList.get(0));
			score.setKor(Integer.parseInt(dataList.get(1)));
			score.setEng(Integer.parseInt(dataList.get(2)));
			score.setMath(Integer.parseInt(dataList.get(3)));
			
			//2. ����ý��ۿ� ����
			if(sms.insert(score)) {
				JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�");
				for(TextField tf : tf_insert_list) {
					tf.setText("");
				}
			}else {
				JOptionPane.showMessageDialog(null, "����� ���еǾ����ϴ�");
			}
		}
//		for(TextField tf : tf_insert_list) {
//			JOptionPane.showMessageDialog(null, tf.getText());
//		}
	}
	/** ���� �˻� ó�� �޼ҵ� **/
	public void updateSearchProc() {
		String sno = tf_update.getText().trim();
		if(sno.equals("")) {
			//TextField�� �����Ͱ� ���� X
			JOptionPane.showMessageDialog(null, "�й��� �Է����ּ���");
			tf_update.requestFocus();
		}else {
			//TextField�� �����Ͱ� ���� O
			//�˻� ���� --> sms �ý��ۿ��� �ش絥���� ��/�� Ȯ��
			idx = sms.searchSno(sno);
//			System.out.println("idx ==========>>" + idx);
			if(idx != -1) {
				//sms�� �ش� �й��� �л����� ��������
				ScoreVO score = sms.selectScore(idx);
				//�л����� �����ϴ� ��� > ���� ����
				updateOKForm(score);					
			}else {
//				JOptionPane.showMessageDialog(null, "�л��� ������ �������� �ʽ��ϴ�");
				updateFailForm();
			}
//			JOptionPane.showMessageDialog(null, idx);
		}
	}
	/** ���� ó�� �޼ҵ� **/
	public void updateProc() {
		JOptionPane.showMessageDialog(null, "�����Ϸ�");
		
		ArrayList<String> dataList = new ArrayList<String>();
		for(TextField tf : tf_update_list) {
			dataList.add(tf.getText().trim());
		}
		ScoreVO score = new ScoreVO();
		score.setSno(dataList.get(0));
		score.setName(dataList.get(1));
		score.setKor(Integer.parseInt(dataList.get(2)));
		score.setEng(Integer.parseInt(dataList.get(3)));
		score.setMath(Integer.parseInt(dataList.get(4)));

		if(sms.update(idx, score)) {
			//�����Ϸ�
			JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
			for(TextField tf : tf_update_list) {
				tf.setText("");
			}
//			selectForm(); //��ȸȭ�� ȣ��
			selectFormTable();
		}else {
			//��������
			JOptionPane.showMessageDialog(null, "���� ����");
		}
		
	}
	/** ���� ó�� �޼ҵ� **/
	public void deleteProc() {
		//��������
		String sno = tf_delete.getText().trim();
		if(sno.equals("")) {
			//TextField�� �����Ͱ� ���� X
			JOptionPane.showMessageDialog(null, "�й��� �Է��� �ּ���");
			tf_delete.requestFocus();
		}else {
			//TextField�� �����Ͱ� ���� O
			idx = sms.searchSno(sno);
			if(idx != -1) {
				//sms�� ������ ���� O
				int choice = JOptionPane.showConfirmDialog(null, "������ ������ �����Ͻðڽ��ϱ�?");
				//��(0), �ƴϿ�(1), ���(2)
				if(choice == 0) {
					if(sms.delete(idx)) {
						//sms���� ��������
						JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�");
//						selectForm(); //��ȸȭ�� ȣ��
						selectFormTable();
					}else {
						//sms���� ��������
						JOptionPane.showMessageDialog(null, "������ ���еǾ����ϴ�");
					}
				}
			}else {
				//sms�� ������ ���� X
				JOptionPane.showMessageDialog(null, "�л��� ������ �������� �ʽ��ϴ�");
			}
		}
	}
	
	/** ��� �޴��� �гε��� ��Ȱ�� **/
	public void resetMenuPanel() {
		insert_panel.setVisible(false);
		select_panel.setVisible(false);
		update_panel.setVisible(false);
		delete_panel.setVisible(false); 
	}
	/** �޴� ���ý� �г� ����Ī **/
	public void switchPanel(int menu) {
		//��� �޴��� �г��� ��Ȱ��ȭ
		resetMenuPanel();
		//menu 1(���), 2(��ȸ), 3(����), 4(����)
		switch(menu) {
		case 1:
			insert_panel.setVisible(true); break;
		case 2:
			select_panel.setVisible(true); break;
		case 3:
			update_panel.setVisible(true); break;
		case 4:
			delete_panel.setVisible(true); break;
		}
	}
	/** ���� ��� ȭ�� ��ȿ�� üũ **/
	public boolean insertFormCheck() {
		boolean result = false;
		
		int i=0;
		for(TextField tf : tf_insert_list) {
			if(tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, form_names[i]+"�� �Է����ּ���");
				tf.requestFocus();
				result = false;
				break;
			}else {
				result = true;
			}
			i++;
		}
		
		return result;
	}
	
	/** ���� ��� ȭ�� **/
	public void insertForm() {
		switchPanel(ScoreUI.INSERT);   //1���ٴ� static ����� ó���� �ϴ� ���� ȿ����
		insert_panel.removeAll();
		
		tf_insert_list = new ArrayList<TextField>();
		
		//��������� ���� : �й�, �л���, ����, ���� , ����
		String title = "-- ������ ����ϴ� ȭ���Դϴ� --";
		Label title_label = new Label(title);
		Panel label_panel = new Panel(new GridLayout(5,1));
		Panel tf_panel = new Panel(new GridLayout(5,1));
		Panel btn_panel = new Panel();
		Button btn_insert = new Button("��ϿϷ�");
		Button btn_reset = new Button("������");
		btn_panel.add(btn_insert); btn_panel.add(btn_reset);
		
		for(String name : form_names) {
			Panel p1 = new Panel(); //label
			Panel p2 = new Panel(); //tf
			Label label = new Label(name);
			TextField tf = new TextField(30);
			p1.add(label); 		label_panel.add(p1);
			p2.add(tf);			tf_panel.add(p2);
			
			tf_insert_list.add(tf);  //��������� ������ tf ��ü�� ��ü ���� ����
		}
		
		insert_panel.setLayout(new BorderLayout());
		insert_panel.add(BorderLayout.NORTH, title_label);
		insert_panel.add(BorderLayout.WEST, label_panel);
		insert_panel.add(BorderLayout.CENTER, tf_panel);
		insert_panel.add(BorderLayout.SOUTH, btn_panel);
		
		content_panel.add(insert_panel);
//		f.add(BorderLayout.CENTER, content_panel);
		
		f.setVisible(true);
		
		btn_insert.addActionListener(score_event);
		btn_reset.addActionListener(score_event);
		tf_insert_last = tf_insert_list.get(tf_insert_list.size()-1);
		tf_insert_last.addActionListener(score_event);
	}
	
	/** JTable�� �߰��Ǵ� row ���� **/
	public void createRow() {
		ArrayList<ScoreVO> list = sms.selectList();
		model.setNumRows(0);
		
		int no = 1;
		for(ScoreVO score: list) {
			if(score != null) {
				row[0] = no;
				row[1] = score.getSno();  //�й�
				row[2] = score.getName(); //�л���
				row[3] = score.getKor();  //��������
				row[4] = score.getEng();  //��������
				row[5] = score.getMath(); //��������
				row[6] = score.getTot();  //����
				row[7] = score.getAvg();  //���
				
				model.addRow(row);
				no++;
			}
			table.repaint(); //���ŵ� list�� �ݿ��ϱ� ���� ���
		}
		model.fireTableDataChanged();			
	}
	
	/** JTable�� �̿��Ͽ� ��ȸȭ�� ���� **/
	public void selectFormTable() {
		switchPanel(ScoreUI.SELECT);
		select_panel.removeAll();
		
		createRow();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(SwingConstants.CENTER); //�� ��� ����
//		TableColumnModel col_model = table.getColumnModel();
//		col_model.getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setPreferredWidth(100); //��ȣ �÷� ũ��
		table.getColumnModel().getColumn(1).setPreferredWidth(100); //�й� �÷� ũ��
		
		table.getColumn("��ȣ").setCellRenderer(cell);
		table.getColumn("�й�").setCellRenderer(cell);
		table.getColumn("�л���").setCellRenderer(cell);
		table.getColumn("����").setCellRenderer(cell);
		table.getColumn("����").setCellRenderer(cell);
		table.getColumn("����").setCellRenderer(cell);
		table.getColumn("����").setCellRenderer(cell);
		table.getColumn("���").setCellRenderer(cell);
		
		JScrollPane scroll_pane = new JScrollPane(table);
		scroll_pane.setBounds(0, 100, 450, 370);
		
		select_panel.setLayout(new BorderLayout());
		select_panel.add(BorderLayout.NORTH, new Label("-- ���� ��ȸ ��� --"));
		select_panel.add(scroll_pane);
		
		content_panel.add(select_panel);
		f.add(content_panel);
		f.setSize(500,400);
		f.setVisible(true);
	}
	
	/** ���� ��ȸ ȭ�� **/
	public void selectForm() {
		switchPanel(ScoreUI.SELECT);
		select_panel.removeAll();
		
		//�����Ͱ� �����ϴ� ��쿡�� ��ȸ�� �����ϵ��� �մϴ�
		ArrayList<ScoreVO> list = sms.selectList();
		TextArea ta = new TextArea(15,60);
		
		if(list.size() > 0) {
			//��ȸȭ�� ���� select_panel.add(label);
			ta.append("==== ���� ��ȸ ��� ===\n");
			ta.append("��ȣ\t�й�\t�̸�\t����\t����\t����\t����\t���\n");
			ta.append("-------------------------------------------------------------\n");	
			int no = 1;
			for(ScoreVO score : list) {
				ta.append(no + "\t");
				ta.append(score.getSno() +"\t");
				ta.append(score.getName() +"\t");
				ta.append(score.getKor() +"\t");
				ta.append(score.getEng() +"\t");
				ta.append(score.getMath() +"\t");
				ta.append(score.getTot() +"\t");
				ta.append(Math.round(score.getAvg()) +"\n");
				no++;
			}
			ta.append("-------------------------------------------------------------\n");	
			
		}else {
			ta.append("--v�����Ͱ� �������� �ʽ��ϴ�. ��Ϻ��� �������ּ���~ --");
//			JOptionPane.showMessageDialog(null, "�����Ͱ� �������� �ʽ��ϴ�. ��Ϻ��� �������ּ���~");
		}
		
		select_panel.add(ta);		
		content_panel.add(select_panel);
		
		f.setVisible(true);
	}
	/** ���� ���� ȭ�� **/
	public void updateForm() {
		tf_update_list = new ArrayList<TextField>();
		
		switchPanel(ScoreUI.UPDATE);
		update_panel.removeAll();
		
		//1. �й� �˻� �� ���� �� �˻���ư�� �̺�Ʈ �߰�
		Panel update_top = new Panel(new BorderLayout());
		Panel search_panel = new Panel();
		String title = "-- ������ �л��� �й��� �Է����ּ��� --";
		Label title_label = new Label(title);
		Label label = new Label("�й�");
		tf_update = new TextField(20);
		update_search = new Button("�˻�"); //Object�� �ּҷ� ActionPerformed
		
		tf_update_list.add(tf_update); //������ ������ �й�
		
		search_panel.add(label);
		search_panel.add(tf_update);
		search_panel.add(update_search);
		
		update_top.add(BorderLayout.NORTH, title_label);
		update_top.add(BorderLayout.CENTER, search_panel);
		
		update_panel.setLayout(new BorderLayout());
		update_panel.add(BorderLayout.NORTH,update_top);		
		content_panel.add(update_panel);		
		f.setVisible(true);
		
		tf_update.addActionListener(score_event);
		update_search.addActionListener(score_event);
	}
	
	/** ���� ������ ��� �� : �л���, ����, ����, ���� **/
	public void updateOKForm() {
		
	}
	
	/** ���� ������ ��� �� : �л���, ����, ����, ���� **/
	public void updateOKForm(ScoreVO score) {
		update_bottom.removeAll();
		
		Panel label_panel = new Panel(new GridLayout(4,1));
		Panel tf_panel = new Panel(new GridLayout(4,1));
		Panel btn_panel = new Panel();
		Button btn_update = new Button("�����Ϸ�");
		Button btn_reset = new Button("�������");
		btn_panel.add(btn_update);
		btn_panel.add(btn_reset);
		
		String[] data_list = new String[4];
		data_list[0] = score.getName();
		data_list[1] = String.valueOf(score.getKor());
		data_list[2] = String.valueOf(score.getEng());
		data_list[3] = String.valueOf(score.getMath());
		
		for(int i=0; i<form_names.length; i++) {
			Panel p1 = new Panel();
			Panel p2 = new Panel();
			Label label = new Label(form_names[i]);
			TextField tf = new TextField(20);
			tf.setText(data_list[i]);
			p1.add(label); 	p2.add(tf);
			label_panel.add(p1);
			tf_panel.add(p2);
			
			tf_update_list.add(tf);
		}
		
		update_bottom.add(BorderLayout.NORTH, new Label());
		update_bottom.add(BorderLayout.WEST, label_panel);
		update_bottom.add(BorderLayout.CENTER, tf_panel);
		update_bottom.add(BorderLayout.SOUTH, btn_panel);
		
		update_panel.add(BorderLayout.CENTER, update_bottom);
		content_panel.add(update_panel);
		f.setVisible(true);
		
		btn_update.addActionListener(score_event);
		btn_reset.addActionListener(score_event);
		tf_update_last = tf_update_list.get(tf_update_list.size()-1);
		tf_update_last.addActionListener(score_event);
		
	}
	/** �������� **/
	public void updateFailForm() {
		update_bottom.removeAll();
		update_bottom.add(BorderLayout.NORTH, new Label());
		update_bottom.add(BorderLayout.CENTER, new Label("-- �л� ������ �������� �ʽ��ϴ� --"));
		
		update_panel.add(BorderLayout.CENTER, update_bottom);
		content_panel.add(update_panel);
		f.setVisible(true);
	}
	
	/** ���� ���� ȭ�� **/
	public void deleteForm() {
		switchPanel(ScoreUI.DELETE);
		delete_panel.removeAll();
		
		Panel del_panel = new Panel(new BorderLayout());
		Panel p = new Panel();
		String title = "-- ������ �л��� �й��� �Է����ּ��� --";
		Label title_label = new Label(title);
		Label label = new Label("�й�");
		tf_delete = new TextField(20);
		delete_search = new Button("�˻�");
		p.add(label);  p.add(tf_delete);  p.add(delete_search);
		
		del_panel.add(BorderLayout.NORTH, title_label);
		del_panel.add(BorderLayout.CENTER, p);
		
		delete_panel.add(del_panel);
		content_panel.add(delete_panel);		
		f.setVisible(true);
		
		tf_delete.addActionListener(score_event);
		delete_search.addActionListener(score_event);
	}
}//class
