package java_0922_thread;

public class FamilyThreadTest {

	public static void main(String[] args) {
		System.out.println("main start----------------------------");
		
		/**�����Ҷ����� �������� ����
		 * -> �����ٷ��� ���� �������� ����
		 */
//		LivingRoom living_room = new LivingRoom();  //public ����
//		FamilyThread father = new FamilyThread("�ƺ�", living_room);
//		FamilyThread son = new FamilyThread("�Ƶ�", living_room);
//		FamilyThread mother = new FamilyThread("����", living_room);
		
		BathRoom bath_room = new BathRoom();		//private ���� -> thread�� ����ȭ
		FamilyThread father = new FamilyThread("�ƺ�", bath_room);
		FamilyThread son = new FamilyThread("�Ƶ�", bath_room);
		FamilyThread mother = new FamilyThread("����", bath_room);
		
		father.start();		//run()
		son.start();
		mother.start();
		
		System.out.println("main end------------------------------");
	}

}
