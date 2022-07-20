package booklibrary.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.java.util.DBUtil;
import com.java.util.Page1;

import booklibrary.model.Orders;
import dao.OrderDao;

public class OrderDaoImpl implements OrderDao{
	DBUtil dbutil=new DBUtil();
@Override
	public int addOrder(DBUtil dbutil, Orders orders) {
		// TODO Auto-generated method stub
		String sql="insert into orders (orderid,bookid,userid,begintime,endtime,struts) values(?,?,?,?,?,?)";
		Object[] obj={orders.getOrderid(),orders.getBookid(),orders.getUserid(),orders.getBegintime(),orders.getEndtime(),orders.getStruts()};
		int result=0;
		try {
			 result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
  public Page1 findOrders(Page1 page){
	  String sql="select orders.id id,orders.bookid bookid,orders.userid userid,orders.orderid orderid,users.name name,books.name book,orders.begintime begintime,orders.endtime endtime,orders.struts struts from orders inner join users on orders.userid=users.id inner join books on books.id=orders.bookid";
	  Page1 page1=null;
	  page1=dbutil.getQueryPage1(Orders.class, sql, null, page);
	return page1;
	  
  }
  public Page1 findOrders(Page1 page,String userid){
	  String sql="select orders.id id,books.img bookimg,orders.bookid bookid,orders.userid userid,orders.orderid orderid,users.name name,books.name book,orders.begintime begintime,orders.endtime endtime,orders.struts struts from orders inner join users on orders.userid=users.id inner join books on books.id=orders.bookid where users.id=?";
	  Object[] obj={userid};
	  Page1 page1=null;
	  page1=dbutil.getQueryPage1(Orders.class, sql, obj, page);
	return page1;
	  
  }
@Override
public int returnbook(String orderid) {
	// TODO Auto-generated method stub
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time=df.format(new Date());
	String sql="update orders set struts=?,endtime=? where id=?";
	Object[]obj={"已归还",time,orderid};
	int result=0;
	try {
		result=dbutil.execute(sql, obj);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}

public List<Orders> getOrderView(){
	  String sql="select books.name as name,count(bookid)as total  from orders inner join books on orders.bookid=books.id group by books.id";
		List orders=null;
		try {
			orders=dbutil.getQueryList(Orders.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
}

public List<Orders> getOrderViewTime(){
	String sql="select DATE_FORMAT(begintime,'%Y-%m-%d') begintime,count(id)as total  from orders  group by DATE_FORMAT(begintime,'%Y-%m-%d')";
	List orders=null;
	try {
		orders=dbutil.getQueryList(Orders.class, sql, null);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return orders;
	
}

@Override
public Page1 findOrderByName(Page1 page, String name, String struts) {
	// TODO Auto-generated method stub
	String sql="select orders.id id,orders.bookid bookid,orders.userid userid,orders.orderid orderid,users.name name,books.name book,orders.begintime begintime,orders.endtime endtime,orders.struts struts from orders inner join users on orders.userid=users.id inner join books on books.id=orders.bookid where users.name like '%"+name+"%' and orders.struts=? order by orders.id desc";
	  Page1 page1=null;
	  Object[]obj={struts};
	  page1=dbutil.getQueryPage1(Orders.class, sql, obj, page);
	return page1;
}
@Override
public int updateOrder() {
	// TODO Auto-generated method stub
	String sql="update orders set struts=?  where TO_DAYS( endtime) < TO_DAYS(NOW()) and struts=?";
	Object[] obj={"逾期","借阅中"};
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
