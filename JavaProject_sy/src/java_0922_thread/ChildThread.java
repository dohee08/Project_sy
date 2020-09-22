package java_0922_thread;

//public class ChildThread extends Thread{
//	
//	public void run() {  //Thread로 생성되면 반드시 run()메소드를 반드시 오버라이딩 해야 함!!
//		for(int i=0; i<10; i++) {
//			System.out.println("i-->" + (i+1));
//		}
//	}
//}

public class ChildThread implements Runnable{

	@Override
	public void run() {
		System.out.println("child 메소드 시작 ------------");
		
		for(int i=0; i<10; i++) {
			System.out.println("i-->" + (i+1));
		}	
		
		System.out.println("child 메소드 종료 ------------");
	}
	
}