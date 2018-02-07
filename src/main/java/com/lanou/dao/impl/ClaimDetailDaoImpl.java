package com.lanou.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.lanou.dao.ClaimVoucherDetailDao;
import com.lanou.entity.BizClaimVoucherDetail;

@Repository
public class ClaimDetailDaoImpl implements ClaimVoucherDetailDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public int saveClaimDetail(BizClaimVoucherDetail detail) {
		Session session = sessionFactory.getCurrentSession();
		int result = (int) session.save(detail);
		return result;
	}
	
	
	/**
	 * 修改
	 * @param detail
	 */
	@Override
	public void updateDetail(BizClaimVoucherDetail detail) {
		Session session = sessionFactory.getCurrentSession();
		session.update(detail);
	}
	
	

}
