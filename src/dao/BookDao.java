package dao;

import java.util.List;

import com.java.util.DBUtil;
import com.java.util.Page;

import booklibrary.model.Book;

public interface BookDao {
	public int addBook(Book book);
	
	public List<Book> getBookSList();
	
	public List<Book> getBookSListNew(String name);
	
	public Book getBookById(String id);
	
	public List<Book> getBookRe(String name);
	
	public int addcomments(Book book);
	
	public Page  getBooks(Page page);
	
	public Page  getBooksByType(Page page,int typeid);
	
	public Page  getBooksByName(Page page,String name,String author,String publisher);
	
	public int update(Book book);

	public int borrow(DBUtil dbutil,String bookid);
	
	public int returnbook(String bookid);

	public int delete(int id);
}
