package com.lanou.dao;
import java.util.List;

import com.lanou.bean.BizClaimVoucherBean;
import com.lanou.bean.Emp;
import com.lanou.entity.BizClaimVoucher;
import com.lanou.entity.SysEmployee;

public interface ClaimDao {

	/**
	 * 分页显示内容
	 * @param bean
	 * @param positionName  职位名称
	 * @return
	 */
	public List<BizClaimVoucher> pageByClaim(BizClaimVoucherBean bean,
			int pageNo,int pageSize,String positionName,
			String empName);
	
	
	/**
	 * 获取总条数
	 * @param bean
	 * @return
	 */
	public int totalCount(BizClaimVoucherBean bean,
			String positionName,String empName);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public void delete(int id);
	/**
	 * 添加
	 * @param claim
	 * @return
	 */
	public int save(BizClaimVoucher claim);
	/**
	 * 修改
	 * @param claim
	 * @return
	 */
	public void update(BizClaimVoucher claim);
	
	/**
	 * 通过id 查找报销单
	 * @param id
	 * @return
	 */
	public BizClaimVoucher findClaimById(int id);
	
	
	
	/**
	 * 上级处理人
	 * @param currentId
	 * @return
	 */
	public Emp higher(int deptId,String positionNamecn);
	
	
	
	
	
}
