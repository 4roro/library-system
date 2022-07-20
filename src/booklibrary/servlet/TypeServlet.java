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

import booklibrary.model.Admin;
import booklibrary.model.Book;
import booklibrary.model.Notice;
import booklibrary.model.Types;
import booklibrary.service.impl.BookServiceImpl;
import booklibrary.service.impl.TypeServiceImpl;
import service.BookService;
import service.TypeService;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      TypeService typeservice=new TypeServiceImpl(); 
      BookService bookservice=new BookServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
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
			findTypesAll(request,response);
		}if(action.equals("findAllAdmin")){//查询所有
			findTypesAllAdmin(request,response);
		}else if(action.equals("add")){
			add(request,response);
		}else if(action.equals("delete")){
			delete(request,response);
		}else if(action.equals("findTypeById")){
			findById(request,response);
		}else if(action.equals("update")){
			updateType(request,response);
		}
	}

	private void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		Types types=typeservice.findByid(id);
		request.setAttribute("type", types);
		request.getRequestDispatcher("/admin/updatetype.jsp").forward(request,response);
	
	}

	private void updateType(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		Types types=new Types();
		int id1=Integer.valueOf(id);
		types.setId(id1);
		types.setName(name);
		types.setDetail(detail);
		int result = typeservice.updatetype(types);
		if (result == 1) {
			out.write("<script>" + "alert('success to update');"
					+ "window.parent.location.href='"
					+ request.getContextPath() + "/admin/index.jsp'; "
					+ "</script>");
		} else {
			out.write("<script>" + "alert('success to update');"
					+ "window.parent.location.href='"
					+ request.getContextPath() + "/admin/index.jsp'; "
					+ "</script>");
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
				int result=typeservice.deleteType(id);
				if(result==1){
					out.write("<script>"+"alert('删除成功');"
							+"window.location.href='"+request.getContextPath()+"/TypeServlet?action=findAllAdmin&&curPage=1'; "
								+"</script>");
				}else if(result==0){
					out.write("<script>"+"alert('删除失败');"
							+"window.location.href='"+request.getContextPath()+"/TypeServlet?action=findAllAdmin&&curPage=1'; "
								+"</script>");
				}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Types type=new Types();
		type.setName(title);
		type.setDetail(content);
		int result=typeservice.addtype(type);
		if(result==1){
			out.write("<script>"+"alert('添加类别成功');"
					+"window.location.href='"+request.getContextPath()+"/TypeServlet?action=findAllAdmin'; "
						+"</script>");
		}else{
			out.write("<script>"+"alert('添加类别失败');"
					+"window.location.href='"+request.getContextPath()+"/TypeServlet?action=findAllAdmin'; "
						+"</script>");
		}
	}

	private void findTypesAllAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Types> types=typeservice.getTypes();
		request.setAttribute("types", types);
		List<Book> book=bookservice.getBookSListNew("");
		request.setAttribute("books", book);
		request.getRequestDispatcher("/admin/types.jsp").forward(request,response);
	}

	private void findTypesAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Types> types=typeservice.getTypes();
		request.setAttribute("types", types);
		List<Book> book=bookservice.getBookSListNew("");
		request.setAttribute("books", book);
		request.getRequestDispatcher("/index/booktype.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
