package socre_mgm_interface2;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

//ScoreUI에서 이벤트 발생했을 때 처리해주는 class
public class ScoreEvent extends WindowAdapter implements ActionListener{
	//Field
	ScoreUI ui;
	
	//Constructor
	public ScoreEvent(ScoreUI ui) {
		//ScoreEvent 전역변수에 파라미터 ui를 넣음
		this.ui = ui;
	}
	
	//Method
	//윈도우 이벤트 처리
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
		
	@Override
	//액션 이벤트 처리
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand().trim();
		Object obj = e.getSource(); //객체의 주소를 가져와 비교
			
		if(name.equals("로그인") || obj == ui.tf_pass) {
			ui.loginProc();
		}else if(name.equals("등록")) {
			ui.insertForm();
		}else if(name.equals("조회")) {
			ui.selectFormTable();
//			ui.selectForm();
		}else if(name.equals("수정")) {
			ui.updateForm();
		}else if(name.equals("삭제")) {
			ui.deleteForm();
		}else if(name.equals("종료")) {
			int exit = JOptionPane.showConfirmDialog(null, "정말로 종료하시겠습니까?");
			if(exit == 0) {
				System.exit(0);
			}			
		}else if(name.equals("등록완료") || obj == ui.tf_insert_last) {
			ui.insertProc();
		}else if(name.equals("등록취소")) {
			for(TextField tf : ui.tf_insert_list) {
				tf.setText("");
			}
		}else if(obj == ui.update_search || obj == ui.tf_update) {
			ui.updateSearchProc();
		}else if(name.equals("수정완료") || obj == ui.tf_update_last) {
			ui.updateProc();
		}else if(name.equals("수정취소")) {
			JOptionPane.showMessageDialog(null, "수정취소");
		}else if(obj == ui.tf_delete || obj == ui.delete_search){
			ui.deleteProc();
		}
	}//actionPerformed method

}
