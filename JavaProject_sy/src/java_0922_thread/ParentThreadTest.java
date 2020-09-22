package java_0922_thread;

public class ParentThreadTest {

	public static void main(String[] args) { 
		//main 메소드 실행 시 JVM이 main thread를 자동으로 할당하고
		//이후 main 메소드 종료 시 main thread도 자동으로 회수됨
		
		System.out.println("메인 메소드 시작 --------------");
		//ChildThread 객체 생성 : 숫자1에서 10까지 출력

		ChildThread ct = new ChildThread();
//		ct.start();  //start 메소드를 호출하여 run() 실행!! --> 별도의 Thread를 할당받아 실행됨!!
		new Thread(ct).start();  //Runnable을 구현한 클래스는 별도의 Thread 클래스를 생성하고 start 호출!
		
		System.out.println("메인 메소드 종료 --------------");
	}

}
