package java_0922_thread;

public class DrinkMachine { //���� Ŭ����
	//Field
	String[] drinkList = {"�ݶ�", "���̴�", "Ŀ��", "ȯŸ", "����", "����"};
	
	//Constructor
	//Method
	synchronized public void put(String name) {
		System.out.println("put start ------> ");
		for(String menu : drinkList) {
			System.out.println(menu + "�ֱ�---------");
		}
		System.out.println("put end ------> ");
	}
	
	synchronized public void get(String name) {
		System.out.println("get start ------> ");
		for(String menu : drinkList) {
			System.out.println(menu + "������---------");
		}
		System.out.println("get end ------> ");
	}
}
