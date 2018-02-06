package club.longyi.common.persistence;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hibernate查询辅助工具，使分页查询更方便
 * @author Master.Xia v1.0 Date:2017年1月14日17:39:17
 * @author Master.Xia v2.0 Date:2017年10月18日14:55:25
 */
public class HibernateHelper {
	
	public static void main(String[] args) {
		String  str="SUN公司被Oracle收购，是否意味着java被逼上了死路？";
		str = "from User";
        String s = "\\d+.\\d+|\\w+";
        Pattern  pattern=Pattern.compile(s);  
        Matcher  ma=pattern.matcher(str);  
   
        while(ma.find()){  
            System.out.println(ma.group());  
        }  
        
        /*
        String hql = "";
        System.out.println("--- "+hql);
        Pattern  pattern=Pattern.compile("\\d+.\\d+|\\w+");  
        Matcher  ma=pattern.matcher(hql);
        
        String table = null;
        for(int i=1;ma.find();i++){
        	table = ma.group();
        	if(i==2){
        		break;
        	}
        }

*/
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Paging<?> find(final String hql, final int page,
			final int rows,final Object[] params,Session session) {
        String countStr = "select count(1) "+hql.substring(hql.toLowerCase().indexOf("from"));
		Query query = session.createQuery(hql);
		Query countQuery = session.createQuery(countStr);
		for(int i=0;(params!=null&&i<params.length);i++){
			query.setParameter(i, params[i]);
			countQuery.setParameter(i, params[i]);
		}
        int count = ((Long) countQuery.uniqueResult()).intValue();
		int firstResult = (page - 1) * rows;	//开始位置
		query.setFirstResult(firstResult);
		query.setMaxResults(rows);
		List<Object> data = query.getResultList();
		return new Paging<Object>(data, page, count, rows);
	}
	
	public static Paging<?> find(final String hql, final int page,final int rows,Session session){
		return find(hql, page, rows, new String[]{}, session);
	}
	
	public static Paging<?> find(Class<?> c,int page, int rows,Session session){
		return find("from "+c.getName(), page, rows, session);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Paging<T> findByProperty(
			Class<T> c,String property,Object value, 
			int page, int rows,Session session){
		String hql = "from "+c.getName()+" where "+property+"=?";
		return (Paging<T>) find(hql, page, rows, new Object[]{value}, session);
	}
	
	public static Object findOne(String hql, Object[] params,Session session){
		List<?> list = find(hql, 1, 1, params,session).getList();
		return list.size()>0? list.get(0):null;
	}
	
	public static <T> T findOneByProperty(Class<T> c, String property, Object value,Session session){
		List<T> list = findByProperty(c, property, value, 1, 1,session).getList();
		return list.size()>0? list.get(0):null;
	}
	
}
