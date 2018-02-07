package com.lanou.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
@Repository
public class BaseDaoImpl<T> {
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public int add(T t) {
		return (int) getCurrentSession().save(t);
	}
	
	public void delete(T t) {
		 getCurrentSession().delete(t);
	}
	
}
