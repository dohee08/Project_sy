package socre_mgm_interface2;

import java.util.ArrayList;

import commons.util.DataStandardManager;

public class ScoreMgmSystem implements DataStandardManager{
	//Field
	ArrayList<ScoreVO> score_list = new ArrayList<ScoreVO>();
	
	//Constructor
	//Method
	@Override // 오버라이딩
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
	
	//오버로딩
	public boolean insert(ScoreVO score) {
		boolean result = false;
			
		if(score != null) {
			score_list.add(score);
			result = true;
		}
//		System.out.println("size------>" + score_list.size());
		return result;
	}
	
	/** 전체 리스트 출력 **/
	public ArrayList<ScoreVO> selectList(){	
		return score_list;
		}
	
	/** 학생 정보 출력 **/
	public ScoreVO selectScore(int idx) {
		ScoreVO score = null;
		
		if(idx != -1) {
			score = score_list.get(idx);
		}
		
		return score;
	}
	
	/** 학번 검색 **/
	public int searchSno(String sno) {
		int idx = -1;
		
		for(ScoreVO score : score_list) {
			if(score.getSno().equals(sno)) {
				idx = score_list.indexOf(score);
			}
		}
		
		return idx;
	}
	/** 수정 **/
	public boolean update(int idx, ScoreVO score) {
		boolean result = false;
		
		if(idx != -1) {
			score_list.set(idx, score);
			result = true;   //결과값을 받아서 넘김
		}
		
		return result;
	}
	/** 삭제 **/
	public boolean delete(int idx) {
		boolean result = false;
		if(idx != -1) {
			score_list.remove(idx);
			result = true;
		}
		return result;
	}
	
}//class
