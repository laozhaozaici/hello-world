package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PostRemove;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.dao.ClaimResultDao;
import com.lanou.entity.BizCheckResult;
import com.lanou.service.ClaimResultService;
@Service
public class ClaimResultServiceImpl implements ClaimResultService {
	@Resource
	private ClaimResultDao claimResultDao;
	
	@Transactional
	@Override
	public int save(BizCheckResult result) {
		return claimResultDao.save(result);
	}

	@Override
	public List<BizCheckResult> selectAll(int claimId) {
		// TODO Auto-generated method stub
		return claimResultDao.selectAll(claimId);
	}

}
