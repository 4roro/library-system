package booklibrary.service.impl;

import java.util.List;

import com.java.util.Page;

import dao.TypeDao;
import booklibrary.dao.impl.TypeDaoImpl;
import booklibrary.model.Types;
import service.TypeService;

public class TypeServiceImpl implements TypeService{
	TypeDao typedao=new TypeDaoImpl();
	@Override
	public List<Types> getTypes() {
		// TODO Auto-generated method stub
		return typedao.getTypes();
	}
	@Override
	public Page getTypesAdmin(Page page) {
		// TODO Auto-generated method stub
		return typedao.getTypesAdmin(page);
	}
	@Override
	public int addtype(Types type) {
		// TODO Auto-generated method stub
		return typedao.addtype(type);
	}
	@Override
	public int deleteType(int id) {
		// TODO Auto-generated method stub
		return typedao.deleteType(id);
	}
	@Override
	public Types findByid(String id) {
		// TODO Auto-generated method stub
		return typedao.findById(id);
	}
	@Override
	public int updatetype(Types types) {
		// TODO Auto-generated method stub
		return typedao.update(types);
	}

}
