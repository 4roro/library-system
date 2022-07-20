package service;

import java.util.List;

import com.java.util.Page;

import booklibrary.model.Book;

public interface BookService {
	public int addBook(Book book);

	public List<Book> getBookSList();
	
	public List<Book> getBookSListNew(String name);//获取所有新上架图书
	
	public Book getBookById(String id);//书籍详情
	
	public List<Book> getBookRe(String name);//推荐书籍
	
	public int addcomments(Book book);//添加书籍评论
	
	public Page  getBooks(Page page);//获取所有图书  分页
	
	public Page  getBooksByType(Page page,int typeid);//根据数据类别获取图书
	
	public Page  getBooksByName(Page page,String name,String author,String publisher);

	public int update(Book book);

	public int delete(int id);

	public int returnbook(String bookid);
	
	
}
