package com.lanou.service;

import java.util.List;

import com.lanou.bean.PageBean;
import com.lanou.bean.QueryStatistics;
import com.lanou.dao.MonthBean;
import com.lanou.entity.BizClaimVoucherStatistics;
import com.lanou.entity.BizClaimVouyearStatistics;

public interface StatisticsService {

	/**
	 * 部门的月度统计
	 * @param deptId
	 * @return
	 */
	public PageBean<BizClaimVoucherStatistics> selectDeptMonthStatistics(int deptId,int pageNo,QueryStatistics q);
	
	/**
	 * 部门月度统计详情
	 * @param year
	 * @param month
	 * @param deptId
	 * @return
	 */
	public List<BizClaimVoucherStatistics> selectDeptMonthStatisticsDetail(int year,int month, int deptId);
	
	/**
	 * 部门的年度统计
	 * @param deptId
	 * @return
	 */
	public List<BizClaimVouyearStatistics> selectDeptYearStatistics(int deptId);
	
	/**
	 * 部门的年度统计详情
	 * @param year
	 * @param deptId
	 * @return
	 */
	public List<BizClaimVouyearStatistics> selectDeptYearStatisticsDetail(int  year,int deptId);

	
	
	
	

	/**
	 * 月度统计
	 * @return
	 */
	public List<BizClaimVoucherStatistics> selectMonthStatistics();
	
	/**
	 * 月度统计详情
	 * @param year
	 * @param month
	 * @return
	 */
	public List<BizClaimVoucherStatistics> selectMonthStatisticsDetail(int year,int month);
	
	/**
	 *年度统计
	 * @return
	 */
	public List<BizClaimVouyearStatistics> selectYearStatistics();
	
	/**
	 *年度统计
	 * @return
	 */
	public List<BizClaimVouyearStatistics> selectYearStatisticsDetail(int year);
	
	/**
	 * 月度统计柱形图
	 * @param deptId
	 * @param year
	 * @return
	 */
	public List<MonthBean> getMonth(int deptId, int year) ;
}
