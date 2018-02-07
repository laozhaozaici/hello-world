package com.lanou.dao;

import java.util.List;

import com.lanou.entity.SysEmployee;

public interface EmpDao {

	/**
	 * 查询里面所有的员工
	 * @return
	 */
	public List<SysEmployee> selectAll();
	
	/**
	 * 根据工号和密码查询员工
	 * @return
	 */
	public SysEmployee findEmp(int  cn,String pwd);
	
}
