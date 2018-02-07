package com.lanou.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanou.bean.PageBean;
import com.lanou.bean.QueryStatistics;
import com.lanou.dao.MonthBean;
import com.lanou.entity.BizClaimVoucherStatistics;
import com.lanou.entity.SysEmployee;
import com.lanou.service.StatisticsService;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope("prototype")
public class StatisticsAction extends ActionSupport {

	@Resource
	private StatisticsService statisticsService;
	
	
	
	private int pageNo;
	private PageBean<BizClaimVoucherStatistics> page ;
	
	private List<Integer> list;
	
	/**
	 * 月度统计的查询条件
	 */
	private QueryStatistics q;
	
	/**
	 * 部门统计列表
	 * @return
	 */
	public String deptStatisticsByMonth() {
		QueryStatistics q1 = new QueryStatistics();
		if (q!=null) {
			q1 = q;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		SysEmployee emp = (SysEmployee) session.getAttribute("employee");
		if (emp!=null) {
			page = statisticsService.selectDeptMonthStatistics(emp.getSysDepartment().getId(), pageNo,q1);
			if (page!=null) {
				request.setAttribute("page", page);
				return SUCCESS;
			}else {
				return ERROR;
			}
		}else {
			return ERROR;
		}
	}

	
	public String month() {
		List<MonthBean> ms = statisticsService.getMonth(2,2017);
		list = new ArrayList<Integer>();
		for (MonthBean m : ms) {
			list.add(m.getMoney());
		}
		return SUCCESS;
	}
	


	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public PageBean<BizClaimVoucherStatistics> getPage() {
		return page;
	}
	public void setPage(PageBean<BizClaimVoucherStatistics> page) {
		this.page = page;
	}
	public QueryStatistics getQ() {
		return q;
	}
	public void setQ(QueryStatistics q) {
		this.q = q;
	}


	public List<Integer> getList() {
		return list;
	}


	public void setList(List<Integer> list) {
		this.list = list;
	}



	
	
	
}
