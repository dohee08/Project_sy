package java_0922_thread;

public class BathRoom {
	
	//thread�� ����ȭ 
	synchronized void openDoor(String name) {
		System.out.println(name + "-----> ��� ����");
		for(int i=0; i<10; i++) {
			System.out.println(name + "-----> �Ĵ� ��~");
		}
		System.out.println(name + "-----> ��� ����");
	}
}
