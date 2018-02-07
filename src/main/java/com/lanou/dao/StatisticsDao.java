package com.lanou.dao;

import java.util.List;

import com.lanou.bean.QueryStatistics;
import com.lanou.entity.BizClaimVoucherStatistics;
import com.lanou.entity.BizClaimVouyearStatistics;

public interface StatisticsDao {

	/**
	 * 部门的月度统计
	 * @param deptId
	 * @return
	 */
	public List<BizClaimVoucherStatistics> selectDeptMonthStatistics(int deptId,QueryStatistics q);

	/**
	 * 部门月度统计的总条数
	 * @param deptId
	 * @return
	 */
	public int countDeptMonthStatistics(int deptId,QueryStatistics q);

	
	
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
	 * 添加年度统计
	 * @param year
	 * @return
	 */
	public int addYearStatistics(BizClaimVouyearStatistics year);
	/**
	 * 添加月度统计
	 * @param month
	 * @return
	 */
	public int addMonthStatistics(BizClaimVoucherStatistics month);
	
	/**
	 * 获取某一个年某一个部门的月度统计
	 * @param deptId
	 * @param year
	 * @return
	 */
	public List<MonthBean> getMonth(int deptId,int year);
	
	
}
