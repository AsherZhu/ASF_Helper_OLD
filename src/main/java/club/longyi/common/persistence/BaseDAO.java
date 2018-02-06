package club.longyi.common.persistence;

import java.io.Serializable;
import java.util.List;


/**
 * 基础数据访问对象接口
 * 
 * @author 夏增明
 * @version 1.0 create date 2013-10-5 
 * @version 2.0 create date 2016-03-16 
 * @version 3.0 create date 2017-04-05
 */
public interface BaseDAO {

	/**
	 * 保存
	 * @param obj
	 */
	public abstract void save(Object obj) throws Exception;
	/**
	 * 删除
	 * @param obj
	 */
	public abstract void delete(Object obj) throws Exception;
	/**
	 * 更新
	 * @param obj
	 */
	public abstract void update(Object obj) throws Exception;

	/**
	 * 根据hql分页查询（无参数）
	 * @param hql 查询语句
	 * @param page 第几页
	 * @param rows 每页记录数
	 * @return
	 * @throws Exception
	 */
	public abstract Paging<?> find(String hql, int page, int rows) throws Exception;

	/**
	 * 根据hql分页查询（附加参数）
	 * @param hql 查询语句
	 * @param page 第几页
	 * @param rows 每页记录数
	 * @param params 传入HQL中的参数
	 * @return
	 * @throws Exception
	 */
	public abstract Paging<?> find(String hql, int page, int rows, Object[] params) throws Exception;

	/**
	 *
	 * 根据ID查询
	 * @param id
	 * @return 查找到的对象
	 */
	public abstract <T> T findById(Class<T> c, Serializable id) throws Exception;

	/**
	 * 根据属性进行分页查询
	 * @param c 哪个实体类
	 * @param property 哪个属性
	 * @param value 什么值
	 * @param page 第几页
	 * @param rows 每页记录数
	 * @return
	 * @throws Exception
	 */
	public abstract <T> Paging<T> findByProperty(Class<T> c, String property, Object value, int page, int rows)throws Exception;

	/**
	 * 对所有数据进行分页查询
	 * @param <T>
	 * @param page 当前页
	 * @param rows 每页数据量
	 * @return 查找到的对象集合
	 */
	public abstract <T> Paging<T> find(Class<T> c, int page, int rows) throws Exception;

	/**
	 * 通过HQL语句查询第一个对象
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public abstract Object findOne(String hql, Object[] params) throws Exception;

	/**
	 * 根据属性值查询第一个对象
	 * @param c 哪个实体类
	 * @param property 哪个属性
	 * @param value 什么值
	 * @return
	 * @throws Exception
	 */
	public abstract <T> T findOneByProperty(Class<T> c, String property, Object value)throws Exception;



	/* ----------------------- 3.0添加 ----------------------- */
	/**
	 * 根据hql分页查询（无参数）
	 * @param hql 查询语句
	 * @param page 第几页
	 * @param rows 每页记录数
	 * @return
	 * @throws Exception
	 */
	public abstract <T> Paging<T> find(Class<T> c, String hql, int page, int rows) throws Exception;
	/**
	 * 根据hql分页查询（附加参数）
	 * @param hql 查询语句
	 * @param page 第几页
	 * @param rows 每页记录数
	 * @param params 传入HQL中的参数
	 * @return
	 * @throws Exception
	 */
	public abstract <T> Paging<T> find(Class<T> c, String hql, int page, int rows, Object[] params) throws Exception;
	/**
	 * 通过HQL语句查询第一个对象
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public abstract <T> T findOne(Class<T> c, String hql, Object[] params) throws Exception;
	
	/* ----------------------- 4.0添加 ----------------------- */
	
	public abstract <T> List<T> findAll(Class<T> c)throws Exception;
}
