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




import service.BookService;
import service.NoticeService;
import booklibrary.model.Book;
import booklibrary.model.Notice;
import booklibrary.service.impl.BookServiceImpl;
import booklibrary.service.impl.NoticeServiceImpl;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     NoticeService noticeservice=new NoticeServiceImpl();  
     BookService bookservice=new BookServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
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
		if(action.equals("findNotice")){
			findNotice(request,response);
		}else if(action.equals("findNoticeAdmin")){
			findNoticeAdmin(request,response);
		}else if(action.equals("delete")){
			delete(request,response);
		}else if(action.equals("add")){
			add(request,response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(new Date());
		Notice notice=new Notice();
		System.out.println(title);
		notice.setTitle(title);
		notice.setDetail(content);
		notice.setDatetime(time);
		int result=noticeservice.addnotice(notice);
		if(result==1){
			out.write("<script>"+"alert('添加公告成功');"
					+"window.location.href='"+request.getContextPath()+"/NoticeServlet?action=findNoticeAdmin&&curPage=1'; "
						+"</script>");
		}else{
			out.write("<script>"+"alert('添加公告失败');"
					+"window.location.href='"+request.getContextPath()+"/NoticeServlet?action=findNoticeAdmin&&curPage=1'; "
						+"</script>");
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();	
		String name=request.getParameter("id");
				int id=Integer.valueOf(name);
				String curPage=request.getParameter("curpage");
				int curpagestr=0;
				if(curPage==null||"".equals(curPage)){
					curpagestr=1;
				}else{
					curpagestr=Integer.parseInt(curPage);
				}
				//System.out.print(id);
				//types.setId(id);
				int result=noticeservice.deleteNotice(id);
				if(result==1){
					out.write("<script>"+"alert('删除成功');"
							+"window.location.href='"+request.getContextPath()+"/NoticeServlet?action=findNoticeAdmin&&curPage=1'; "
								+"</script>");
				}else if(result==0){
					out.write("<script>"+"alert('删除失败');"
							+"window.location.href='"+request.getContextPath()+"/NoticeServlet?action=findNoticeAdmin&&curPage=1'; "
								+"</script>");
				}
	}

	private void findNoticeAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		int curpagestr=1;
		if("".equals(curPage)||curPage.equals(null)||curPage==null){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page page=new Page();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=noticeservice.getNoticeAll(page);
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/admin/notice.jsp").forward(request, response);
	}

	private void findNotice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Book> booknew=bookservice.getBookSListNew("");
		request.setAttribute("booknew", booknew);
		List<Notice> notice=noticeservice.getAll();
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/index/notice.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
