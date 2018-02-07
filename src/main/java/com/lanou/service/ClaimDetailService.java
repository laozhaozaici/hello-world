package com.lanou.service;

import com.lanou.entity.BizClaimVoucherDetail;

public interface ClaimDetailService {

	

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
