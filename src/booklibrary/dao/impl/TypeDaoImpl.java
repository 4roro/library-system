package booklibrary.dao.impl;

import java.util.List;

import booklibrary.model.Types;

import com.java.util.DBUtil;
import com.java.util.Page;

import dao.TypeDao;

public class TypeDaoImpl implements TypeDao{
	DBUtil dbutil=new DBUtil();

	@Override
	public List<Types> getTypes() {
		// TODO Auto-generated method stub
		String sql="select * from types";
		List list=null;
		try {
			list=dbutil.getQueryList(Types.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public Page getTypesAdmin(Page page) {
		// TODO Auto-generated method stub
		String sql="select * from types";
		Page page1=null;
		page1=dbutil.getQueryPage(Types.class, sql,null, page);
		return page1;
	}
	@Override
	public int addtype(Types type) {
		// TODO Auto-generated method stub
		String sql="insert into types(name,detail) values(?,?)";
		Object[]obj={type.getName(),type.getDetail()};
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
	public int deleteType(int id) {
		// TODO Auto-generated method stub
		String sql="delete from types where id=?";
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
	@Override
	public Types findById(String id) {
		// TODO Auto-generated method stub
		String sql="select * from types where id=?";
		Object[] obj={id};
		Types types=null;
		try {
			types = (Types) dbutil.getObject(Types.class, sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}
	@Override
	public int update(Types types) {
		// TODO Auto-generated method stub
		String sql="update types set name=?,detail=?  where id=? ";
		Object[] obj={types.getName(),types.getDetail(),types.getId()};
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
