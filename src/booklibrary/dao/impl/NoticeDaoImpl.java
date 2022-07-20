package booklibrary.dao.impl;

import java.util.List;

import com.java.util.DBUtil;
import com.java.util.Page;

import booklibrary.model.Notice;
import dao.NoticeDao;


public class NoticeDaoImpl implements NoticeDao{
DBUtil dbutil=new DBUtil();
	@Override
	public List<Notice> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from notice";
		List list=null;
		try {
			list=dbutil.getQueryList(Notice.class, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public Page getNoticeAll(Page page) {
		// TODO Auto-generated method stub
		String sql="select * from notice";
		Page page1=null;
		
			page1 = dbutil.getQueryPage(Notice.class, sql, null, page);
		
		return page1;
	}
	public int deleteNotice(int id) {
		// TODO Auto-generated method stub
		String sql="delete from notice where id=?";
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
	public int addnotice(Notice notice) {
		// TODO Auto-generated method stub
		String sql="insert into notice(title,detail,datetime) values(?,?,?)";
		int result=0;
		Object[] obj={notice.getTitle(),notice.getDetail(),notice.getDatetime()};
		try {
			result=dbutil.execute(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
