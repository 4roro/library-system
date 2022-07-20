package booklibrary.dao.impl;

import java.util.List;

import com.java.util.DBUtil;
import com.java.util.Page;

import booklibrary.model.Book;
import dao.BookDao;

public class BookDaoImpl implements BookDao{
DBUtil dbutil=new DBUtil();
	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		String sql="insert into books(name,detail,countbook,pay,typeid,datetime,img,star,author,publisher) values(?,?,?,?,?,?,?,?,?,?)";
		Object[]obj={book.getName(),book.getDetail(),book.getCountbook(),book.getPay(),book.getTypeid(),book.getDatetime(),book.getImg(),"0",book.getAuthor(),book.getPublicer()};
		int result=0;
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<Book> getBookSList() {
		// TODO Auto-generated method stub
		String sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename from  books inner join types on types.id=books.typeid order by books.star desc";
		List books=null;
		try {
			books=dbutil.getQueryList(Book.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	public List<Book> getBookSListNew(String name) {
		// TODO Auto-generated method stub
		String sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename from  books inner join types on types.id=books.typeid order by books.id desc ";
		List books=null;
		try {
			books=dbutil.getQueryList(Book.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	public Page  getBooks(Page page){
		String sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename,books.datetime datetime,books.author author,books.publisher publicer from  books inner join types on types.id=books.typeid  order by books.id desc ";
		Page page1=null;
		page1=dbutil.getQueryPage(Book.class, sql,null, page);
		return page1;
		
	}
	public Page  getBooksByName(Page page,String name,String author,String publisher){
		String sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename,books.datetime datetime,books.author author,books.publisher publicer from  books inner join types on types.id=books.typeid  order by books.id desc";
		if((!name.equals(""))||name!=""){
			sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename,books.datetime datetime,books.author author,books.publisher publicer from  books inner join types on types.id=books.typeid  where books.name like '%"+name+"%'   order by books.id desc";
		}if((!author.equals(""))||author!=""){
			sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename,books.datetime datetime,books.author author,books.publisher publicer from  books inner join types on types.id=books.typeid  where books.name like '%"+name+"%' and books.author like '%"+author+"%'  order by books.id desc";
		}if((!publisher.equals(""))||publisher!=""){
			sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename,books.datetime datetime,books.author author,books.publisher publicer from  books inner join types on types.id=books.typeid  where books.name like '%"+name+"%' and books.author like '%"+author+"%' and books.publisher like '%"+publisher+"%'    order by books.id desc";
		}
		Page page1=null;
		page1=dbutil.getQueryPage(Book.class, sql,null, page);
		return page1;
		
	}
	public Page  getBooksByType(Page page,int typeid){
		String sql="select  books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments cooments,books.star star,types.name typename from  books inner join types on types.id=books.typeid  where books.typeid=?";
		Object[] obj={typeid};
		Page page1=null;
		page1=dbutil.getQueryPage(Book.class, sql,obj, page);
		return page1;
		
	}
	public List<Book> getBookRe(String name) {
		// TODO Auto-generated method stub
		String sql="select id,name,img,detail from books where name like '"+name+"%'";
		List books=null;
		try {
			books=dbutil.getQueryList(Book.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	public Book getBookById(String id){
		int id1=Integer.valueOf(id);
		String sql="select books.id id, books.name name,books.detail detail,books.countbook countbook,books.pay pay,books.img img,books.comments comments,books.star star,types.name typename ,books.author author,books.publisher publicer from  books inner join types on types.id=books.typeid where books.id=?";
		Object[] obj={id1};
		Book book=new Book();
		try {
			book=(Book) dbutil.getObject(Book.class, sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
		
	}
	public int addcomments(Book book){
		String sql="update books set comments=? where id=?";
				
				Object[] paramList={book.getComments(),book.getId()};
				int result=0;
				try {
					result=dbutil.execute(sql, paramList);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
			}
	@Override
	public int update(Book book) {
		// TODO Auto-generated method stub
		String sql="update books set name=?,detail=?,pay=?,countbook=?,img=?,typeid=?,author=?,publisher=? where id=?";
		Object[] obj={book.getName(),book.getDetail(),book.getPay(),book.getCountbook(),book.getImg(),book.getTypeid(),book.getAuthor(),book.getPublicer(),book.getId()};
		int result=0;
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int borrow(DBUtil dbutil1,String bookid) {
		// TODO Auto-generated method stub
		String sql="update books set countbook=countbook-1,star=star+1 where id=?";
		Object[] obj={bookid};
		int result=0;
		try {
			result=dbutil1.execute(sql,obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int returnbook(String bookid) {
		// TODO Auto-generated method stub
		String sql="update books set countbook=countbook+1 where id=?";
				Object[] obj={bookid};
		int result=0;
		try {
			result=dbutil.execute(sql,obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from books where id=?";
		Object[] obj={id};
		int result=0;
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
