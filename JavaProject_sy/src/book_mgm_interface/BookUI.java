package book_mgm_interface;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BookUI extends WindowAdapter implements ActionListener{
	//Field
	BookMgmSystem bms;
	Frame f;
	Panel menu_panel, content_panel, insert_panel, select_panel,
			update_panel, delete_panel;
	String[] names = {"            ���            ", "���", "����", "����", "����"};
	ArrayList<TextField> tf_list;
	ArrayList<TextField> tf_update_list; // = new ArrayList<TextField>();
	String[] form_names = {"ISBN","������","����","����"};
	TextField tf_update, tf_delete;
	int idx = -1;
	
	public static final int INSERT = 1;
	public static final int SELECT = 2;
	public static final int UPDATE = 3;
	public static final int DELETE = 4;
	
	//Constructor
	public BookUI() {
		bms = new BookMgmSystem();
		insert_panel = new Panel(new BorderLayout());
		select_panel = new Panel();
		update_panel = new Panel();
		delete_panel = new Panel();
		
		f = new Frame("�������� �ý���");
		menu_panel = new Panel(new GridLayout(5,1));
		menu_panel.setSize(100, 400);
		content_panel = new Panel();
		
		for(String name : names) {
			Button button = new Button(name);		
			menu_panel.add(button);
			button.addActionListener(this);
		}
		
		f.add(BorderLayout.WEST, menu_panel);
		f.add(BorderLayout.CENTER, content_panel);
		
		f.setSize(550,300);
		f.setVisible(true);
		
		f.addWindowListener(this);
	}
	
	//Method
	/** ������ ���� �̺�Ʈ **/
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	/** �׼� �̺�Ʈ, �޴� Ŭ�� �̺�Ʈ **/
	public void actionPerformed(ActionEvent e) {
		String menu = e.getActionCommand().trim();
		Object obj = e.getSource();
		
		if(menu.equals("���")) {		
			insertForm();			
		}else if(menu.equals("���")) {	
			selectForm();	
		}else if(menu.equals("����")) {
			updateForm();
		}else if(menu.equals("����")) {
			deleteForm();
		}else if(menu.equals("����")) {
			System.exit(0);
		}else if(menu.equals("����ϱ�")) {
			if(insertFormCheck()) {				
				BookVO book = new BookVO();
				ArrayList<String> data = new ArrayList<String>();
				
				for(TextField tf : tf_list) {
					data.add(tf.getText().trim());					
				}
				
				book.setIsbn(data.get(0));
				book.setTitle(data.get(1));
				book.setAuthor(data.get(2));
				book.setPrice(Integer.parseInt(data.get(3)));
				
				//���������ý��ۿ� ��� �۾�			
				if(bms.insert(book)) {
					JOptionPane.showMessageDialog(null,"��ϼ���~!!");
					for(TextField tf : tf_list) {
						tf.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null,"��Ͻ���~!!");
				}
			}	
		}else if(menu.equals("������")) {
			for(TextField tf : tf_list) {
				tf.setText("");
			}
		}else if(menu.equals("�����˻�") || obj == tf_update) {
			String isbn = tf_update.getText().trim();
			idx = bms.searchAddress(isbn);
			if(idx != -1) {
				//JOptionPane.showMessageDialog(null,"��������");
				updateOkForm();  //���� �� : ������, ���ڸ�, ������ ���� �Է� ��
			}else {
				JOptionPane.showMessageDialog(null,"�ش� �����Ͱ� �������� �ʽ��ϴ�.");
			}
		}else if(menu.equals("�����ϱ�")) {
			//1. ����������
			ArrayList<String> dataList = new ArrayList<String>();
			for(TextField tf : tf_update_list) {
				dataList.add(tf.getText().trim());
			}
			//BookVO ��ü ���� �� ������ �߰�
			BookVO book = new BookVO();
			book.setIsbn(dataList.get(0));
			book.setTitle(dataList.get(1));
			book.setAuthor(dataList.get(2));
			book.setPrice(Integer.parseInt(dataList.get(3)));
			
			System.out.println(book.getIsbn());
			System.out.println(book.getTitle());
			System.out.println(book.getAuthor());
			System.out.println(book.getPrice());
			
			//2. bms�� ���������� ����
			if(bms.update(idx, book)) {				
				JOptionPane.showMessageDialog(null, idx+"�����Ϸ�");
			}else {
				JOptionPane.showMessageDialog(null, "��������");
			}
			
		}else if(menu.equals("�������")) {
			
		}else if(menu.equals("�����˻�") || obj == tf_delete) {
			String isbn = tf_delete.getText().trim();
			idx = bms.searchAddress(isbn);
			
			if(idx != -1) {
				//���� ������ ���� �� ����ڿ��� �����ǻ縦 ���
				int choice = JOptionPane.showConfirmDialog(null, "������ ���� �Ͻðڽ��ϱ�?");
				if(choice == 0) {
					//���� ���� ---> �����Ͱ� �����ϴ� ������ ����  bms
					if(bms.delete(idx)) {
						JOptionPane.showMessageDialog(null, "�����Ϸ�~");
					}else {
						JOptionPane.showMessageDialog(null, "��������~");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "�ش絥���Ͱ� �������� �ʽ��ϴ�");
			}
//			JOptionPane.showMessageDialog(null, "�����˻�");
		}
		
	}//actionPerformed
	
	/** �г� Reset **/
	public void resetPanel() {
		insert_panel.setVisible(false);
		select_panel.setVisible(false);
		update_panel.setVisible(false);
		delete_panel.setVisible(false);
	}
	
	/** �г� ����Ī **/
	public void switchPanel(String pname) {
		resetPanel();
		
		if(pname.equals("���")) {
			insert_panel.setVisible(true);
		}else if(pname.equals("���")) {
			select_panel.setVisible(true);
		}else if(pname.equals("����")) {
			update_panel.setVisible(true);
		}else if(pname.equals("����")) {
			delete_panel.setVisible(true);
		}
	}
	
	/** �г� ����Ī **/
	public void switchPanel(int pname) {
		resetPanel();
		
		switch(pname) {
		case 1:
			insert_panel.setVisible(true);	break;
		case 2:
			select_panel.setVisible(true);	break;
		case 3:
			update_panel.setVisible(true);	break;
		case 4:
			delete_panel.setVisible(true);	break;
		}
	}		
	
	
	/** ������ ��ȿ�� üũ **/
	public boolean insertFormCheck() {
		boolean result = false;
		
		int i=0;
		for(TextField tf : tf_list) {
			if(tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, form_names[i]+"��(��) �Է����ּ���");
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
	
	
	/** ��� ȭ��  **/
	public void insertForm() {		
		switchPanel(BookUI.INSERT);
		insert_panel.removeAll();
		tf_list = new ArrayList<TextField>(4);
		
		String title = "===== ���������� ����ϴ� ȭ���Դϴ�  =====";				
		Panel label_panel = new Panel(new GridLayout(5,1));
		Panel tf_panel = new Panel(new GridLayout(5,1));
		Panel btn_panel = new Panel();
		Button btn_insert = new Button("����ϱ�");
		Button btn_reset = new Button("������");
		Label label_title = new Label(title);
		btn_panel.add(btn_insert);
		btn_panel.add(btn_reset);
				
		for(String name : form_names) {
			Label label = new Label(name);
			Panel panel = new Panel();
			panel.add(label);
			label_panel.add(panel);
			
			TextField tf = new TextField(30);
			Panel t_panel = new Panel();
			t_panel.add(tf);
			tf_panel.add(t_panel);
			tf_list.add(tf);
		}
				
		insert_panel.add(BorderLayout.NORTH, label_title);
		insert_panel.add(BorderLayout.WEST, label_panel);
		insert_panel.add(BorderLayout.CENTER, tf_panel);
		insert_panel.add(BorderLayout.SOUTH, btn_panel);
		
		content_panel.add(insert_panel);
		f.setVisible(true);
		
		btn_insert.addActionListener(this);
		btn_reset.addActionListener(this);
	}
	
	/** ��� ȭ��  **/
	public void selectForm() {
		ArrayList<BookVO> list = (ArrayList<BookVO>)bms.selectList();
		switchPanel(BookUI.SELECT);
		select_panel.removeAll();
		
		TextArea ta = new TextArea(15,40);
		if(list.size() != 0) {
			ta.append("*************** �������� �ý��� ***************\n");
			ta.append("��ȣ\tISBN\t������\t����\t����\n");
			ta.append("---------------------------------------------\n");
			int i=0;
			for(BookVO book : list) {
				ta.append((i+1)+"\t");
				ta.append(book.getIsbn()+"\t");
				ta.append(book.getTitle()+"\t");
				ta.append(book.getAuthor()+"\t");
				ta.append(book.getPrice()+"\n");
				i++;
			}
			ta.append("---------------------------------------------\n");	
		}else {
			ta.append("-- �����Ͱ� �������� �ʽ��ϴ�. ��Ϻ��� ������ �ּ��� --");
		}
		select_panel.add(ta);
		content_panel.add(select_panel);
		f.setVisible(true);
	}

	/** ����ȭ�� **/
	public void updateForm() {
		tf_update_list = new ArrayList<TextField>();
		switchPanel(BookUI.UPDATE);
		update_panel.removeAll();
				
		Panel update_top = new Panel();
		Label label = new Label(" ISBN ");
		tf_update = new TextField(20);
		Button tf_search = new Button("�����˻�");
		
		tf_update_list.add(tf_update);  //���� isbn TextField �߰�
		
		update_top.add(label);
		update_top.add(tf_update);
		update_top.add(tf_search);
		
		update_panel.setLayout(new BorderLayout());
		update_panel.add(BorderLayout.NORTH, update_top);	
				
		content_panel.add(update_panel);
		f.setVisible(true);
		
		tf_search.addActionListener(this); //�����˻� ��ư �̺�Ʈ ó�� ȣ��
		tf_update.addActionListener(this); //���� �ؽ�Ʈ�ʵ� �̺�Ʈ ó�� ȣ��
	}
	
	/** ���� ������ ��� �� **/
	public void updateOkForm() {
		String title = "+++++++ ���� �� �Դϴ� ++++++++";
		Panel update_bottom = new Panel();
		Panel label_panel = new Panel(new GridLayout(5,1));
		Panel tf_panel = new Panel(new GridLayout(5,1));
		Panel btn_panel = new Panel();
		
		Label update_title = new Label(title);
		Button btn_update_ok = new Button("�����ϱ�");
		Button btn_update_reset = new Button("�������");
		btn_panel.add(btn_update_ok);
		btn_panel.add(btn_update_reset);
		
		for(int i=1; i<form_names.length; i++) {
			Label label = new Label(form_names[i]);
			Panel p_label = new Panel();
			p_label.add(label);
			label_panel.add(p_label);
			
			TextField tf = new TextField(30);
			Panel p_tf = new Panel();
			p_tf.add(tf);
			tf_panel.add(p_tf);
			
			tf_update_list.add(tf); //���� ������, ����, ���� TextField �߰�
		}
		
		update_bottom.setLayout(new BorderLayout());
		update_bottom.add(BorderLayout.NORTH, update_title);
		update_bottom.add(BorderLayout.WEST, label_panel);
		update_bottom.add(BorderLayout.CENTER, tf_panel);
		update_bottom.add(BorderLayout.SOUTH, btn_panel);
		
		update_panel.add(BorderLayout.CENTER, update_bottom);
		content_panel.add(update_panel);
		f.setVisible(true);
		
		//�����ϱ�, ������� �̺�Ʈ �ڵ鷯 ȣ��
		btn_update_ok.addActionListener(this);
		btn_update_reset.addActionListener(this);
		
	}
	
	
	/** ����ȭ�� **/
	public void deleteForm() {
	      switchPanel(BookUI.DELETE);
	      delete_panel.removeAll();
	      
	      //1. ������ ���� isbn �Է� TextField�� ������ư �߰�
	      Panel p = new Panel();
	      String title = "������ ISBN(������ȣ)�� �Է����ּ���";
	      Label label_title = new Label(title);
	      Label label = new Label("ISBN");
	      tf_delete = new TextField(20);
	      Button del_search = new Button("�����˻�");
	      
	      p.add(label);    p.add(tf_delete);   p.add(del_search);
	      
	      delete_panel.setLayout(new BorderLayout());
	      delete_panel.add(BorderLayout.NORTH, label_title);
	      delete_panel.add(BorderLayout.CENTER, p);
	      
	      content_panel.add(delete_panel);
	      f.add(content_panel);
	      f.setVisible(true);
	      
	      del_search.addActionListener(this); //�����˻� ��ư�� �̺�Ʈ ó�� ȣ��
	      tf_delete.addActionListener(this); //�ؽ�Ʈ�ʵ忡�� �߻��� �̺�Ʈ ó�� ȣ��
	      //2. isbn �Է��� ���� ��ư Ŭ�� �� ������ ���� �� ���� ����,
	      //     ������ �������� �ʴ� ��� �޽��� ���
	      
	   }
	
}//class



















