package socre_mgm_interface2;

public class ScoreVO {
	//Field : �й�, �л���, ����, ����, ����, ����, ���
	int kor, eng, math;
	double tot, avg;
	String sno, name;
	
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public double getTot() {
		return kor+eng+math;
	}
	public void setTot(double tot) {
		this.tot = tot;
	}
	public double getAvg() {
		return getTot()/3;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
