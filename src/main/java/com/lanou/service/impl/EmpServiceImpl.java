package com.lanou.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanou.dao.EmpDao;
import com.lanou.entity.SysEmployee;
import com.lanou.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Resource
	private EmpDao empDao;
	
	@Transactional(readOnly=false)
	@Override
	public List<SysEmployee> selectAll() {
		return empDao.selectAll();
	}
	@Override
	public SysEmployee login(SysEmployee e) {
		return empDao.findEmp(e.getSn(), e.getPassword());
	}
	
}
