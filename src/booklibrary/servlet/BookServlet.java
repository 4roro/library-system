package booklibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.util.Page;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;









import service.BookService;
import service.NoticeService;
import service.TypeService;
import booklibrary.model.Book;
import booklibrary.model.Notice;
import booklibrary.model.Types;
import booklibrary.service.impl.BookServiceImpl;
import booklibrary.service.impl.NoticeServiceImpl;
import booklibrary.service.impl.TypeServiceImpl;


/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     BookService bookservice=new BookServiceImpl();
     TypeService typeservice=new TypeServiceImpl();
     NoticeService noticeservice=new NoticeServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if(action.equals("findAll")){//查询所有
			findBooksAll(request,response);
		}else if(action.equals("findTypesAll")){
			findTypesAll(request,response);//查询类别
		}else if(action.equals("add")){
			addBooks(request,response);//添加书籍
		}else if(action.equals("findAllList")){
			findBooksList(request,response);//以列表形式查出，用于首页展示
		}else if(action.equals("findById")){
			findById(request,response);//查询单本书籍
		}else if(action.equals("addComments")){
			addComments(request,response);//添加评论
		}else if(action.equals("findAll")){//查询所有
			findBooksAll(request,response);
		}else if(action.equals("findAllByType")){//查询所有
			findBooksAllByType(request,response);
		}else if(action.equals("findAllAdmin")){//查询所有
			findBooksAllAdmin(request,response);
		}else if(action.equals("findBookByName")){//查询书名
			findBookByName(request,response);
		}else if(action.equals("findBookByNameWeb")){//查询书名
			findBookByNameWeb(request,response);
		}
		else if(action.equals("findByIdAdmin")){
			findByIdAdmin(request,response);//查询单本书籍
		}else if(action.equals("update")){//修改
			update(request,response);
		}else if(action.equals("delete")){//删除
			delete(request,response);
		}else if(action.equals("borrow")){//租借详情
			borrow(request,response);
		}
	}

	private void findBookByNameWeb(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		String name=request.getParameter("name");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publicer");
		int curpagestr=0;

		if(curPage==null||"".equals(curPage)){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page page=new Page();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=bookservice.getBooksByName(page, name, author, publisher);
		
		
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/index/books.jsp").forward(request, response);
	}

	private void borrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		Book book=bookservice.getBookById(id);
		request.setAttribute("books", book);
		
		request.getRequestDispatcher("/admin/borrowbook.jsp").forward(request,response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		//int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("id");
		int id=Integer.valueOf(name);
		System.out.println("删除"+id);
		out.print("删除"+id);
		//System.out.print(id);
		//types.setId(id);
		int result=bookservice.delete(id);
		if(result==1){
			out.write("<script>"+"alert('删除成功');"
					+"window.location.href='"+request.getContextPath()+"/BookServlet?action=findAllAdmin&&curPage=1'; "
						+"</script>");
		}else {
			out.write("<script>"+"alert('删除失败');"
					+"window.location.href='"+request.getContextPath()+"/BookServlet?action=findAllAdmin&&curPage=1'; "
						+"</script>");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		//创建SmartUpLoad对象
		SmartUpload smartUpload=new SmartUpload();
		//执行上传初始化
		smartUpload.initialize(this.getServletConfig(),request, response);
		//servletconfig是一个接口，具体实现是由servlet开发商实现的
			//执行上传
			try {
				smartUpload.upload();
				//4获取用户输入的菜品信息
				//注意：在使用了该插件后，要这样获取数据
				String id= smartUpload.getRequest().getParameter("id");
				String name= smartUpload.getRequest().getParameter("name");
				String detail = smartUpload.getRequest().getParameter("deatil");
				String count = smartUpload.getRequest().getParameter("count");
				String pay = smartUpload.getRequest().getParameter("pay");
				String typeid = smartUpload.getRequest().getParameter("typeid");
				String author = smartUpload.getRequest().getParameter("author");
				String publicer = smartUpload.getRequest().getParameter("publicer");
				System.out.println(publicer);
				//获取上传的文件对象
				SmartFile file = smartUpload.getFiles().getFile(0);//获取第一个文件，因为只能上传一个文件
				//获取上传文件的名称
				String imgpath = "img/" + file.getFilePathName();
				Book book=new Book();
				int id1=Integer.valueOf(id);
				book.setId(id1);
				book.setName(name);
				book.setDetail(detail);
				book.setCountbook(count);
				book.setPay(pay);
				book.setTypeid(typeid);
				book.setImg(imgpath);
				book.setAuthor(author);
				book.setPublicer(publicer);
				book.setDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				int result=bookservice.update(book);
				if (result == 1) {
					//保存上传文件
					smartUpload.save("/img");
					out.write("<script>" + "alert('修改书籍成功！');"
							 + "</script>");
					findBooksAllAdmin(request,response);
				}else{
					out.write("<script>" + "alert('修改书籍失败！');"
							 + "</script>");
					findBooksAllAdmin(request,response);
				}
				
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void findByIdAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		Book book=bookservice.getBookById(id);
		request.setAttribute("books", book);
		List<Types> types=typeservice.getTypes();
		request.setAttribute("types", types);
		request.getRequestDispatcher("/admin/updatebook.jsp").forward(request,response);
	}

	private void findBookByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		String name=request.getParameter("name");
		String author=request.getParameter("author");
		String publisher=request.getParameter("publicer");
		int curpagestr=0;

		if(curPage==null||"".equals(curPage)){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page page=new Page();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=bookservice.getBooksByName(page, name, author, publisher);
		
		
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/admin/book.jsp").forward(request, response);
	}

	private void findBooksAllAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		int curpagestr=0;

		if(curPage==null||"".equals(curPage)){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page page=new Page();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=bookservice.getBooks(page);
		
		
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/admin/book.jsp").forward(request, response);
	}

	private void findBooksAllByType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("typeid");
		int typeid=Integer.valueOf(id);
		String curPage=request.getParameter("curPage");
		int curpagestr=0;

		if(curPage==null||"".equals(curPage)){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page page=new Page();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=bookservice.getBooksByType(page, typeid);
		
		
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/index/books.jsp").forward(request, response);
		
	}

	private void addComments(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String contents=request.getParameter("comments");
		String bookname=request.getParameter("bookname");
		int id=Integer.parseInt(request.getParameter("bookid"));
		String bookid=request.getParameter("bookid");
		String beforecontents=request.getParameter("before");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(new Date());
		String username=request.getParameter("name");
		if(username==null||username.equals("")){
			username="匿名";
		}
		contents="<p class='more_details'>评论者: "+username+" </font><br>  评论内容: "+contents+"<br> 评论时间: "+time+"<br>"+beforecontents;
		Book book=new Book();
		book.setComments(contents);
		book.setId(id);
		int result=bookservice.addcomments(book);
		if(result==1){
			find(request,response,bookid,bookname);
		}else if(result==-1){
			find(request,response,bookid,bookname);
		}else{
			out.write("<script>"+"alert('评论失败！');"
						+"</script>");
			find(request,response,bookid,bookname);
		}
	}
	private void find(HttpServletRequest request,
			HttpServletResponse response,String id,String name) throws ServletException, IOException{
		Book book=bookservice.getBookById(id);
		request.setAttribute("books", book);
		List<Book> booknew=bookservice.getBookSListNew("");
		request.setAttribute("booknew", booknew);
		List<Types> types=typeservice.getTypes();
		request.setAttribute("types", types);
		List<Notice> notice=noticeservice.getAll();
		request.setAttribute("notice", notice);
		List<Book> books=bookservice.getBookRe(name);
		request.setAttribute("book", books);
		request.getRequestDispatcher("/index/detail.jsp").forward(request,response);
	}
	private void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		List<Book> booknew=bookservice.getBookSListNew("");
		request.setAttribute("booknew", booknew);
		List<Types> types=typeservice.getTypes();
		request.setAttribute("types", types);
		List<Notice> notice=noticeservice.getAll();
		request.setAttribute("notice", notice);
		Book book=bookservice.getBookById(id);
		request.setAttribute("books", book);
		List<Book> books=bookservice.getBookRe(name);
		request.setAttribute("book", books);
		request.getRequestDispatcher("/index/detail.jsp").forward(request,response);
		
	}

	private void findBooksList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Book> books=bookservice.getBookSList();
		request.setAttribute("books", books);
		request.getRequestDispatcher("/index/index.jsp").forward(request,response);
	}

	private void addBooks(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		//创建SmartUpLoad对象
		SmartUpload smartUpload=new SmartUpload();
		//执行上传初始化
		smartUpload.initialize(this.getServletConfig(),request, response);
		//servletconfig是一个接口，具体实现是由servlet开发商实现的
			//执行上传
			try {
				smartUpload.upload();
				//4获取用户输入的菜品信息
				//注意：在使用了该插件后，要这样获取数据
				String name= smartUpload.getRequest().getParameter("name");
				String detail = smartUpload.getRequest().getParameter("deatil");
				String count = smartUpload.getRequest().getParameter("count");
				String pay = smartUpload.getRequest().getParameter("pay");
				String typeid = smartUpload.getRequest().getParameter("typeid");
				String publicer = smartUpload.getRequest().getParameter("publicer");
				String author = smartUpload.getRequest().getParameter("author");
				//获取上传的文件对象
				SmartFile file = smartUpload.getFiles().getFile(0);//获取第一个文件，因为只能上传一个文件
				//获取上传文件的名称
				String imgpath = "img/" + file.getFilePathName();
				Book book=new Book();
				book.setName(name);
				book.setDetail(detail);
				book.setCountbook(count);
				book.setPay(pay);
				book.setTypeid(typeid);
				book.setImg(imgpath);
				book.setAuthor(author);
				book.setPublicer(publicer);
				book.setDatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				int result=bookservice.addBook(book);
				if (result == 1) {
					//保存上传文件
					smartUpload.save("/img");
					out.write("<script>" + "alert('添加书籍成功！');"
							+ "window.location.href='" + request.getContextPath()
							+ "/BookServlet?action=findAllAdmin&&curPage=1'; " + "</script>");
				}else{
					out.write("<script>" + "alert('添加书籍失败！');"
							+ "window.location.href='" + request.getContextPath()
							+ "/BookServlet?action=findAllAdmin&&curPage=1'; " + "</script>");
				}
				
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void findTypesAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Types> types=typeservice.getTypes();
		request.setAttribute("types", types);
		request.getRequestDispatcher("/admin/addbook.jsp").forward(request,response);
	}

	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void findBooksAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		int curpagestr=0;

		if(curPage==null||"".equals(curPage)){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page page=new Page();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=bookservice.getBooks(page);
		
		
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/index/books.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
