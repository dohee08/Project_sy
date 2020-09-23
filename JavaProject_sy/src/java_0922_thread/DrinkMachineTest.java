package java_0922_thread;

public class DrinkMachineTest {

	public static void main(String[] args) {
		DrinkMachine dm = new DrinkMachine();
		Owner owner = new Owner("È«»çÀå", dm);
		Customer customer = new Customer("°í°´", dm);
		
		owner.start();
		new Thread(customer).start();
	}

}
