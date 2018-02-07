package com.lanou.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lanou.bean.BizClaimVoucherBean;
import com.lanou.bean.Emp;
import com.lanou.dao.ClaimDao;
import com.lanou.entity.BizClaimVoucher;
import com.lanou.util.Constant;
@Repository
public class ClaimDaoImpl implements ClaimDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<BizClaimVoucher> pageByClaim(BizClaimVoucherBean bean,int pageNo,int pageSize,
			String positionName,String empName) {
		Session session = sessionFactory.getCurrentSession();
		Map<String,Object> map = new HashMap<>();
		StringBuffer hql = new StringBuffer("from BizClaimVoucher b where 1=1");
		if (!StringUtils.isEmpty(bean.getStatus())  ) {
			if (!bean.getStatus().equals("全部")) {
				hql.append(" and b.status = :status");
				map.put("status", bean.getStatus());
			}
		}
		if (null!=bean.getStartTime()) {
			hql.append(" and b.createTime >= :startTime");
			map.put("startTime", bean.getStartTime());
		}
		
		if (null!=bean.getEndTime()) {
			hql.append(" and b.createTime <= :endTime");
			map.put("endTime", bean.getEndTime());
		}
		
		// 待处理人一定要是当前登录人   部门经理
		// 状态一定是  已提交状态
		//   总经理      待审批
		//  财务     已审批
		if (!StringUtils.isEmpty(positionName)) {
			if (!positionName.equals("staff")) {
				hql.append(" and  b.sysEmployeeByNextDealSn.name"
						+ ""
						+ " = :name");
				map.put("name", empName);
				if (positionName.equals("manager")) {
					hql.append(" and b.status = '已提交'");
				}
				if (positionName.equals(Constant.GENERALMANAGER_NAME_EN)) {
					hql.append(" and b.status = '待审批'");
				}
				if (positionName.equals(Constant.CASHIER_NAME_EN)) {
					hql.append(" and b.status = '已审批'");
				}
			}
			
		}
		hql.append(" order by createTime desc");
		Query q = session.createQuery(hql.toString());
		q.setFirstResult((pageNo-1)*pageSize);
		q.setMaxResults(pageSize);		
		for (Entry<String, Object> e : map.entrySet()) {
			q.setParameter(e.getKey(), e.getValue());
		}
		List<BizClaimVoucher> list = q.list();
		return list;
	}

	@Override
	public int totalCount(BizClaimVoucherBean bean,String positionName,String empName) {
		Session session = sessionFactory.getCurrentSession();
		Map<String,Object> map = new HashMap<>();
		StringBuffer hql = new StringBuffer("select count(b.id) from "
				+ " BizClaimVoucher b where 1=1");
		if (!StringUtils.isEmpty(bean.getStatus())) {
			hql.append(" and status = :status");
			map.put("status", bean.getStatus());
		}
		if (null!=bean.getStartTime()) {
			hql.append(" and createTime >= :startTime");
			map.put("startTime", bean.getStartTime());
		}
		if (null!=bean.getEndTime()) {
			hql.append(" and createTime <= :endTime");
			map.put("endTime", bean.getEndTime());
		}
		
		// 待处理人一定要是当前登录人   部门经理
		// 状态一定是  已提交状态
		if (!StringUtils.isEmpty(positionName)) {
			if (!positionName.equals("staff")) {
				hql.append(" and  b.sysEmployeeByNextDealSn.name"
						+ ""
						+ " = :name");
				map.put("name", empName);
				if (positionName.equals("manager")) {
					hql.append(" and b.status = '已提交'");
				}
				if (positionName.equals(Constant.GENERALMANAGER_NAME_EN)) {
					hql.append(" and b.status = '待审批'");
				}
				if (positionName.equals(Constant.CASHIER_NAME_EN)) {
					hql.append(" and b.status = '已审批'");
				}
			}
			
		}
		Query q = session.createQuery(hql.toString());
		for (Entry<String, Object> e : map.entrySet()) {
			q.setParameter(e.getKey(), e.getValue());
		}
		Long result = (Long) q.uniqueResult();
		return result.intValue();
	}

	@Transactional
	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		BizClaimVoucher b = (BizClaimVoucher) session.get(BizClaimVoucher.class, id);
		if (b!=null) {
			session.delete(b);
		}
	}

	@Transactional
	@Override
	public int save(BizClaimVoucher claim) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(claim);
	}

	@Transactional
	@Override
	public void update(BizClaimVoucher claim) {
		Session session = sessionFactory.getCurrentSession();
		session.update(claim);
	}

	@Override
	public BizClaimVoucher findClaimById(int id) {
		Session session = sessionFactory.getCurrentSession();
		BizClaimVoucher b = (BizClaimVoucher) session.get(BizClaimVoucher.class, id);
		return b;
	}

	@Override
	public Emp higher(int deptId, String positionNamecn) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT t1.`NAME` as empName ,t1.SN as sn from  sys_department  t\r\n" + 
				"LEFT JOIN  sys_employee t1 on t1.DEPARTMENT_ID = t.ID\r\n" + 
				"LEFT JOIN sys_position t2 on t2.id = t1.POSITION_ID\r\n" + 
				"where t1.DEPARTMENT_ID =? and t2.NAME_EN=?  and t1.`STATUS` = '在职'";
		
		SQLQuery navtiveSQL  = session.createSQLQuery(sql);
		navtiveSQL.setParameter(0, deptId);
		navtiveSQL.setParameter(1, positionNamecn);
		
		navtiveSQL.addScalar("empName", StandardBasicTypes.STRING)
			.addScalar("sn", StandardBasicTypes.INTEGER);
		navtiveSQL.setResultTransformer(Transformers.aliasToBean(Emp.class));  
		Emp e  = (Emp) navtiveSQL.uniqueResult();
		return e;
	}


}
