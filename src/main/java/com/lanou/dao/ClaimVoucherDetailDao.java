package com.lanou.dao;

import com.lanou.entity.BizClaimVoucherDetail;

public interface ClaimVoucherDetailDao {

	/**
	 * 保存详情
	 * @param detail
	 * @return
	 */
	public int saveClaimDetail(BizClaimVoucherDetail detail);
	
	/**
	 * 修改
	 * @param detail
	 */
	public void updateDetail(BizClaimVoucherDetail detail);
	
	
	
}
