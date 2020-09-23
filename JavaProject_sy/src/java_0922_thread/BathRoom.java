package java_0922_thread;

public class BathRoom {
	
	//thread의 동기화 
	synchronized void openDoor(String name) {
		System.out.println(name + "-----> 욕실 입장");
		for(int i=0; i<10; i++) {
			System.out.println(name + "-----> 씻는 중~");
		}
		System.out.println(name + "-----> 욕실 퇴장");
	}
}
