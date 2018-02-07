package com.lanou.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.lanou.dao.ClaimResultDao;
import com.lanou.entity.BizCheckResult;

@Repository
public class ClaimResultDaoImpl implements ClaimResultDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public int save(BizCheckResult result) {
		Session session = sessionFactory.getCurrentSession();
		int row = (int) session.save(result);
		return row;
	}

	@Override
	public List<BizCheckResult> selectAll(int claimId) {
		String hql  = "from BizCheckResult b  where "
				+ "  b.bizClaimVoucher.id =  ?";
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		q.setParameter(0, claimId);
		List<BizCheckResult> bc = q.list();
		return bc;
	}

}
