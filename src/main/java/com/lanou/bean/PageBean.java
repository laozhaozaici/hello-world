package com.lanou.bean;

import java.util.List;

public class PageBean<T> {

	/**
	 *   都一页的内容
	 */
	private List<T> list;
	/**
	 *  页码
	 */
	private int pageNo;
	/**
	 * 一页显示多少条
	 */
	private int pageSize;
	/**
	 * 总条数
	 */
	private int totalCount;
	/**
	 *  总页数
	 */
	private int totalNumber;
	/**
	 *  当前页数
	 */
	private int currentNo;
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalNumber() {
		return totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
	}
	public int getCurrentNo() {
		return currentNo;
	}
	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}
	
	
	
}
