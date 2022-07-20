package booklibrary.dao.impl;

import java.util.List;

import com.java.util.DBUtil;
import com.java.util.Page;

import booklibrary.model.Admin;
import booklibrary.model.Book;
import booklibrary.model.User;
import dao.UserDao;

public class UserDaoImpl implements UserDao{
	DBUtil dbutil=new DBUtil();
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		String sql="select * from users where tel=? and password=?";
		Object[] objects={user.getName(),user.getPassword()};
		User user1=null;
		try {
			user1=(User)dbutil.getObject(User.class, sql, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user1;
		
	}
	@Override
	public int adduser(User user) {
		// TODO Auto-generated method stub
		String sql="insert into users (name,tel,password,card,address) values(?,?,?,?,?)";
		Object[] obj={user.getName(),user.getTel(),user.getPassword(),user.getCard(),user.getAddress()};
		int result=0;
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int addadmin(Admin admin) {
		// TODO Auto-generated method stub
		String sql="insert into admin (adminid,password) values(?,?)";
		Object[] obj={admin.getAdminid(),admin.getPassword()};
		int result=0;
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Page  getUser(Page page,String name){
		String sql="";
		if(name==null||name.equals("")){
			sql="select * from users order by id desc";
		}else{
		    sql="select * from users where name like '%"+name+"%' order by id desc";
		}
		Page page1=null;
		page1=dbutil.getQueryPage(User.class, sql,null, page);
		return page1;
		
	}
	@Override
	public User findUserByTel(String tel) {
		// TODO Auto-generated method stub
		String sql="select * from users where tel=?";
		User user=new User();
		Object[] obj={tel};
		try {
			user=(User) dbutil.getObject(User.class, sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public Admin adminlogin(Admin admin) {
		// TODO Auto-generated method stub
		String sql="select * from admin where adminid=? and password=?";
		Object[] paramList={admin.getAdminid(),admin.getPassword()};
		User user1=null;
		Admin admin1=new Admin();
		try {
			admin1=(Admin) dbutil.getObject(Admin.class, sql, paramList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin1;
	}
	@Override
	public List<Admin> admins() {
		// TODO Auto-generated method stub
		String sql="select * from admin ";
		User user1=null;
		List admins=null;
		try {
			admins=dbutil.getQueryList(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admins;
	}
	@Override
	public int updateadmin(Admin admin, String newpassword) {
		// TODO Auto-generated method stub
		String sql="update admin set password=?  where adminid=? and password=?";
		Object[] obj={newpassword,admin.getAdminid(),admin.getPassword()};
		int result=0;
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from users where id=?";
		Object[] obj={id};
		int result=0;
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int update(User user){
		String sql="update users set password=? where id=? and password=?";
		Object[] obj={user.getPassword(),user.getId(),user.getHoppy()};
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
