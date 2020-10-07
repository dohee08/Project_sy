package book_mgm_interface;

import java.util.ArrayList;

import java_1006_jdbc.DBConn;

public class BookDAO extends DBConn{
	//등록(C-- insert)
	public boolean insert(BookVO book) {
		boolean result = false;
		
		try {
			String sql = "INSERT INTO BOOK(ISBN, TITLE, AUTHOR, PRICE) "
					+ "VALUES('" + book.getIsbn() + "', '" 
					+ book.getTitle() +"', '" 
					+ book.getAuthor() + "', " 
					+ book.getPrice() +")";
			getStatement();
			int count = stmt.executeUpdate(sql);  //insert, update, delete 쿼리 실행문 executeUpdate 사용됨!!
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//조회(R-- select)
	public ArrayList<BookVO> select() {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		
		try {
			String sql = "SELECT * FROM BOOK";
			getStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setIsbn(rs.getString(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPrice(rs.getInt(4));
				
				list.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	//조회(R-- select) ISBN 검색
	public int select(String isbn){
		int result = 0;
		
		try {
			String sql = "SELECT COUNT(*) FROM BOOK WHERE ISBN = '" + isbn +"'";
			getStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//수정(U-- update)
	public boolean update(BookVO book) {
		boolean result = false;
		
		try {
			String sql = "UPDATE BOOK "
					+ " SET TITLE = '" + book.getTitle() + "',"
					+ " AUTHOR = '" + book.getAuthor() + "',"
					+ " PRICE = " + book.getPrice() 
					+ " WHERE ISBN = '" + book.getIsbn() + "'";
			getStatement();
			int count = stmt.executeUpdate(sql);
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//삭제(D-- delete)
	public boolean delete(String isbn) {
		boolean result = false;
		
		try {
			String sql = "DELETE FROM BOOK WHERE ISBN = '" + isbn + "'";
			getStatement();
			int count = stmt.executeUpdate(sql);
			if(count != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
