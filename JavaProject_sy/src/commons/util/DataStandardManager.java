package commons.util;

public interface DataStandardManager {
	//Method(추상 메소드만)
	//반드시 오버로딩
	public void insert();
	public void select();
	public void update();
	public void delete();
	public Object input (String fname); 	//어떤 타입이든지 다양하게 리턴하겠다.
	
}
