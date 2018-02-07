package com.lanou.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.lanou.bean.Emp;
import com.lanou.bean.QueryStatistics;
import com.lanou.dao.MonthBean;
import com.lanou.dao.StatisticsDao;
import com.lanou.entity.BizClaimVoucherStatistics;
import com.lanou.entity.BizClaimVouyearStatistics;
@Repository
public class StatisticsDaoImpl implements StatisticsDao {
    
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public List<BizClaimVoucherStatistics> selectDeptMonthStatistics(int deptId,QueryStatistics q) {
		Map<String,Object> map  = new HashMap<>();
		StringBuffer hql = new StringBuffer("from BizClaimVoucherStatistics b where 1= 1 ");
		if (deptId!=0) {
			hql.append("  and b.sysDepartment.id = :deptId ");
			map.put("deptId", deptId);
		}
		if (q.getYear()!=0) {
			hql.append("  and b.year= :year ");
			map.put("year", q.getYear());
		}
		if (q.getStartMonth()!=0) {
			hql.append(" and b.month  >=:startMonth");
			map.put("startMonth", q.getStartMonth());
		}
		if (q.getEndMonth()!=0) {
			hql.append(" and b.month  <=:endMonth");
			map.put("endMonth", q.getEndMonth());
		}
		
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		
		for (Entry<String, Object> e : map.entrySet()) {
			query.setParameter(e.getKey(), e.getValue());
		}
		
		List<BizClaimVoucherStatistics> list = query.list();
		return list;
	}
	
	@Override
	public int countDeptMonthStatistics(int deptId,QueryStatistics q) {
		Map<String,Object> map  = new HashMap<>();
		StringBuffer hql = new StringBuffer("select count(b.id) from BizClaimVoucherStatistics b where 1= 1 ");
		if (deptId!=0) {
			hql.append("  and b.sysDepartment.id = :deptId ");
			map.put("deptId", deptId);
		}
		if (q.getYear()!=0) {
			hql.append("  and b.year= :year ");
			map.put("year", q.getYear());
		}
		if (q.getStartMonth()!=0) {
			hql.append(" and b.month  >=:startMonth");
			map.put("startMonth", q.getStartMonth());
		}
		if (q.getEndMonth()!=0) {
			hql.append(" and b.month  <=:endMonth");
			map.put("endMonth", q.getEndMonth());
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql.toString());
		for (Entry<String, Object> e : map.entrySet()) {
			query.setParameter(e.getKey(), e.getValue());
		}
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}

	
	
	

	@Override
	public List<BizClaimVoucherStatistics> selectDeptMonthStatisticsDetail(int year, int month, int deptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BizClaimVouyearStatistics> selectDeptYearStatistics(int deptId) {
		// TODO Auto-generated method stub
		String hql = "from BizClaimVouyearStatistics b where b.sysDepartment.id = ? ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, deptId);
		List<BizClaimVouyearStatistics> list = query.list();
		return list;	
	}

	@Override
	public List<BizClaimVouyearStatistics> selectDeptYearStatisticsDetail(int year, int deptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BizClaimVoucherStatistics> selectMonthStatistics() {
		// TODO Auto-generated method stub
		String hql = "from BizClaimVoucherStatistics b ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<BizClaimVoucherStatistics> list = query.list();
		return list;
	}

	@Override
	public List<BizClaimVoucherStatistics> selectMonthStatisticsDetail(int year, int month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BizClaimVouyearStatistics> selectYearStatistics() {
		// TODO Auto-generated method stub
		String hql = "from BizClaimVouyearStatistics b  ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<BizClaimVouyearStatistics> list = query.list();
		return list;	
	}

	@Override
	public List<BizClaimVouyearStatistics> selectYearStatisticsDetail(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addYearStatistics(BizClaimVouyearStatistics year) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(year);
	}

	@Override
	public int addMonthStatistics(BizClaimVoucherStatistics month) {
		Session session = sessionFactory.getCurrentSession();
		return (int) session.save(month);
	}

	@Override
	public List<MonthBean> getMonth(int deptId, int year) {
		
		String sql = "SELECT sum(TOTAL_COUNT) as money from biz_claim_voucher_statistics\r\n" + 
				"where year = 2017 and DEPARTMENT_ID = 2\r\n" + 
				"GROUP BY `MONTH`\r\n" + 
				"ORDER BY `MONTH` asc\r\n" + 
				"";
		Session session = sessionFactory.getCurrentSession();
		SQLQuery createSQLQuery = session.createSQLQuery(sql);
		createSQLQuery.addScalar("money", StandardBasicTypes.INTEGER);
		createSQLQuery.setResultTransformer(Transformers.aliasToBean(MonthBean.class));  
		List<MonthBean> ms = createSQLQuery.list();
		return ms;
	}






	
}
