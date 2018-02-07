package com.lanou.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.lanou.dao.EmpDao;
import com.lanou.entity.SysEmployee;
@Repository
public class EmpDaoImpl implements EmpDao {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public List<SysEmployee> selectAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(SysEmployee.class);
		return c.list();
	}

	@Override
	public SysEmployee findEmp(int cn, String pwd) {
		Session session = sessionFactory.openSession();
		String hql = "from SysEmployee where sn = ? "
				+ "and password = ?";
		Query q = session.createQuery(hql);
		q.setParameter(0, cn);
		q.setParameter(1, pwd);
		SysEmployee  s = (SysEmployee) q.uniqueResult();
		return s;
	}

}
