package java_0922_thread;

public class DrinkMachineTest {

	public static void main(String[] args) {
		DrinkMachine dm = new DrinkMachine();
		Owner owner = new Owner("ȫ����", dm);
		Customer customer = new Customer("��", dm);
		
		owner.start();
		new Thread(customer).start();
	}

}
