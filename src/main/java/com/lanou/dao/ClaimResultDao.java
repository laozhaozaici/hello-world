package com.lanou.dao;

import java.util.List;

import com.lanou.entity.BizCheckResult;

public interface ClaimResultDao {

	/**
	 * 保存审核结果
	 * @param result
	 * @return
	 */
	public int save(BizCheckResult result);
	/**
	 * 根据报销单id 查询审核结果
	 * @param claimId
	 * @return
	 */
	public List<BizCheckResult> selectAll(int claimId);
}
