package java_0922_thread;

//public class ChildThread extends Thread{
//	
//	public void run() {  //Thread�� �����Ǹ� �ݵ�� run()�޼ҵ带 �ݵ�� �������̵� �ؾ� ��!!
//		for(int i=0; i<10; i++) {
//			System.out.println("i-->" + (i+1));
//		}
//	}
//}

public class ChildThread implements Runnable{

	@Override
	public void run() {
		System.out.println("child �޼ҵ� ���� ------------");
		
		for(int i=0; i<10; i++) {
			System.out.println("i-->" + (i+1));
		}	
		
		System.out.println("child �޼ҵ� ���� ------------");
	}
	
}