package com.lanou.service;

import com.lanou.bean.BizClaimVoucherBean;
import com.lanou.bean.Emp;
import com.lanou.bean.PageBean;
import com.lanou.entity.BizCheckResult;
import com.lanou.entity.BizClaimVoucher;
import com.lanou.entity.SysEmployee;

public interface ClaimService {

	/**
	 * BizClaimVoucher  报销单
	 * 分页显示报销单
	 */
	public PageBean<BizClaimVoucher> pageList(BizClaimVoucherBean bean,
			int pageNo,SysEmployee emp);
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public void delete(int id);
	/**
	 * 添加
	 * @param claim
	 * @return
	 */
	public int save(BizClaimVoucher claim);
	/**
	 * 修改
	 * @param claim
	 * @return
	 */
	public void update(BizClaimVoucher claim);
	
	/**
	 * 通过id 查找报销单
	 * @param id
	 * @return
	 */
	public BizClaimVoucher findClaimById(int id);
	
	/**
	 * 上级处理人
	 * @param currentId
	 * @return
	 */
	public Emp higher(int deptId,String positionNamecn);
	/**
	 * 已打回  或者审核拒绝
	 * @param checkResult
	 * @param claimId
	 * @param emp
	 */
	public void repulse(BizCheckResult checkResult,
			SysEmployee emp,String status);
	/**
	 * 审核通过
	 */
	public void checkPass(BizCheckResult checkResult,SysEmployee emp);
}
