package book_mgm_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import commons.util.DataStandardManager;

public class BookMgmSystem implements DataStandardManager{ //implements ���� �ݵ�� �������̵� �ؾ� �� 
	//Field
	List<BookVO> bookList = new ArrayList<BookVO>();
//	ArrayList<BookVO> bookList = new ArrayList<BookVO>();
	Scanner scan = new Scanner(System.in);
	
	//Constructor
	public BookMgmSystem() {
//		showMenu();
//		choiceMenu();
	}
	//Method
	/** �޴� ��� **/
	public void showMenu() {
		System.out.println("==============================");
		System.out.println("\t  ���� ���� �ý��� ");
		System.out.println("==============================");
		System.out.println("\t1. �������� ���");
		System.out.println("\t2. �������� ���");
		System.out.println("\t3. �������� ����");
		System.out.println("\t4. �������� ����");
		System.out.println("\t5. ���α׷� ����");
		System.out.println("==============================");
	}
	
	/** �޴� ���� **/
	public void  choiceMenu() {
		System.out.print("�޴� ����> ");
		switch( scan.nextInt()) {
		case 1: 	insert();	break;
		case 2:		select();	break;
		case 3:		update();	break;
		case 4:		delete();	break;
		case 5:		System.out.println("== ���α׷��� �����մϴ� =="); 
					System.exit(0);	break;
		default :   System.out.println("�غ����Դϴ�."); 
					choiceMenu();
		}
	}
	
	/** ������ȣ(isbn) �ߺ� üũ **/
	public boolean isbnCheck(String isbn) {
		boolean result = false;
		
		for(BookVO book : bookList) {
			if(book.getIsbn() != null) {
				if(book.getIsbn().equals(isbn))
					result = true;
			}
		}
			
		return result;
	}
	
	/** ���� ���� �Է�  **/   // + ������ �ߺ� ���� üũ
	public Object input (String fname) { 
		BookVO book = null;
		
		if(fname.equals("insert")) {
			System.out.print("ISBN> ");
			String isbn = scan.next();
			
			if(isbnCheck(isbn)) {
				//�ߺ�
				System.out.println(" == �ߺ��� �����Ͱ� �����մϴ�. == ");
			}else {
				//�Է� ����
				book = new BookVO();
				book.setIsbn(isbn);
				
				System.out.print("������> ");
				book.setTitle(scan.next());
				System.out.print("����> ");
				book.setAuthor(scan.next());
				System.out.print("����> ");
				book.setPrice(scan.nextInt());
			}
		}else {
			
			System.out.print("������> ");
			book.setTitle(scan.next());
			System.out.print("����> ");
			book.setAuthor(scan.next());
			System.out.print("����> ");
			book.setPrice(scan.nextInt());
			
			
		} return book; // Object�κи� Ȱ��ȭ �Ǿ� ���ϵ�
		
	}
	
	/*
	 * ���� ���
	 */
	public void insert() {
		BookVO book =(BookVO) input("insert"); 		//(BookVO)ĳ���� �ϱ������� object-> Ȱ��, BookVO ->��Ȱ��
		if(book !=null ) {
			if(bookList.add(book)) {
				boolean result = bookList.add(book);
				if(result) {
					System.out.println("== ����� �Ϸ�Ǿ����ϴ�. ==");
				}else {
					System.out.println("== ����� �����Ͽ����ϴ�. ==");
					}
				}
		}
//		choiceMenu();
	}
	
