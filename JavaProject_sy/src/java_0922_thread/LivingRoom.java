package java_0922_thread;

public class LivingRoom {
	
	//Method
	public void openDoor(String name) {
		System.out.println(name + "-----> ����");
		for(int i=0; i<20; i++) {
			System.out.println(name + "-----> TV ��û");
		}
		System.out.println(name + "-----> ����");
	}

}
