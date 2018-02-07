package com.lanou.service;

import java.util.List;

import com.lanou.entity.SysEmployee;

public interface EmpService {

	/**
	 * 查询里面所有的员工
	 * @return
	 */
	public List<SysEmployee> selectAll();
	
	/**
	 * 登录
	 * @param e
	 * @return
	 */
	public SysEmployee login(SysEmployee e) ;

}
