package com.yinhe.dama.aop;

/**
 * 分页Model类
 * @author 
 *
 */
@SuppressWarnings("unused")
public class PageBean {

	public final static int PAGE_SHOW_COUNT = 10;
	
	private int page  = 0; // 第几页
	private int pageSize  = 0; // 每页记录数
	private int total = 0;  // 总数
	
	private int start;  // 起始页
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize > 0 ? pageSize : PAGE_SHOW_COUNT;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStart() {
		return (page-1)*pageSize;
	}
	
}
