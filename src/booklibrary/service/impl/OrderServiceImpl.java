package booklibrary.service.impl;

import java.util.List;

import com.java.util.DBUtil;
import com.java.util.Page1;

import dao.BookDao;
import dao.OrderDao;
import booklibrary.dao.impl.BookDaoImpl;
import booklibrary.dao.impl.OrderDaoImpl;
import booklibrary.model.Orders;
import service.OrderService;

public class OrderServiceImpl implements OrderService{
	OrderDao orderdao=new OrderDaoImpl();
	BookDao bookdao=new BookDaoImpl();
	DBUtil dbutil=new DBUtil();
	@Override
	public int addOrder(Orders orders) {
		// TODO Auto-generated method stub
		int result=0;
		result=bookdao.borrow(dbutil,orders.getBookid());
		if(result==1){
			result=orderdao.addOrder(dbutil,orders);
		}
		return result;
	}
	@Override
	public Page1 findOrders(Page1 page) {
		// TODO Auto-generated method stub
		return orderdao.findOrders(page);
	}
	@Override
	public int returnbook(String orderid) {
		// TODO Auto-generated method stub
		return orderdao.returnbook(orderid);
	}
	@Override
	public Page1 findOrders(Page1 page, String userid) {
		// TODO Auto-generated method stub
		return orderdao.findOrders(page, userid);
	}
	@Override
	public Page1 findOrderByName(Page1 page, String name, String struts) {
		// TODO Auto-generated method stub
		return orderdao.findOrderByName(page, name, struts);
	}
	@Override
	public int updateOrder() {
		// TODO Auto-generated method stub
		return orderdao.updateOrder();
	}
	@Override
	public List<Orders> getOrderView() {
		// TODO Auto-generated method stub
		return orderdao.getOrderView();
	}
	@Override
	public List<Orders> getOrderViewTime() {
		// TODO Auto-generated method stub
		return orderdao.getOrderViewTime();
	}

}
