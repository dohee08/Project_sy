package java_0922_thread;

public class FamilyThreadTest {

	public static void main(String[] args) {
		System.out.println("main start----------------------------");
		
		/**실행할때마다 랜덤으로 나옴
		 * -> 스케줄러에 의해 랜덤으로 관리
		 */
//		LivingRoom living_room = new LivingRoom();  //public 공간
//		FamilyThread father = new FamilyThread("아빠", living_room);
//		FamilyThread son = new FamilyThread("아들", living_room);
//		FamilyThread mother = new FamilyThread("엄마", living_room);
		
		BathRoom bath_room = new BathRoom();		//private 공간 -> thread의 동기화
		FamilyThread father = new FamilyThread("아빠", bath_room);
		FamilyThread son = new FamilyThread("아들", bath_room);
		FamilyThread mother = new FamilyThread("엄마", bath_room);
		
		father.start();		//run()
		son.start();
		mother.start();
		
		System.out.println("main end------------------------------");
	}

}
