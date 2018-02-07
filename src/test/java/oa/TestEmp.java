package oa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lanou.bean.BizClaimVoucherBean;
import com.lanou.entity.BizClaimVoucher;
import com.lanou.entity.SysEmployee;
import com.lanou.service.EmpService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class TestEmp {

	@Autowired
	private EmpService empService;
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void showEmp() {
		List<SysEmployee>  ls = empService.selectAll();
		for (SysEmployee sys : ls) {
			System.out.println(sys.getName());
		}
	}
	@Test
	public void login() {
		Session session = sessionFactory.openSession();
		String hql = "from SysEmployee where sn = ? and password = ?";
		Query q = session.createQuery(hql);
		q.setParameter(0, 2);
		q.setParameter(1, "123");
		SysEmployee  s = (SysEmployee) q.uniqueResult();
		System.out.println(s.getSysPosition().getNameCn());
	}
	
	
	@Test
	public void list() {
		
		BizClaimVoucherBean bean = new BizClaimVoucherBean();
//		bean.setStatus("新创建");
		Map<String,Object> map = new HashMap<>();
		Session session = sessionFactory.openSession();
		StringBuffer hql = new StringBuffer("from BizClaimVoucher where 1=1");
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
		hql.append(" order by createTime desc");
		Query q = session.createQuery(hql.toString());
		
		q.setFirstResult(1);
		q.setMaxResults(1);
		
		for (Entry<String, Object> e : map.entrySet()) {
			q.setParameter(e.getKey(), e.getValue());
		}
		
		List<BizClaimVoucher> list = q.list();
		for (BizClaimVoucher b : list) {
			System.out.println(b.getCreateTime()+"--------"+b.getSysEmployeeByCreateSn().getName()+"-------"
					+b.getTotalAccount()+"-----"+b.getStatus()+"------"+b.getSysEmployeeByNextDealSn().getName());
		}
	}
	
	
	
	
	
}
