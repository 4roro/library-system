package service;

import java.util.List;

import com.java.util.Page1;

import booklibrary.model.Orders;

public interface OrderService {

	int addOrder(Orders orders);
	public Page1 findOrders(Page1 page);
	int returnbook(String orderid);
	public Page1 findOrders(Page1 page,String userid);
	Page1 findOrderByName(Page1 page, String name, String struts);
	int updateOrder();
	public List<Orders> getOrderView();
	public List<Orders> getOrderViewTime();

}
