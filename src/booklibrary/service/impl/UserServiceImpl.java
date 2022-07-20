package booklibrary.service.impl;

import java.util.List;

import com.java.util.Page;

import booklibrary.dao.impl.UserDaoImpl;
import booklibrary.model.Admin;
import booklibrary.model.User;
import dao.UserDao;
import service.UserService;

public class UserServiceImpl implements UserService{
	UserDao userdao=new UserDaoImpl();

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userdao.login(user);
	}

	@Override
	public int adduser(User user) {
		// TODO Auto-generated method stub
		return userdao.adduser(user);
	}

	@Override
	public Page getUser(Page page, String name) {
		// TODO Auto-generated method stub
		return userdao.getUser(page, name);
	}

	@Override
	public User findUserByTel(String tel) {
		// TODO Auto-generated method stub
		return userdao.findUserByTel(tel);
	}

	@Override
	public Admin adminlogin(Admin admin) {
		// TODO Auto-generated method stub
		return userdao.adminlogin(admin);
	}

	@Override
	public int updateadmin(Admin admin, String newpassword) {
		// TODO Auto-generated method stub
		return userdao.updateadmin(admin, newpassword);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return userdao.delete(id);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userdao.update(user);
	}

	@Override
	public int addadmin(Admin admin) {
		// TODO Auto-generated method stub
		return userdao.addadmin(admin);
	}

	@Override
	public List<Admin> admins() {
		// TODO Auto-generated method stub
		return userdao.admins();
	}
	
}
