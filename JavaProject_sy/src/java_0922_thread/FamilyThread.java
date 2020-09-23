package java_0922_thread;

public class FamilyThread extends Thread{
	//Field
	String name;
	LivingRoom living_room;
	BathRoom bath_room;
	
	//Constructor
	public FamilyThread(String name, LivingRoom living_room) {
		this.name = name;
		this.living_room = living_room;
	}
	
	public FamilyThread(String name, BathRoom bath_room) {
		this.name = name;
		this.bath_room = bath_room;
	}
	
	//Method
	public void run() {
		try {
			sleep(2000);
//			living_room.openDoor(name);
			bath_room.openDoor(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
