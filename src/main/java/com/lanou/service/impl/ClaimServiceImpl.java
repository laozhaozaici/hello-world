package com.lanou.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lanou.bean.BizClaimVoucherBean;
import com.lanou.bean.Emp;
import com.lanou.bean.PageBean;
import com.lanou.dao.ClaimDao;
import com.lanou.dao.ClaimResultDao;
import com.lanou.dao.StatisticsDao;
import com.lanou.entity.BizCheckResult;
import com.lanou.entity.BizClaimVoucher;
import com.lanou.entity.BizClaimVoucherStatistics;
import com.lanou.entity.BizClaimVouyearStatistics;
import com.lanou.entity.SysEmployee;
import com.lanou.service.ClaimService;
import com.lanou.util.Constant;
import com.lanou.util.DateUtil;
@Service
public class ClaimServiceImpl implements ClaimService {

	@Resource
	private ClaimDao claimDao;
	@Resource
	private ClaimResultDao claimResultDao;
	@Resource
	private StatisticsDao statisticsDao;
	
	
	
	@Override
	public PageBean<BizClaimVoucher> pageList(BizClaimVoucherBean bean,
				int pageNo,SysEmployee emp) {
		PageBean<BizClaimVoucher>  page = new PageBean<>();
		page.setPageSize(Constant.PAGESIZE);
		// 某一页显示的报销单
		page.setList(claimDao.pageByClaim(bean, pageNo, page.getPageSize(),emp.getSysPosition().getNameEn(),emp.getName()));
		// 总记录条数
		page.setTotalCount(claimDao.totalCount(bean,emp.getSysPosition().getNameEn(),emp.getName()));
		return page;
	}

	@Override
	public void delete(int id) {
		claimDao.delete(id);
	}

	@Override
	public int save(BizClaimVoucher claim) {
		return claimDao.save(claim);
	}

	@Transactional
	@Override
	public void update(BizClaimVoucher claim) {
		claimDao.update(claim);
	}
	@Transactional(readOnly=false)
	@Override
	public BizClaimVoucher findClaimById(int id) {
		return claimDao.findClaimById(id);
	}

	@Transactional(readOnly=false)
	@Override
	public Emp higher(int deptId, String positionNamecn) {
		return claimDao.higher(deptId, positionNamecn);
	}

	@Transactional
	@Override
	public void repulse(BizCheckResult checkResult, 
			SysEmployee emp,String status) {
		try {
			BizClaimVoucher claim = findClaimById(
					checkResult.getBizClaimVoucher().getId());
			if (status.equals("已终止")) {
				claim.setSysEmployeeByNextDealSn(null);
			}
			claim.setStatus(status);
			update(claim);
			checkResult.setCheckTime(new Date());
			checkResult.setSysEmployee(emp);
			claimResultDao.save(checkResult);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	@Override
	public void checkPass(BizCheckResult checkResult,SysEmployee emp) {
		try {
			BizClaimVoucher claim = findClaimById(
					checkResult.getBizClaimVoucher().getId());
			if (null!=claim) {
				// 待处理人
				Emp e = null;
				if (claim.getTotalAccount().doubleValue()>=5000) {
					if (emp.getSysPosition().getNameEn().equals("manager")) {
						// 待处理人  总经理
						e = higher(Constant.GENERALMANAGER_DEPT_ID, Constant.GENERALMANAGER_NAME_EN);
						claim.setStatus("待审批");
					}else if(emp.getSysPosition().getNameEn().equals(Constant.GENERALMANAGER_NAME_EN)) {
						// 待处理人是财务
						e = higher(Constant.CASHIER_DEPT_ID, Constant.CASHIER_NAME_EN);
						claim.setStatus("已审批");
					}else if(emp.getSysPosition().getNameEn().equals(Constant.CASHIER_NAME_EN)) {
						claim.setStatus("已终止");
					}
				}else {
//					金额<5000
//					已审批
					if (emp.getSysPosition().getNameEn().equals("manager")) {
						e = higher(Constant.CASHIER_DEPT_ID, Constant.CASHIER_NAME_EN);
						claim.setStatus("已审批");
					}else if(emp.getSysPosition().getNameEn().equals(Constant.CASHIER_NAME_EN)) {
						// 待处理人是财务
						e = higher(Constant.CASHIER_DEPT_ID, Constant.CASHIER_NAME_EN);
						claim.setStatus("已终止");
					}
				}
				if (claim.getStatus().equals("已终止")) {
					claim.setSysEmployeeByNextDealSn(null);
				}else {
					
					//  1:记录保存月度统计表
					//  2:记录保存年度统计表
					BizClaimVoucherStatistics month = new BizClaimVoucherStatistics();
					Date d = new Date();
					month.setModifyTime(d);
					month.setSysDepartment(claim.getSysEmployeeByCreateSn().getSysDepartment());;
					month.setMonth(DateUtil.getMonth(d));
					month.setYear(DateUtil.getYear(d));
					month.setTotalCount(claim.getTotalAccount());
					statisticsDao.addMonthStatistics(month);
					
					BizClaimVouyearStatistics year = new BizClaimVouyearStatistics();
					year.setModifyTime(d);
					year.setSysDepartment(claim.getSysEmployeeByCreateSn().getSysDepartment());;
					year.setYear(DateUtil.getYear(d));
					year.setTotalCount(claim.getTotalAccount());
					statisticsDao.addYearStatistics(year);
					
					
					
					
					SysEmployee nexEmp = new SysEmployee();
					nexEmp.setName(e.getEmpName());
					nexEmp.setSn(e.getSn());
					claim.setSysEmployeeByNextDealSn(nexEmp);
				}
				update(claim);
				//审核单   添加记录
				checkResult.setCheckTime(new Date());
				checkResult.setSysEmployee(emp);
				claimResultDao.save(checkResult);
				
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
