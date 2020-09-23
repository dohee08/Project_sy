package java_0922_thread;

public class Customer implements Runnable{
	//Field
	DrinkMachine dm;
	String name;
	
	//Constructor
	public Customer(String name, DrinkMachine dm) {
		this.name = name;
		this.dm = dm;
	}
	
	//Method
	@Override
	public void run() {
		dm.get(name);
	}

}
