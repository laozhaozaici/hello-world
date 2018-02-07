package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.bean.PageBean;
import com.lanou.bean.QueryStatistics;
import com.lanou.dao.MonthBean;
import com.lanou.dao.StatisticsDao;
import com.lanou.entity.BizClaimVoucherStatistics;
import com.lanou.entity.BizClaimVouyearStatistics;
import com.lanou.service.StatisticsService;
import com.lanou.util.Constant;
@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Resource
	private StatisticsDao statisticsDao;
	
	@Override
	public PageBean<BizClaimVoucherStatistics> selectDeptMonthStatistics(int deptId,int pageNo,QueryStatistics q) {
		// TODO Auto-generated method stub
		PageBean<BizClaimVoucherStatistics> page = new PageBean<>();
		page.setList(statisticsDao.selectDeptMonthStatistics(deptId,q));
		page.setTotalCount(statisticsDao.countDeptMonthStatistics(deptId,q));
		page.setPageSize(Constant.PAGESIZE);
		page.setPageNo(pageNo);
		page.setCurrentNo(page.getPageNo());
		return page;
	}

	@Override
	public List<BizClaimVoucherStatistics> selectDeptMonthStatisticsDetail(int year, int month, int deptId) {
		// TODO Auto-generated method stub
		return statisticsDao.selectDeptMonthStatisticsDetail(year, month, deptId);
	}

	@Override
	public List<BizClaimVouyearStatistics> selectDeptYearStatistics(int deptId) {
		// TODO Auto-generated method stub
		return statisticsDao.selectDeptYearStatistics(deptId);
	}

	@Override
	public List<BizClaimVouyearStatistics> selectDeptYearStatisticsDetail(int year, int deptId) {
		// TODO Auto-generated method stub
		return statisticsDao.selectDeptYearStatisticsDetail(year, deptId);
	}

	@Override
	public List<BizClaimVoucherStatistics> selectMonthStatistics() {
		// TODO Auto-generated method stub
		return statisticsDao.selectMonthStatistics();
	}

	@Override
	public List<BizClaimVoucherStatistics> selectMonthStatisticsDetail(int year, int month) {
		// TODO Auto-generated method stub
		return statisticsDao.selectMonthStatisticsDetail(year, month);
	}

	@Override
	public List<BizClaimVouyearStatistics> selectYearStatistics() {
		// TODO Auto-generated method stub
		return statisticsDao.selectYearStatistics();
	}

	@Override
	public List<BizClaimVouyearStatistics> selectYearStatisticsDetail(int year) {
		// TODO Auto-generated method stub
		return statisticsDao.selectYearStatisticsDetail(year);
	}

	@Override
	public List<MonthBean> getMonth(int deptId, int year) {
		// TODO Auto-generated method stub
		return statisticsDao.getMonth(deptId, year);
	}

}