	public boolean insert(BookVO book) {
//		BookVO book =(BookVO) input("insert"); 		//(BookVO)ĳ���� �ϱ������� object-> Ȱ��, BookVO ->��Ȱ��
		boolean result = false;
		
		//DB ���� �� insert 
		BookDAO dao = new BookDAO();
		result = dao.insert(book);  	//dao�� �Ѱ���
		
//		if(book !=null ) {
//				result = bookList.add(book);
////				if(result) {
////					System.out.println("------>" + bookList.size());
////					System.out.println("== ����� �Ϸ�Ǿ����ϴ�. ==");
////				}else {
////					System.out.println("== ����� �����Ͽ����ϴ�. ==");
////					}
//				}
		return result;
	}
	
	
	/*
	 * ���� ���� ���
	 */
	public void select() {
		if(bookList.size() != 0) {
			//������ ���
			System.out.println("======== ���� ���� �ý��� ========");
			System.out.print("������ȣ\t������\t����\t����\n");
			System.out.println("================================");
			
			for(BookVO book : bookList)  {       				// book == bookList[i]
				if(book != null) {
					System.out.print(book.getIsbn() + "\t");
					System.out.print(book.getTitle() + "\t");
					System.out.print(book.getAuthor() + "\t");
					System.out.print(book.getPrice() + "\n");
				}
			}
		}else {
			System.out.println("== �����Ͱ� �������� �ʽ��ϴ�. ��Ϻ����������ּ���. ==");
		}
	
		choiceMenu();
		
	}
	/*
	 * ���� ���� ���
	 */
	public List<BookVO> selectList() {
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		BookDAO dao = new BookDAO();
		bookList = dao.select();
		
		return bookList;
		
//		for(BookVO book : bookList) {
//			System.out.println(book.getIsbn());
//			System.out.println(book.getTitle());
//			System.out.println(book.getAuthor());
//			System.out.println(book.getPrice());
//		}
	}
	
	/*
	 * �ε��� �˻�
	 */
	public int searchAddress(String isbn) {
		int idx = 0;
		
		//db���� �� �˻�
		BookDAO dao = new BookDAO();
		idx = dao.select(isbn);
		
		return idx;
//		for(BookVO book : bookList) {
//			if(book != null) {
//				if(book.getIsbn().equals(isbn)) {
//					idx = bookList.indexOf(book);
//				}
//			}
//		}
		
	}
	
	/*
	 * ���� ���� ����
	 */
	public boolean update(BookVO book) {
		boolean result = false;
		
		BookDAO dao = new BookDAO();
		result = dao.update(book);
		
		return result;
	}
//	public boolean update(int idx, BookVO book) {
//		boolean result = false;
//		
//		if(book != null) {
//			bookList.set(idx, book);
//			System.out.println("== ������ �Ϸ�Ǿ����ϴ�. ==");
//			result = true;
//		}
//		return result;
//		System.out.print("������ ISBN> ");
//		String isbn = scan.next();
//		int update_idx = searchAddress(isbn);
				
//		if(update_idx != -1) {
//			BookVO update_book = (BookVO)input("update");
//			update_book.setIsbn(isbn);
//	
//			bookList.set(update_idx, update_book);
//			System.out.println("== ������ �Ϸ�Ǿ����ϴ�. ==");
//		}else {
//			System.out.println("== �ش� �����Ͱ� �������� �ʽ��ϴ�.");
//		}
//		choiceMenu();
//	}
	
	@Override
	public void update() {
		System.out.print("������ ISBN>");
		String isbn = scan.next();
		int update_idx = searchAddress(isbn);
		
		if(update_idx != -1) {
			BookVO update_book = (BookVO)input("update");
			update_book.setIsbn(isbn);
			
			bookList.set(update_idx, update_book);
			System.out.println("== ������ �Ϸ�Ǿ����ϴ�. ==");
		}else {
			System.out.println("== �ش� �����Ͱ� �������� �ʽ��ϴ�. ==");	
		}
	
		
	}
	
	/** ���� ���� ���� **/
	public void delete() {
		System.out.print("������ ISBN> ");
		String isbn = scan.next();
		int delete_idx = searchAddress(isbn);
		
		if(delete_idx != -1) {
			bookList.remove(delete_idx);
			System.out.println("== ������ �Ϸ�Ǿ����ϴ�. ==");
		}else {
			System.out.println("== �ش� �����Ͱ� �������� �ʽ��ϴ�.");
		}
		choiceMenu();
		
	}
	
	/** ���� ���� ���� **/
	public boolean delete(String isbn) {
		boolean result = false;
		
		BookDAO dao = new BookDAO();
		result = dao.delete(isbn);
		
		return result;
	}
//	public boolean delete(int idx) {
//		boolean result = false;
//		
//		if(idx != -1) {
//			bookList.remove(idx);
//			result = true;
//		}
//		return result;
//		
//	}
	
		
}