package java_0922_thread;

public class ParentThreadTest {

	public static void main(String[] args) { 
		//main �޼ҵ� ���� �� JVM�� main thread�� �ڵ����� �Ҵ��ϰ�
		//���� main �޼ҵ� ���� �� main thread�� �ڵ����� ȸ����
		
		System.out.println("���� �޼ҵ� ���� --------------");
		//ChildThread ��ü ���� : ����1���� 10���� ���

		ChildThread ct = new ChildThread();
//		ct.start();  //start �޼ҵ带 ȣ���Ͽ� run() ����!! --> ������ Thread�� �Ҵ�޾� �����!!
		new Thread(ct).start();  //Runnable�� ������ Ŭ������ ������ Thread Ŭ������ �����ϰ� start ȣ��!
		
		System.out.println("���� �޼ҵ� ���� --------------");
	}

}
