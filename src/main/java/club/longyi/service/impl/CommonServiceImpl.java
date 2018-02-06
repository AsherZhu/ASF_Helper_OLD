package club.longyi.service.impl;


import club.longyi.common.persistence.BaseDAO;
import club.longyi.common.persistence.Paging;
import club.longyi.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


@Service
@Transactional
public class CommonServiceImpl implements CommonService {

	@Resource(name="baseDAOImpl")
	protected BaseDAO dao;

	
	public void save(Object obj) throws Exception {
		dao.save(obj);
	}
	
	public void save(Object[] objs) throws Exception {
		for(Object obj : objs){
			dao.save(obj);
		}
	}
	
	public void save(List<?> objs) throws Exception {
		for(Object obj : objs){
			dao.save(obj);
		}
	}
	
	public void update(Object obj) throws Exception {
		dao.update(obj);
	}

	
	public void delete(Object obj) throws Exception {
		dao.delete(obj);
	}

	
	public void delete(Class<?> c, Serializable id) throws Exception {
		dao.delete(dao.findById(c, id));
	}

	
	public void delete(Class<?> c, Serializable[] ids) throws Exception {
		for(int i=0;ids!=null&&i<ids.length;i++){
			dao.delete(dao.findById(c, ids[i]));
		}
	}

	
	public <T> T findById(Class<T> c, Serializable id) throws Exception {
		return dao.findById(c, id);
	}

	
	public <T> Paging<T> find(Class<T> c, int page, int rows) throws Exception {
		return dao.find(c, page, rows);
	}

	
	public <T> Paging<T> find(Class<T> c, String hql, int page, int rows, Object[] params) throws Exception {
		return dao.find(c,hql, page, rows, params);
	}
	
	public <T> Paging<T> findByProperty(Class<T> c, String property, Object value,int page,int rows) throws Exception {
		return dao.findByProperty(c, property, value, page, rows);
	}
	
	public <T> List<T> findByProperty(Class<T> c, String property, Object value) throws Exception {
		return dao.findByProperty(c, property, value, 1, Integer.MAX_VALUE).getList();
	}

	
	public <T> T findOne(Class<T> c, String hql, Object[] params) throws Exception {
		return dao.findOne(c, hql, params);
	}

	
	public <T> T findOneByProperty(Class<T> c, String property, Object value) throws Exception {
		return dao.findOneByProperty(c, property, value);
	}

	
	public <T> List<T> findAll(Class<T> c) throws Exception {
		return dao.findAll(c);
	}
	
	public <T> Paging<T> find(Class<T> c, String hql, int page, int rows) throws Exception {
		return dao.find(c, hql, page, rows);
	}

}
