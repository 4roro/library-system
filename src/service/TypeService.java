package service;

import java.util.List;

import com.java.util.Page;

import booklibrary.model.Types;

public interface TypeService {
	public List<Types> getTypes();//获取书籍类别
	
	public Page getTypesAdmin(Page page);

	public int addtype(Types type);

	public int deleteType(int id);

	public Types findByid(String id);

	public int updatetype(Types types);
}
