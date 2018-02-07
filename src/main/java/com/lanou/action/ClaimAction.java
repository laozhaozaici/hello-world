package com.lanou.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.lanou.bean.BizClaimVoucherBean;
import com.lanou.bean.Emp;
import com.lanou.bean.PageBean;
import com.lanou.entity.BizCheckResult;
import com.lanou.entity.BizClaimVoucher;
import com.lanou.entity.BizClaimVoucherDetail;
import com.lanou.entity.SysEmployee;
import com.lanou.service.ClaimDetailService;
import com.lanou.service.ClaimResultService;
import com.lanou.service.ClaimService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ClaimAction extends ActionSupport {

	@Resource
	private ClaimService claimService;
	@Resource
	private ClaimDetailService claimDetailService;
	@Resource
	private ClaimResultService claimResultService;
	
	
	
	
	/** 报销单的id*/
	private Integer id;
	private BizClaimVoucher claimVoucher;
	private PageBean<BizClaimVoucher> page;
	/**查询的条件bean*/ 
	private BizClaimVoucherBean bean;
	private int pageNo;
	private Map<String,Object> statusMap;
	
	// 单个报销单详情
	private BizClaimVoucherDetail claimVoucherDetail;
	//  多个报销单详情
	private List<BizClaimVoucherDetail> detailList;
	//审核结果
	private BizCheckResult checkResult;
	
	
	/**
	 * 查看报销单页面的方法
	 * @return
	 */
	public String searchClaimVoucher() {
		BizClaimVoucherBean claim = new BizClaimVoucherBean();
		if (this.getBean()!=null) {
			claim = bean;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		SysEmployee emp = (SysEmployee) session.getAttribute("employee");
		if (emp!=null) {
			pageNo = pageNo==0 ?pageNo=1:pageNo;
			statusMap = new HashMap<>();
			statusMap.put("新创建", "新创建");
			page = claimService.pageList(claim, pageNo,emp);
			page.setCurrentNo(pageNo);
			request.setAttribute("pageSupport", page);
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}
	/**
	 * 跳转到 添加报销单
	 * @return
	 */
	public String toAddClaimVoucher() {
		return SUCCESS;
	}
	
	public String addClaim() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		SysEmployee emp = (SysEmployee) session.getAttribute("employee");
		if (emp==null) {
			return ERROR;
		}else {
			//添加报销单
			claimVoucher.setSysEmployeeByCreateSn(emp);
			
//			Emp e = claimService.higher(emp.getSysDepartment().getId(), "manager");
//			SysEmployee nexEmp = new SysEmployee();
//			nexEmp.setName(e.getEmpName());
//			nexEmp.setSn(e.getSn());
//			claimVoucher.setSysEmployeeByNextDealSn(nexEmp);
			int result = claimService.save(claimVoucher);
			
			//  
			System.out.println("添加单个详情====="+detailList==null);
			System.out.println("添加多个详情====="+claimVoucherDetail==null);
			
			if (claimVoucherDetail!=null&&claimVoucherDetail.getAccount()!=null) {
				claimVoucherDetail.setBizClaimVoucher(claimVoucher);
				claimDetailService.saveClaimDetail(claimVoucherDetail);
			}
			if (detailList!=null) {
				for (BizClaimVoucherDetail b : detailList) {
					b.setBizClaimVoucher(claimVoucher);
					claimDetailService.saveClaimDetail(b);
				}
			}
			
			if (result>0) {
				return SUCCESS;
			}
		}
		return null;
	}
	
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toUpdate() {
		claimVoucher = claimService.findClaimById(id);
		if (claimVoucher!=null) {
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}
	
	/**
	 * 修改报销单
	 * @return
	 */
	public String updateClaimVoucher() {
		System.out.println("进入update  方法");
		HttpSession session = ServletActionContext.getRequest().getSession();
		SysEmployee emp = (SysEmployee) session.getAttribute("employee");
		
		if (claimVoucher!=null) {
					System.out.println("添加单个详情====="+detailList==null);
					System.out.println("添加多个详情====="+claimVoucherDetail==null);
					
			if (claimVoucher.getStatus().equals("新创建")) {
				// 详情   都 报销单绑定上
				
				if (claimVoucherDetail!=null&&claimVoucherDetail.getAccount()!=null) {
					claimVoucherDetail.setBizClaimVoucher(claimVoucher);
					claimDetailService.saveClaimDetail(claimVoucherDetail);
				}
				if (detailList!=null) {
					for (BizClaimVoucherDetail b : detailList) {
						b.setBizClaimVoucher(claimVoucher);
						claimDetailService.updateDetail(b);
					}
				}
				return SUCCESS;

			}else {
				// 执行提交操作
				System.out.println("进入提交 方法");
				claimVoucher.setStatus("已提交");
				// 待处理人
				Emp e = claimService.higher(emp.getSysDepartment().getId(), "manager");
				SysEmployee nexEmp = new SysEmployee();
				nexEmp.setName(e.getEmpName());
				nexEmp.setSn(e.getSn());
				claimVoucher.setSysEmployeeByNextDealSn(nexEmp);
				// 创建人
				claimVoucher.setSysEmployeeByCreateSn(emp);
				
				claimService.update(claimVoucher);
				return SUCCESS;
//				status   = "已提交"
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到审核页面
	 * @return
	 */
	public String toCheck() {
		// 通过id 查询报销单
		claimVoucher = claimService.findClaimById(id);
		if (claimVoucher!=null) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	/**
	 * 审核
	 * @return
	 */
	public String checkResult() {
		// 审核通过      已审核
		// 审核拒绝      已终止
		//打回             已打回、
		SysEmployee emp = (SysEmployee) ServletActionContext.getRequest().getSession().getAttribute("employee");
		if (emp!=null) {
			if (checkResult!=null) {
				if (checkResult.getResult().equals("通过")) {
					System.out.println("审核通过");
//					金额>5000    待审批  待处理人：  总经理
//					金额<5000  总经理  已审批  待处理人：财务

//					修改状态 报销单
//					增加审核记录 
					claimService.checkPass(checkResult, emp);
				}else if(checkResult.getResult().equals("拒绝")) {
					System.out.println("审核拒绝");
					
					// 获取报销单  修改状态   已终止
					// 添加审核记录
					 claimService.repulse(checkResult, 
							 emp,"已终止");
					
				}else if(checkResult.getResult().equals("打回")){
					System.out.println("打回");
					
					//报销单  修改状态  已打回
					 claimService.repulse(checkResult, 
							 emp,"已打回");
				}
			}
		}else {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	
	
	
	
	

	public BizCheckResult getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(BizCheckResult checkResult) {
		this.checkResult = checkResult;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BizClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}

	public void setClaimVoucher(BizClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}

	public Map<String, Object> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, Object> statusMap) {
		this.statusMap = statusMap;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public PageBean<BizClaimVoucher> getPage() {
		return page;
	}

	public void setPage(PageBean<BizClaimVoucher> page) {
		this.page = page;
	}
	public BizClaimVoucherBean getBean() {
		return bean;
	}
	public void setBean(BizClaimVoucherBean bean) {
		this.bean = bean;
	}
	public BizClaimVoucherDetail getClaimVoucherDetail() {
		return claimVoucherDetail;
	}
	public void setClaimVoucherDetail(BizClaimVoucherDetail claimVoucherDetail) {
		this.claimVoucherDetail = claimVoucherDetail;
	}
	public List<BizClaimVoucherDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<BizClaimVoucherDetail> detailList) {
		this.detailList = detailList;
	}

	
}
