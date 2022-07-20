package booklibrary.service.impl;

import java.util.List;

import com.java.util.Page;

import dao.BookDao;
import booklibrary.dao.impl.BookDaoImpl;
import booklibrary.model.Book;
import service.BookService;

public class BookServiceImpl implements BookService{
	BookDao bookdao=new BookDaoImpl();
	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		return bookdao.addBook(book);
	}
	@Override
	public List<Book> getBookSList() {
		// TODO Auto-generated method stub
		return bookdao.getBookSList();
	}
	@Override
	public List<Book> getBookSListNew(String name) {
		// TODO Auto-generated method stub
		return bookdao.getBookSListNew(name);
	}
	@Override
	public Book getBookById(String id) {
		// TODO Auto-generated method stub
		return bookdao.getBookById(id);
	}
	@Override
	public List<Book> getBookRe(String name) {
		// TODO Auto-generated method stub
		return bookdao.getBookRe(name);
	}
	@Override
	public int addcomments(Book book) {
		// TODO Auto-generated method stub
		return bookdao.addcomments(book);
	}
	@Override
	public Page getBooks(Page page) {
		// TODO Auto-generated method stub
		return bookdao.getBooks(page);
	}
	@Override
	public Page getBooksByType(Page page, int typeid) {
		// TODO Auto-generated method stub
		return bookdao.getBooksByType(page, typeid);
	}
	@Override
	public Page getBooksByName(Page page, String name,String author,String publisher) {
		// TODO Auto-generated method stub
		return bookdao.getBooksByName(page, name, author, publisher);
	}
	@Override
	public int update(Book book) {
		// TODO Auto-generated method stub
		return bookdao.update(book);
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return bookdao.delete(id);
	}
	@Override
	public int returnbook(String bookid) {
		// TODO Auto-generated method stub
		return bookdao.returnbook(bookid);
	}

}
