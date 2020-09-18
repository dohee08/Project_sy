package socre_mgm_interface2;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

//ScoreUI���� �̺�Ʈ �߻����� �� ó�����ִ� class
public class ScoreEvent extends WindowAdapter implements ActionListener{
	//Field
	ScoreUI ui;
	
	//Constructor
	public ScoreEvent(ScoreUI ui) {
		//ScoreEvent ���������� �Ķ���� ui�� ����
		this.ui = ui;
	}
	
	//Method
	//������ �̺�Ʈ ó��
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
		
	@Override
	//�׼� �̺�Ʈ ó��
	public void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand().trim();
		Object obj = e.getSource(); //��ü�� �ּҸ� ������ ��
			
		if(name.equals("�α���") || obj == ui.tf_pass) {
			ui.loginProc();
		}else if(name.equals("���")) {
			ui.insertForm();
		}else if(name.equals("��ȸ")) {
			ui.selectFormTable();
//			ui.selectForm();
		}else if(name.equals("����")) {
			ui.updateForm();
		}else if(name.equals("����")) {
			ui.deleteForm();
		}else if(name.equals("����")) {
			int exit = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?");
			if(exit == 0) {
				System.exit(0);
			}			
		}else if(name.equals("��ϿϷ�") || obj == ui.tf_insert_last) {
			ui.insertProc();
		}else if(name.equals("������")) {
			for(TextField tf : ui.tf_insert_list) {
				tf.setText("");
			}
		}else if(obj == ui.update_search || obj == ui.tf_update) {
			ui.updateSearchProc();
		}else if(name.equals("�����Ϸ�") || obj == ui.tf_update_last) {
			ui.updateProc();
		}else if(name.equals("�������")) {
			JOptionPane.showMessageDialog(null, "�������");
		}else if(obj == ui.tf_delete || obj == ui.delete_search){
			ui.deleteProc();
		}
	}//actionPerformed method

}
