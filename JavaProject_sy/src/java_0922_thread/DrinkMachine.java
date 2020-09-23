package java_0922_thread;

public class DrinkMachine { //공유 클래스
	//Field
	String[] drinkList = {"콜라", "사이다", "커피", "환타", "생수", "우유"};
	
	//Constructor
	//Method
	synchronized public void put(String name) {
		System.out.println("put start ------> ");
		for(String menu : drinkList) {
			System.out.println(menu + "넣기---------");
		}
		System.out.println("put end ------> ");
	}
	
	synchronized public void get(String name) {
		System.out.println("get start ------> ");
		for(String menu : drinkList) {
			System.out.println(menu + "꺼내기---------");
		}
		System.out.println("get end ------> ");
	}
}
