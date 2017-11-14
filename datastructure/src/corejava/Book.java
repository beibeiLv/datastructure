package corejava;

import java.io.Serializable;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long bookId;
	private String bookName;
	
	
	public Book(long bookId, String bookName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	 public String toString(){
		 return "[id ="+ bookId+ " name ="+ bookName +"]";
	 }
}
