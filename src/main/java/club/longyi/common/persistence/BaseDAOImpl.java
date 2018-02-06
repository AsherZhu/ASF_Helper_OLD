package club.longyi.common.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 基础实现类 + 分页功能
 * 
 * @author 夏增明
 * @version 1.0 create date 2013-10-5
 * @version 2.0 create date 2016-03-16 
 * @version 3.0 create date 2017-04-05
 * @version 4.0 create 2017年4月20日22:08:56
 */
@Repository //创建一个bean放入Spring容器
@SuppressWarnings("unchecked")
public class BaseDAOImpl implements BaseDAO {
	
	@Autowired //Spring根据类型自动注入，无需get、set，可以private
	private SessionFactory factory;

	/**
	 * 分页查询的核心实现
	 * @param hql hql语句
	 * @param page 当前页
	 * @param rows 长度
	 * @param params hql中的问号参数
	 * @return PagingSupport
	 */
	@SuppressWarnings({ "rawtypes" })
	public Paging<?> queryForPage(final String hql, final int page,final int rows,final Object[] params) {
		Session session = factory.getCurrentSession();
		return (Paging) HibernateHelper.find(hql, page, rows, params, session);
	}
	
	/**
	 * 分页查询
	 * @param hql hql语句
	 * @param page 当前页
	 * @param rows 长度
	 * @return PagingSupport
	 */
	public Paging<?> queryForPage(final String hql, final int page,final int rows) {
		return queryForPage(hql, page, rows, null);
	}

	
	public void save(Object obj) throws Exception {
		factory.getCurrentSession().save(obj);
	}
	
	public void delete(Object obj) throws Exception {
		factory.getCurrentSession().delete(obj);
	}
	
	public void update(Object obj) throws Exception {
		factory.getCurrentSession().update(obj);
	}

	
	public Paging<?> find(String hql, int page, int rows)throws Exception {
		return queryForPage(hql, page, rows);
	}
	
	
	public Paging<?> find(String hql, int page, int rows,Object[] params) throws Exception {
		return queryForPage(hql, page, rows,params);
	}
	
	
	public <T> Paging<T>  find(Class<T> c,int page, int rows)throws Exception {
		return (Paging<T>) queryForPage("from " + c.getName(), page, rows);
	}
	
	
	public <T> Paging<T> findByProperty(Class<T> c,String property,Object value, int page, int rows)throws Exception {
		Session session = factory.getCurrentSession();
		return (Paging<T>) HibernateHelper.findByProperty(c, property, value, page, rows, session);
	}

	
	public <T> T findById(Class<T> c,Serializable id) throws Exception {
		return factory.getCurrentSession().get(c, id);
	}

	
	public Object findOne(String hql, Object[] params) throws Exception {
		Session session = factory.getCurrentSession();
		return HibernateHelper.findOne(hql, params, session);
	}

	
	public <T> T findOneByProperty(Class<T> c, String property, Object value)throws Exception {
		Session session = factory.getCurrentSession();
		return (T) HibernateHelper.findOneByProperty(c, property, value, session);
	}
	
	/* ----------------------- 3.0添加 ----------------------- */
	public <T> Paging<T> find(Class<T> c, String hql, int page, int rows) throws Exception {
		return (Paging<T>) find(hql, page, rows);
	}
	
	public <T> Paging<T> find(Class<T> c, String hql, int page, int rows, Object[] params)
			throws Exception {
		return (Paging<T>) find(hql, page, rows, params);
	}
	
	public <T> T findOne(Class<T> c, String hql, Object[] params) throws Exception {
		return (T) findOne(hql, params);
	}

	
	public <T> List<T> findAll(Class<T> c) throws Exception {
		return factory.getCurrentSession().createQuery("from "+c.getName()).list();
	}
	
}
