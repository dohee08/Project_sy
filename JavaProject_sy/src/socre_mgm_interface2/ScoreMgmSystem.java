package socre_mgm_interface2;

import java.util.ArrayList;

import commons.util.DataStandardManager;

public class ScoreMgmSystem implements DataStandardManager{
	//Field
	ArrayList<ScoreVO> score_list = new ArrayList<ScoreVO>();
	
	//Constructor
	//Method
	@Override // �������̵�
	public void insert() {}
	
	@Override
	public void select() {}				
	
	@Override
	public void update() {}

	@Override
	public void delete() {}		

	@Override
	public Object input(String fname) {		
		return new Object();
	}
	
	//�����ε�
	public boolean insert(ScoreVO score) {
		boolean result = false;
			
		if(score != null) {
			score_list.add(score);
			result = true;
		}
//		System.out.println("size------>" + score_list.size());
		return result;
	}
	
	/** ��ü ����Ʈ ��� **/
	public ArrayList<ScoreVO> selectList(){	
		return score_list;
		}
	
	/** �л� ���� ��� **/
	public ScoreVO selectScore(int idx) {
		ScoreVO score = null;
		
		if(idx != -1) {
			score = score_list.get(idx);
		}
		
		return score;
	}
	
	/** �й� �˻� **/
	public int searchSno(String sno) {
		int idx = -1;
		
		for(ScoreVO score : score_list) {
			if(score.getSno().equals(sno)) {
				idx = score_list.indexOf(score);
			}
		}
		
		return idx;
	}
	/** ���� **/
	public boolean update(int idx, ScoreVO score) {
		boolean result = false;
		
		if(idx != -1) {
			score_list.set(idx, score);
			result = true;   //������� �޾Ƽ� �ѱ�
		}
		
		return result;
	}
	/** ���� **/
	public boolean delete(int idx) {
		boolean result = false;
		if(idx != -1) {
			score_list.remove(idx);
			result = true;
		}
		return result;
	}
	
}//class
