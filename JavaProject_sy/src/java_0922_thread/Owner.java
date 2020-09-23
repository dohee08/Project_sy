package java_0922_thread;

public class Owner extends Thread {
	//Field
	DrinkMachine dm;
	String name;
	
	//Constructor
	public Owner(String name, DrinkMachine dm) {
		this.name = name;
		this.dm = dm;
	}
	
	//Method
	public void run() {
		dm.put(name);
	}
}
