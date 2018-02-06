package club.longyi.common.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * 分页支持类,代表一页数据
 * 
 * @author 夏增明
 * @version 1.0 create date 2013-10-5
 * @version 1.1 create data 2015-08-27
 * @version 2.0 create data 2016-03-16
 */
@SuppressWarnings("serial")
public class Paging<T> implements Serializable {

	private int prev;		//上一页
	private int now;		//当前页
	private int next;		//下一页
	private int pageSize;	//每页记录数
	private int totalPage;	//总页数
	private long totalCount;//总记录数
	private List<T> list;	//数据集合

	private boolean isFirst;//是否第一页
	private boolean isLast;	//是否最后页
	
	/**
	 * @param nowPage 当前页
	 * @param count 总数据条数
	 * @param list 数据集合
	 * @param pageSize 每页显示数据数
	 */
	public Paging(List<T> list, int nowPage, long count, int pageSize) {
		this.list = list;
		
		this.now = nowPage;
		
		this.totalCount = count;
		this.pageSize = pageSize;
		this.totalPage = (int) ((count + pageSize - 1) / pageSize);
		this.prev = nowPage-1>1? nowPage-1:1;
		this.next = nowPage>=totalPage? totalPage: nowPage + 1;
		this.isFirst = nowPage==1;
		this.isLast = nowPage==totalPage;
	}
	
	/**
	 * 上一页的页码
	 * @return
	 */
	public int getPrev() {
		return prev;
	}
	/**
	 * 当前页的页码
	 * @return
	 */
	public int getNow() {
		return now;
	}
	/**
	 * 下一页的页码
	 * @return
	 */
	public int getNext() {
		return next;
	}
	/**
	 * 当前是否第一页
	 * @return
	 */
	public boolean isFirst() {
		return isFirst;
	}
	/**
	 * 当前是否最后一页
	 * @return
	 */
	public boolean isLast() {
		return isLast;
	}
	/**
	 * 总页数
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * 数据集合
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}
	/**
	 * 总记录数
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}
	/**
	 * 每页记录数
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	
}
