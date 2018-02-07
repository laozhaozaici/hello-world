package com.lanou.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanou.dao.ClaimVoucherDetailDao;
import com.lanou.entity.BizClaimVoucherDetail;
import com.lanou.service.ClaimDetailService;
@Service
public class ClaimDetailServiceImpl implements ClaimDetailService {

	@Resource
	private ClaimVoucherDetailDao claimVoucherDetailDao;
	@Override
	public int saveClaimDetail(BizClaimVoucherDetail detail) {
		return claimVoucherDetailDao.saveClaimDetail(detail);
	}
	
	
	@Override
	public void updateDetail(BizClaimVoucherDetail detail) {
		claimVoucherDetailDao.updateDetail(detail);
	}

}
