package service;

import java.util.List;

import com.java.util.Page;

import booklibrary.model.Notice;

public interface NoticeService {
	public List<Notice> getAll();
	public Page getNoticeAll(Page page) ;
	public int deleteNotice(int id);
	public int addnotice(Notice notice);
}
