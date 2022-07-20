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

import com.java.util.JsonUtil;
import com.java.util.Page;
import com.java.util.Page1;

import service.BookService;
import service.OrderService;
import service.UserService;
import booklibrary.model.Orders;
import booklibrary.model.User;
import booklibrary.service.impl.BookServiceImpl;
import booklibrary.service.impl.OrderServiceImpl;
import booklibrary.service.impl.UserServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserService userservice=new UserServiceImpl();
    OrderService orderservice=new OrderServiceImpl();
    BookService bookservice=new BookServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		if(action.equals("borrow")){//借书
			borrow(request,response);
		}else if(action.equals("findAll")){
			findAll(request,response);
		}else if(action.equals("return")){
			returnbook(request,response);
		}else if(action.equals("findMyOrder")){
			findMyOrder(request,response);
		}else if(action.equals("findOrderByName")){
			findOrderByName(request,response);
		}else if(action.equals("updateOrder")){
			updateOrder(request,response);
		}else if(action.equals("findView")){
			findView(request,response);
		}else if(action.equals("findViewTime")){
			findViewTime(request,response);
		}
	}
	
	private void findViewTime(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		List<Orders> orders=orderservice.getOrderViewTime();
		String jsonString=JsonUtil.ObjectRoJsonString(orders);
		System.out.println(jsonString);
		out.write(jsonString);
		out.flush();
		out.close();
	}

	private void findView(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		List<Orders> orders=orderservice.getOrderView();
		String jsonString=JsonUtil.ObjectRoJsonString(orders);
		System.out.println(jsonString);
		out.write(jsonString);
		out.flush();
		out.close();
		
		
	}

	private void updateOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		int result=orderservice.updateOrder();
		
			
		int curpagestr=1;
				
				//把当前页码赋值给Page的curpage属性
		Page1 page=new Page1();
		page.setCurPage(curpagestr);
				//调用Service分页查询方法
		page=orderservice.findOrders(page);
				//request绑定查询结果
		request.setAttribute("page", page);
				
				//请求转发
		request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
		
	}

	private void findOrderByName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		String name=request.getParameter("name");
		String struts=request.getParameter("struts");
		int curpagestr=1;
		if("".equals(curPage)||curPage.equals(null)||curPage==null){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page1 page=new Page1();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=orderservice.findOrderByName(page,name,struts);
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
	}

	private void findMyOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		String userid=request.getParameter("userid");
		int curpagestr=1;
		if("".equals(curPage)||curPage.equals(null)||curPage==null){
			curpagestr=1;
		}else{
			curpagestr=Integer.parseInt(curPage);
		}
		//把当前页码赋值给Page的curpage属性
		Page1 page=new Page1();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=orderservice.findOrders(page, userid);
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/index/myorders.jsp").forward(request, response);
	}

	private void returnbook(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String bookid=request.getParameter("bookid");
		String orderid=request.getParameter("id");
		int result=0;
		result=orderservice.returnbook(orderid);
		if(result==1){
			result=bookservice.returnbook(bookid);
			if(result==1){
				out.write("<script>"+"alert('还书成功！');"
						+"window.location.href='"+request.getContextPath()+"/OrderServlet?action=findAll&&curPage=1'; "
							+"</script>");
			}else{
				out.write("<script>"+"alert('还书失败！');"
						+"window.location.href='"+request.getContextPath()+"/OrderServlet?action=findAll&&curPage=1'; "
							+"</script>");
			}
		}else{
			out.write("<script>"+"alert('还书失败！');"
					+"window.location.href='"+request.getContextPath()+"/OrderServlet?action=findAll&&curPage=1'; "
						+"</script>");
		}
	}

	private void findAll(HttpServletRequest request,
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
		Page1 page=new Page1();
		page.setCurPage(curpagestr);
		//调用Service分页查询方法
		page=orderservice.findOrders(page);
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
	}

	private void borrow(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String bookid=request.getParameter("bookid");
		String tel=request.getParameter("tel");
		User user=new User();
		user=userservice.findUserByTel(tel);
		if(user!=null){
			Orders orders=new Orders();
			String endtime=request.getParameter("endtime");
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=df.format(new Date());
			String orderid=time.replaceAll("[[\\s-:punct:]]","");
			orders.setBookid(bookid);
			orders.setUserid(String.valueOf(user.getId()));
			orders.setBegintime(time);
			orders.setOrderid(orderid+user.getId());
			orders.setEndtime(endtime);
			orders.setStruts("借阅中");
			int result=0;
			result=orderservice.addOrder(orders);
			if(result==1){
				out.write("<script>"+"alert('借阅成功！');"
						+"window.location.href='"+request.getContextPath()+"/BookServlet?action=findAllAdmin'; "
							+"</script>");
			}else{
				out.write("<script>"+"alert('借阅失败！');"
						+"window.location.href='"+request.getContextPath()+"/BookServlet?action=findAllAdmin'; "
							+"</script>");
			}
			
		}else{
			out.write("<script>"+"alert('当前用户不存在！');"
					+"window.location.href='"+request.getContextPath()+"/BookServlet?action=findAllAdmin'; "
						+"</script>");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
