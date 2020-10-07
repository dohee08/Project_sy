package book_mgm_interface;

public class BookVO {
	//Field : 도서번호, 도서명, 저자, 가격
	String isbn, title, author;
	int price;
	
	//alt+shift+s
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
//	public void setIsbn(String isbn) {
//		this.isbn = isbn;
//	}
//	public String getIsbn() {
//		return isbn;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//	public String getAuthor() {
//		return author;
//	}
//	public void setPrice(int price) {
//		this.price = price;
//	}
//	public int getPrice() {
//		return price;
//	}

}
