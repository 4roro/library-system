package booklibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.util.Page;

import service.UserService;
import booklibrary.model.Admin;
import booklibrary.model.Book;
import booklibrary.model.Types;
import booklibrary.model.User;
import booklibrary.service.impl.UserServiceImpl;



/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserService userservice=new UserServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		if(action.equals("login")){
			login(request,response);
		}else if(action.equals("add")){
			add(request,response);
		}else if(action.equals("register")){
			register(request,response);
		}else if(action.equals("findUsers")){
			findUsers(request,response);
		}else if (action.equals("adminlogin")) {
			adminlogin(request, response);
		} else if (action.equals("updateadmin")) {
			updateadmin(request, response);
		} else if (action.equals("delete")) {
			delete(request, response);
		} else if (action.equals("update")) {
			update(request, response);
		} else if (action.equals("logout")) {
			logout(request, response);
		} else if (action.equals("addadmin")) {
			addadmin(request, response);
		}  else if (action.equals("findAdmin")) {
			findAdmin(request, response);
		} 
	}
	private void findAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Admin> admins=userservice.admins();
		request.setAttribute("admins", admins);
		request.getRequestDispatcher("/admin/admins.jsp").forward(request,response);
	}

	private void addadmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String adminid = request.getParameter("adminid");
		String password = request.getParameter("password");
		
		Admin admin = new Admin();
		admin.setAdminid(adminid);
		admin.setPassword(password);
		int result = userservice.addadmin(admin);
		if (result == 1) {
			out.write("<script>" + "alert('success to add');"
					+ "window.location.href='" + request.getContextPath()
					+ "/UserServlet?action=findAdmin&&curPage=1'; " + "</script>");
		} else {
			out.write("<script>" + "alert('fail to add');"
					+ "window.location.href='" + request.getContextPath()
					+ "/UserServlet?action=findAdminn&&curPage=1'; " + "</script>");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		out.write("<script>"+"alert('退出成功!');"
				+"window.location.href='"+request.getContextPath()+"/index/myacount.jsp'; "
					+"</script>");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String newpassword=request.getParameter("newpassword");
		User user=new User();
		user.setId(Integer.valueOf(id));
		user.setHoppy(password);
		user.setPassword(newpassword);
		
		int result=0;
		result=userservice.update(user);
		if(result==1){
			out.write("<script>"+"alert('更新成功!');"
					+"window.location.href='"+request.getContextPath()+"/index/bookdetail.jsp'; "
						+"</script>");
		}else{
			out.write("<script>"+"alert('更新失败!');"
					+"window.location.href='"+request.getContextPath()+"/index/bookdetail.jsp'; "
						+"</script>");
		}
		
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
		int result=userservice.delete(id);
		if(result==1){
			out.write("<script>"+"alert('删除成功');"
					+"window.location.href='"+request.getContextPath()+"/UserServlet?action=findUsers&&curPage=1'; "
						+"</script>");
		}else {
			out.write("<script>"+"alert('删除失败');"
					+"window.location.href='"+request.getContextPath()+"/UserServlet?action=findUsers&&curPage=1'; "
						+"</script>");
		}
	}

	private void updateadmin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String adminid = request.getParameter("adminid");
		String password = request.getParameter("password");
		String newpassword = request.getParameter("newpassword");
		Admin admin = new Admin();
		admin.setAdminid(adminid);
		admin.setPassword(password);
		int result = userservice.updateadmin(admin, newpassword);
		if (result == 1) {
			out.write("<script>" + "alert('success to update');"
					+ "window.parent.location.href='"
					+ request.getContextPath() + "/admin/login.jsp'; "
					+ "</script>");
		} else {
			out.write("<script>" + "alert('success to update');"
					+ "window.parent.location.href='"
					+ request.getContextPath() + "/admin/login.jsp'; "
					+ "</script>");
		}
	}

	private void adminlogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// 创建session
		HttpSession session = request.getSession();
		String adminid = request.getParameter("adminid");
		String password = request.getParameter("password");
		Admin admin = new Admin();
		admin.setAdminid(adminid);
		admin.setPassword(password);
		admin = userservice.adminlogin(admin);
		request.setAttribute("admin", admin);
		if (admin != null) {
			// 把用户信息存在session中，让别的页面共享该数据

			session.setAttribute("admin", admin);
			// out.print("<a href='request.getContextPath()'+'/web/index.jsp'>main</a>");
			out.write("<script>" + "alert('success to login');"
					+ "window.location.href='" + request.getContextPath()
					+ "/admin/index.jsp'; " + "</script>");

		} else {
			out.write("<script>" + "alert('登录失败');"
					+ "window.location.href='" + request.getContextPath()
					+ "/admin/login.jsp'; " + "</script>");
		}
	}

	private void findUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curPage=request.getParameter("curPage");
		String name=request.getParameter("name");
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
		page=userservice.getUser(page, name);
		
		
		//request绑定查询结果
		request.setAttribute("page", page);
		
		//请求转发
		request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String card=request.getParameter("IDCardNumber");
		String address=request.getParameter("address");
		User user=new User();
		user.setName(name);
		user.setTel(tel);
		user.setPassword(tel);
		user.setCard(card);
		user.setAddress(address);
		
		int result=userservice.adduser(user);
		if(result==1){
			out.write("<script>"+"alert('添加用户成功');"
					+"window.location.href='"+request.getContextPath()+"/UserServlet?action=findUsers&&curPage=1'; "
						+"</script>");
		}else{
			out.write("<script>"+"alert('添加用户失败,当前账号已注册');"
					+"window.location.href='"+request.getContextPath()+"/UserServlet?action=findUsers&&curPage=1'; "
						+"</script>");
		}
	}
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String password=request.getParameter("password");
		String card=request.getParameter("IDCardNumber");
		String address=request.getParameter("address");
		User user=new User();
		user.setName(name);
		user.setTel(tel);
		user.setPassword(password);
		user.setCard(card);
		user.setAddress(address);

		int result=userservice.adduser(user);
		if(result==1){
			out.write("<script>"+"alert('添加用户成功');"
					+ "window.location.href='" + request.getContextPath() + "/index/myacount.jsp';"
					+"</script>");
		}else{
			out.write("<script>"+"alert('添加用户失败,当前账号已注册');"
					+ "window.location.href='" + request.getContextPath() + "/index/myacount.jsp';"
					+"</script>");
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		String name=request.getParameter("username");
		String pwd=request.getParameter("password");
		User user=new User();
		user.setName(name);
		user.setPassword(pwd);
		user=userservice.login(user);
		
		if(user!=null){
		 session.setAttribute("user", user);
		 out.print("<script>"
					+ "alert('登录成功！');"
					+ "window.location.href='" + request.getContextPath() + "/index/index.jsp';"
					+ "</script>");
		}else{
			out.print("<script>"
					+ "alert('登录失败，请检查用户名或密码!');"
					+ "window.location.href='" + request.getContextPath() + "/index/myacount.jsp';"
					+ "</script>");
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
