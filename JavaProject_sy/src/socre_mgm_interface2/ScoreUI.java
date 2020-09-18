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
	ScoreEvent score_event = new ScoreEvent(this); //이벤트 처리 객체 생성
	//ScoreEvent에서 ScoreUI 클래스의 주소를 가지고 자기 것처럼 이용할 수 있음
	ScoreMgmSystem sms;
	Frame f;
	Panel intro_panel, menu_panel, content_panel, insert_panel, 
			select_panel, update_panel, delete_panel, update_bottom;
	String[] menu_names = {"		등록 		", "조회", "수정", "삭제", "종료"};
	String[] form_names = {"학생명", "국어", "영어", "수학"};
	ArrayList<TextField> tf_insert_list; //등록메뉴 선택 시 새로운 값을 가져오기 위해 선언만 해 놓음
	ArrayList<TextField> tf_update_list; //수정메뉴 선택 시 새로운 값을 가져오기 위해 선언만 해 놓음
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
	Object[] columns = {"번호","학번", "학생명", "국어", "영어", "수학", "총점", "평균"};
	DefaultTableModel model = new DefaultTableModel(columns, 0);
	Object[] row = new Object[8];
	JTable table = new JTable(model);
	
	//Constructor
	public ScoreUI() {
		//인트로 페이지 생성
		sms = new ScoreMgmSystem();
		f = new Frame("성적관리시스템");
		intro_panel = new Panel(new BorderLayout());
		ImageIcon img = new ImageIcon("Images/logo_img.png");
		JLabel logo  = new JLabel(img);
		
		Panel login_panel = new Panel();
		Label label_id = new Label("아이디");
		Label label_pass = new Label("패스워드");
		tf_id = new JTextField(10);
		tf_pass = new JPasswordField(10);
		JButton btn_login = new JButton("로그인");
		
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
		f.setLocationRelativeTo(null); //화면의 가운데 출력 메소드
		f.setVisible(true);
		
		f.addWindowListener(score_event);
		btn_login.addActionListener(score_event);
		tf_pass.addActionListener(score_event);
	}
	public void init() {	
//		selectForm(); // 로그인 성공 후 등록화면을 기본페이지로 보여줌 -> null point exception : 객체 생성 하기 전에 먼저 호출 되었기 때문
		
		menu_panel = new Panel(new GridLayout(1,5)); //panel의 기본 레이아웃 FlowLayout --> GridLayout
		content_panel = new Panel();
		insert_panel = new Panel();
		select_panel = new Panel();
		update_panel = new Panel();
		delete_panel = new Panel();
		update_bottom = new Panel(new BorderLayout());
		
		//메뉴버튼 생성 및 이벤트 추가
		for(String name : menu_names) {
			Button menu = new Button(name);
			menu_panel.add(menu);
			menu.addActionListener(score_event);
		}
		
		f.add(BorderLayout.NORTH, menu_panel);
		f.add(BorderLayout.CENTER, content_panel);
		insertForm(); //로그인 성공 후 등록화면을 기본페이지로 보여줌
//		f.setSize(500,400);
//		f.setVisible(true);
		
		f.addWindowListener(score_event);
	}
	//Method
	
	
	/** 로그인 처리 메소드 **/
	public void loginProc() {
		//로그인 처리 로직~~
		String id = tf_id.getText().trim();
		String pass = tf_pass.getText().trim();
		
		if(id.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			tf_id.requestFocus();
		}else if(pass.equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
			tf_pass.requestFocus();
		}else {
			//로그인 처리 : 아이디, 패스워드 비교 = test/1234
			String d_id = "test";
			String d_pass = "1234";
			if(d_id.equals(id) && d_pass.equals(pass)) {
				JOptionPane.showMessageDialog(null, "로그인 성공~");	
				intro_panel.setVisible(false);
				init();
			}else {
				JOptionPane.showMessageDialog(null, "아이디 또는 패스워드가 일치하지 않습니다");
				tf_id.setText(""); tf_pass.setText("");
				tf_id.requestFocus();
			}
			
//			if(d_id.equals(id)) {
//				//패스워드 비교
//				if(d_pass.equals(pass)) {
//					//로그인 성공
//					JOptionPane.showMessageDialog(null, "로그인 성공~");
//				}else {
//					JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다");
//					tf_pass.setText("");
//					tf_pass.requestFocus();
//				}
//			}else {
//				JOptionPane.showMessageDialog(null, "아이디가 일치하지 않습니다");
//				tf_id.setText("");
//				tf_id.requestFocus();
//			}
		}
		
//		JOptionPane.showMessageDialog(null, "로그인 성공~");
		
	}//loginProc Method
	
	/** 등록 처리 메소드 **/
	public void insertProc() {
		//데이터 유효성체크 > 데이터를 VO 저장 > 저장시스템에 전달
		if(insertFormCheck()) {
			//1. 데이터를 VO 저장 
			ArrayList<String> dataList = new ArrayList<String>();
			for(TextField tf : tf_insert_list) {
				dataList.add(tf.getText().trim());
			}
			ScoreVO score = new ScoreVO();
			Random rd = new Random();
			score.setSno("S_"+rd.nextInt(10000));
//			score.setSno(String.valueOf(rd.nextInt(10000))); //학번은 자동 생성 후 입력(4자리 수)
			score.setName(dataList.get(0));
			score.setKor(Integer.parseInt(dataList.get(1)));
			score.setEng(Integer.parseInt(dataList.get(2)));
			score.setMath(Integer.parseInt(dataList.get(3)));
			
			//2. 저장시스템에 전달
			if(sms.insert(score)) {
				JOptionPane.showMessageDialog(null, "등록이 완료되었습니다");
				for(TextField tf : tf_insert_list) {
					tf.setText("");
				}
			}else {
				JOptionPane.showMessageDialog(null, "등록이 실패되었습니다");
			}
		}
//		for(TextField tf : tf_insert_list) {
//			JOptionPane.showMessageDialog(null, tf.getText());
//		}
	}
	/** 수정 검색 처리 메소드 **/
	public void updateSearchProc() {
		String sno = tf_update.getText().trim();
		if(sno.equals("")) {
			//TextField에 데이터가 존재 X
			JOptionPane.showMessageDialog(null, "학번을 입력해주세요");
			tf_update.requestFocus();
		}else {
			//TextField에 데이터가 존재 O
			//검색 가능 --> sms 시스템에서 해당데이터 유/무 확인
			idx = sms.searchSno(sno);
//			System.out.println("idx ==========>>" + idx);
			if(idx != -1) {
				//sms에 해당 학번의 학생정보 가져오기
				ScoreVO score = sms.selectScore(idx);
				//학생정보 존재하는 경우 > 수정 가능
				updateOKForm(score);					
			}else {
//				JOptionPane.showMessageDialog(null, "학생의 정보가 존재하지 않습니다");
				updateFailForm();
			}
//			JOptionPane.showMessageDialog(null, idx);
		}
	}
	/** 수정 처리 메소드 **/
	public void updateProc() {
		JOptionPane.showMessageDialog(null, "수정완료");
		
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
			//수정완료
			JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
			for(TextField tf : tf_update_list) {
				tf.setText("");
			}
//			selectForm(); //조회화면 호출
			selectFormTable();
		}else {
			//수정실패
			JOptionPane.showMessageDialog(null, "수정 실패");
		}
		
	}
	/** 삭제 처리 메소드 **/
	public void deleteProc() {
		//삭제가능
		String sno = tf_delete.getText().trim();
		if(sno.equals("")) {
			//TextField에 데이터가 존재 X
			JOptionPane.showMessageDialog(null, "학번을 입력해 주세요");
			tf_delete.requestFocus();
		}else {
			//TextField에 데이터가 존재 O
			idx = sms.searchSno(sno);
			if(idx != -1) {
				//sms에 데이터 존재 O
				int choice = JOptionPane.showConfirmDialog(null, "정말로 삭제를 진행하시겠습니까?");
				//예(0), 아니요(1), 취소(2)
				if(choice == 0) {
					if(sms.delete(idx)) {
						//sms에서 삭제진행
						JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다");
//						selectForm(); //조회화면 호출
						selectFormTable();
					}else {
						//sms에서 삭제실패
						JOptionPane.showMessageDialog(null, "삭제가 실패되었습니다");
					}
				}
			}else {
				//sms에 데이터 존재 X
				JOptionPane.showMessageDialog(null, "학생의 정보가 존재하지 않습니다");
			}
		}
	}
	
	/** 모든 메뉴의 패널들을 비활성 **/
	public void resetMenuPanel() {
		insert_panel.setVisible(false);
		select_panel.setVisible(false);
		update_panel.setVisible(false);
		delete_panel.setVisible(false); 
	}
	/** 메뉴 선택시 패널 스위칭 **/
	public void switchPanel(int menu) {
		//모든 메뉴의 패널을 비활성화
		resetMenuPanel();
		//menu 1(등록), 2(조회), 3(수정), 4(삭제)
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
	/** 성적 등록 화면 유효성 체크 **/
	public boolean insertFormCheck() {
		boolean result = false;
		
		int i=0;
		for(TextField tf : tf_insert_list) {
			if(tf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, form_names[i]+"을 입력해주세요");
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
	
	/** 성적 등록 화면 **/
	public void insertForm() {
		switchPanel(ScoreUI.INSERT);   //1보다는 static 상수로 처리를 하는 것이 효율적
		insert_panel.removeAll();
		
		tf_insert_list = new ArrayList<TextField>();
		
		//등록폼에서 생성 : 학번, 학생명, 국어, 영어 , 수학
		String title = "-- 성적을 등록하는 화면입니다 --";
		Label title_label = new Label(title);
		Panel label_panel = new Panel(new GridLayout(5,1));
		Panel tf_panel = new Panel(new GridLayout(5,1));
		Panel btn_panel = new Panel();
		Button btn_insert = new Button("등록완료");
		Button btn_reset = new Button("등록취소");
		btn_panel.add(btn_insert); btn_panel.add(btn_reset);
		
		for(String name : form_names) {
			Panel p1 = new Panel(); //label
			Panel p2 = new Panel(); //tf
			Label label = new Label(name);
			TextField tf = new TextField(30);
			p1.add(label); 		label_panel.add(p1);
			p2.add(tf);			tf_panel.add(p2);
			
			tf_insert_list.add(tf);  //등록폼에서 생성된 tf 객체를 전체 공유 가능
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
	
	/** JTable에 추가되는 row 생성 **/
	public void createRow() {
		ArrayList<ScoreVO> list = sms.selectList();
		model.setNumRows(0);
		
		int no = 1;
		for(ScoreVO score: list) {
			if(score != null) {
				row[0] = no;
				row[1] = score.getSno();  //학번
				row[2] = score.getName(); //학생명
				row[3] = score.getKor();  //국어점수
				row[4] = score.getEng();  //영어점수
				row[5] = score.getMath(); //수학점수
				row[6] = score.getTot();  //총점
				row[7] = score.getAvg();  //평균
				
				model.addRow(row);
				no++;
			}
			table.repaint(); //갱신된 list를 반영하기 위해 사용
		}
		model.fireTableDataChanged();			
	}
	
	/** JTable을 이용하여 조회화면 구성 **/
	public void selectFormTable() {
		switchPanel(ScoreUI.SELECT);
		select_panel.removeAll();
		
		createRow();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
		cell.setHorizontalAlignment(SwingConstants.CENTER); //셀 가운데 정렬
//		TableColumnModel col_model = table.getColumnModel();
//		col_model.getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setPreferredWidth(100); //번호 컬러 크기
		table.getColumnModel().getColumn(1).setPreferredWidth(100); //학번 컬러 크기
		
		table.getColumn("번호").setCellRenderer(cell);
		table.getColumn("학번").setCellRenderer(cell);
		table.getColumn("학생명").setCellRenderer(cell);
		table.getColumn("국어").setCellRenderer(cell);
		table.getColumn("영어").setCellRenderer(cell);
		table.getColumn("수학").setCellRenderer(cell);
		table.getColumn("총점").setCellRenderer(cell);
		table.getColumn("평균").setCellRenderer(cell);
		
		JScrollPane scroll_pane = new JScrollPane(table);
		scroll_pane.setBounds(0, 100, 450, 370);
		
		select_panel.setLayout(new BorderLayout());
		select_panel.add(BorderLayout.NORTH, new Label("-- 성적 조회 결과 --"));
		select_panel.add(scroll_pane);
		
		content_panel.add(select_panel);
		f.add(content_panel);
		f.setSize(500,400);
		f.setVisible(true);
	}
	
	/** 성적 조회 화면 **/
	public void selectForm() {
		switchPanel(ScoreUI.SELECT);
		select_panel.removeAll();
		
		//데이터가 존재하는 경우에만 조회를 진행하도록 합니다
		ArrayList<ScoreVO> list = sms.selectList();
		TextArea ta = new TextArea(15,60);
		
		if(list.size() > 0) {
			//조회화면 구성 select_panel.add(label);
			ta.append("==== 성적 조회 결과 ===\n");
			ta.append("번호\t학번\t이름\t국어\t영어\t수학\t총점\t평균\n");
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
			ta.append("--v데이터가 존재하지 않습니다. 등록부터 진행해주세요~ --");
//			JOptionPane.showMessageDialog(null, "데이터가 존재하지 않습니다. 등록부터 진행해주세요~");
		}
		
		select_panel.add(ta);		
		content_panel.add(select_panel);
		
		f.setVisible(true);
	}
	/** 성적 수정 화면 **/
	public void updateForm() {
		tf_update_list = new ArrayList<TextField>();
		
		switchPanel(ScoreUI.UPDATE);
		update_panel.removeAll();
		
		//1. 학번 검색 폼 생성 및 검색버튼의 이벤트 추가
		Panel update_top = new Panel(new BorderLayout());
		Panel search_panel = new Panel();
		String title = "-- 수정할 학생의 학번을 입력해주세요 --";
		Label title_label = new Label(title);
		Label label = new Label("학번");
		tf_update = new TextField(20);
		update_search = new Button("검색"); //Object의 주소로 ActionPerformed
		
		tf_update_list.add(tf_update); //수정을 진행할 학번
		
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
	
	/** 수정 데이터 등록 폼 : 학생명, 국어, 영어, 수학 **/
	public void updateOKForm() {
		
	}
	
	/** 수정 데이터 등록 폼 : 학생명, 국어, 영어, 수학 **/
	public void updateOKForm(ScoreVO score) {
		update_bottom.removeAll();
		
		Panel label_panel = new Panel(new GridLayout(4,1));
		Panel tf_panel = new Panel(new GridLayout(4,1));
		Panel btn_panel = new Panel();
		Button btn_update = new Button("수정완료");
		Button btn_reset = new Button("수정취소");
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
	/** 수정실패 **/
	public void updateFailForm() {
		update_bottom.removeAll();
		update_bottom.add(BorderLayout.NORTH, new Label());
		update_bottom.add(BorderLayout.CENTER, new Label("-- 학생 정보가 존재하지 않습니다 --"));
		
		update_panel.add(BorderLayout.CENTER, update_bottom);
		content_panel.add(update_panel);
		f.setVisible(true);
	}
	
	/** 성적 삭제 화면 **/
	public void deleteForm() {
		switchPanel(ScoreUI.DELETE);
		delete_panel.removeAll();
		
		Panel del_panel = new Panel(new BorderLayout());
		Panel p = new Panel();
		String title = "-- 삭제할 학생의 학번을 입력해주세요 --";
		Label title_label = new Label(title);
		Label label = new Label("학번");
		tf_delete = new TextField(20);
		delete_search = new Button("검색");
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
