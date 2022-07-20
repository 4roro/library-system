package booklibrary.service.impl;

import java.util.List;

import com.java.util.Page;

import dao.NoticeDao;
import booklibrary.dao.impl.NoticeDaoImpl;
import booklibrary.model.Notice;
import service.NoticeService;

public class NoticeServiceImpl implements NoticeService{
	NoticeDao noticedao=new NoticeDaoImpl();
	@Override
	public List<Notice> getAll() {
		// TODO Auto-generated method stub
		return noticedao.getAll();
	}
	@Override
	public Page getNoticeAll(Page page) {
		// TODO Auto-generated method stub
		return noticedao.getNoticeAll(page);
	}
	@Override
	public int deleteNotice(int id) {
		// TODO Auto-generated method stub
		return noticedao.deleteNotice(id);
	}
	@Override
	public int addnotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticedao.addnotice(notice);
	}

}
