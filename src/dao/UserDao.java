package dao;

import java.util.List;

import com.java.util.Page;

import booklibrary.model.Admin;
import booklibrary.model.User;

public interface UserDao {
	public User  login(User user);
	public int adduser(User user);
	public Page  getUser(Page page,String name);
	public User findUserByTel(String tel);
	public Admin adminlogin(Admin admin);
	public int updateadmin(Admin admin, String newpassword);
	public int delete(int id);
	public int update(User user);
	public int addadmin(Admin admin);
	public List<Admin> admins() ;
}
