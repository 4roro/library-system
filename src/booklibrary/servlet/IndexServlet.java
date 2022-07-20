package booklibrary.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService bookservice=new BookServiceImpl();
    TypeService typeservice=new TypeServiceImpl();
    NoticeService noticeservice=new NoticeServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
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
		if(action.equals("index")){
			Index(request,response);
		}
	}

	private void Index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Book> books=bookservice.getBookSList();
		request.setAttribute("books", books);
		List<Book> booknew=bookservice.getBookSListNew("");
		request.setAttribute("booknew", booknew);
		List<Types> types=typeservice.getTypes();
		request.setAttribute("types", types);
		List<Notice> notice=noticeservice.getAll();
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/index/index.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
