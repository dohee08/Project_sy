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
	String[] names = {"            등록            ", "출력", "수정", "삭제", "종료"};
	ArrayList<TextField> tf_list;
	ArrayList<TextField> tf_update_list; // = new ArrayList<TextField>();
	String[] form_names = {"ISBN","도서명","저자","가격"};
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
		
		f = new Frame("도서관리 시스템");
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
	/** 윈도우 종료 이벤트 **/
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	/** 액션 이벤트, 메뉴 클릭 이벤트 **/
	public void actionPerformed(ActionEvent e) {
		String menu = e.getActionCommand().trim();
		Object obj = e.getSource();
		
		if(menu.equals("등록")) {		
			insertForm();			
		}else if(menu.equals("출력")) {	
			selectForm();	
		}else if(menu.equals("수정")) {
			updateForm();
		}else if(menu.equals("삭제")) {
			deleteForm();
		}else if(menu.equals("종료")) {
			System.exit(0);
		}else if(menu.equals("등록하기")) {
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
				
				//도서관리시스템에 등록 작업			
				if(bms.insert(book)) {
					JOptionPane.showMessageDialog(null,"등록성공~!!");
					for(TextField tf : tf_list) {
						tf.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null,"등록실패~!!");
				}
			}	
		}else if(menu.equals("등록취소")) {
			for(TextField tf : tf_list) {
				tf.setText("");
			}
		}else if(menu.equals("수정검색") || obj == tf_update) {
			String isbn = tf_update.getText().trim();
			idx = bms.searchAddress(isbn);
			if(idx != -1) {
				//JOptionPane.showMessageDialog(null,"수정가능");
				updateOkForm();  //수정 폼 : 도서명, 저자명, 가격을 새로 입력 폼
			}else {
				JOptionPane.showMessageDialog(null,"해당 데이터가 존재하지 않습니다.");
			}
		}else if(menu.equals("수정하기")) {
			//1. 수정데이터
			ArrayList<String> dataList = new ArrayList<String>();
			for(TextField tf : tf_update_list) {
				dataList.add(tf.getText().trim());
			}
			//BookVO 객체 생성 및 데이터 추가
			BookVO book = new BookVO();
			book.setIsbn(dataList.get(0));
			book.setTitle(dataList.get(1));
			book.setAuthor(dataList.get(2));
			book.setPrice(Integer.parseInt(dataList.get(3)));
			
			System.out.println(book.getIsbn());
			System.out.println(book.getTitle());
			System.out.println(book.getAuthor());
			System.out.println(book.getPrice());
			
			//2. bms에 수정데이터 전송
			if(bms.update(idx, book)) {				
				JOptionPane.showMessageDialog(null, idx+"수정완료");
			}else {
				JOptionPane.showMessageDialog(null, "수정실패");
			}
			
		}else if(menu.equals("수정취소")) {
			
		}else if(menu.equals("삭제검색") || obj == tf_delete) {
			String isbn = tf_delete.getText().trim();
			idx = bms.searchAddress(isbn);
			
			if(idx != -1) {
				//삭제 데이터 존재 시 사용자에게 삭제의사를 물어봄
				int choice = JOptionPane.showConfirmDialog(null, "정말로 삭제 하시겠습니까?");
				if(choice == 0) {
					//삭제 진행 ---> 데이터가 존재하는 곳에서 진행  bms
					if(bms.delete(idx)) {
						JOptionPane.showMessageDialog(null, "삭제완료~");
					}else {
						JOptionPane.showMessageDialog(null, "삭제실패~");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "해당데이터가 존재하지 않습니다");
			}
//			JOptionPane.showMessageDialog(null, "삭제검색");
		}
		
	}//actionPerformed
	
	/** 패널 Reset **/
	public void resetPanel() {
		insert_panel.setVisible(false);
		select_panel.setVisible(false);
		update_panel.setVisible(false);
		delete_panel.setVisible(false);
	}
	
	/** 패널 스위칭 **/
	public void switchPanel(String pname) {
		resetPanel();
		
		if(pname.equals("등록")) {
			insert_panel.setVisible(true);
		}else if(pname.equals("출력")) {
			select_panel.setVisible(true);
		}else if(pname.equals("수정")) {
			update_panel.setVisible(true);
		}else if(pname.equals("삭제")) {
			delete_panel.setVisible(true);
		}
	}
	
	/** 패널 스위칭 **/
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
	
	
	/** 데이터 유효성 체크 **/
	public boolean insertFormCheck() {
		boolean result = false;
		
		int i=0;
		for(TextField tf : tf_list) {
			if(tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, form_names[i]+"을(를) 입력해주세요");
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
	
	
	/** 등록 화면  **/
	public void insertForm() {		
		switchPanel(BookUI.INSERT);
		insert_panel.removeAll();
		tf_list = new ArrayList<TextField>(4);
		
		String title = "===== 도서정보를 등록하는 화면입니다  =====";				
		Panel label_panel = new Panel(new GridLayout(5,1));
		Panel tf_panel = new Panel(new GridLayout(5,1));
		Panel btn_panel = new Panel();
		Button btn_insert = new Button("등록하기");
		Button btn_reset = new Button("등록취소");
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
	
	/** 출력 화면  **/
	public void selectForm() {
		ArrayList<BookVO> list = (ArrayList<BookVO>)bms.selectList();
		switchPanel(BookUI.SELECT);
		select_panel.removeAll();
		
		TextArea ta = new TextArea(15,40);
		if(list.size() != 0) {
			ta.append("*************** 도서관리 시스템 ***************\n");
			ta.append("번호\tISBN\t도서명\t저자\t가격\n");
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
			ta.append("-- 데이터가 존재하지 않습니다. 등록부터 진행해 주세요 --");
		}
		select_panel.add(ta);
		content_panel.add(select_panel);
		f.setVisible(true);
	}

	/** 수정화면 **/
	public void updateForm() {
		tf_update_list = new ArrayList<TextField>();
		switchPanel(BookUI.UPDATE);
		update_panel.removeAll();
				
		Panel update_top = new Panel();
		Label label = new Label(" ISBN ");
		tf_update = new TextField(20);
		Button tf_search = new Button("수정검색");
		
		tf_update_list.add(tf_update);  //수정 isbn TextField 추가
		
		update_top.add(label);
		update_top.add(tf_update);
		update_top.add(tf_search);
		
		update_panel.setLayout(new BorderLayout());
		update_panel.add(BorderLayout.NORTH, update_top);	
				
		content_panel.add(update_panel);
		f.setVisible(true);
		
		tf_search.addActionListener(this); //수정검색 버튼 이벤트 처리 호출
		tf_update.addActionListener(this); //수정 텍스트필드 이벤트 처리 호출
	}
	
	/** 수정 데이터 등록 폼 **/
	public void updateOkForm() {
		String title = "+++++++ 수정 폼 입니다 ++++++++";
		Panel update_bottom = new Panel();
		Panel label_panel = new Panel(new GridLayout(5,1));
		Panel tf_panel = new Panel(new GridLayout(5,1));
		Panel btn_panel = new Panel();
		
		Label update_title = new Label(title);
		Button btn_update_ok = new Button("수정하기");
		Button btn_update_reset = new Button("수정취소");
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
			
			tf_update_list.add(tf); //수정 도서명, 저자, 가격 TextField 추가
		}
		
		update_bottom.setLayout(new BorderLayout());
		update_bottom.add(BorderLayout.NORTH, update_title);
		update_bottom.add(BorderLayout.WEST, label_panel);
		update_bottom.add(BorderLayout.CENTER, tf_panel);
		update_bottom.add(BorderLayout.SOUTH, btn_panel);
		
		update_panel.add(BorderLayout.CENTER, update_bottom);
		content_panel.add(update_panel);
		f.setVisible(true);
		
		//수정하기, 수정취소 이벤트 핸들러 호출
		btn_update_ok.addActionListener(this);
		btn_update_reset.addActionListener(this);
		
	}
	
	
	/** 삭제화면 **/
	public void deleteForm() {
	      switchPanel(BookUI.DELETE);
	      delete_panel.removeAll();
	      
	      //1. 삭제를 위한 isbn 입력 TextField와 삭제버튼 추가
	      Panel p = new Panel();
	      String title = "삭제할 ISBN(도서번호)를 입력해주세요";
	      Label label_title = new Label(title);
	      Label label = new Label("ISBN");
	      tf_delete = new TextField(20);
	      Button del_search = new Button("삭제검색");
	      
	      p.add(label);    p.add(tf_delete);   p.add(del_search);
	      
	      delete_panel.setLayout(new BorderLayout());
	      delete_panel.add(BorderLayout.NORTH, label_title);
	      delete_panel.add(BorderLayout.CENTER, p);
	      
	      content_panel.add(delete_panel);
	      f.add(content_panel);
	      f.setVisible(true);
	      
	      del_search.addActionListener(this); //삭제검색 버튼의 이벤트 처리 호출
	      tf_delete.addActionListener(this); //텍스트필드에서 발생한 이벤트 처리 호출
	      //2. isbn 입력후 삭제 버튼 클릭 시 데이터 존재 시 삭제 진행,
	      //     데이터 존재하지 않는 경우 메시지 출력
	      
	   }
	
}//class



















