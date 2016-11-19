package biz.common.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import biz.common.utils.ReflectionUtils;

@SuppressWarnings("unchecked")
public class BaseHibernateDao<T> {
	
	private final Class<T> entityClass;
	
	@Resource
	private SessionFactory sessionFactory;
	
	public BaseHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(this.getClass(), 0);
	}
	
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected Serializable baseSave(Object obj) {
		return getSession().save(obj);
	}
	
	protected void baseUpdate(Object obj) {
		getSession().update(obj);
	}
	
	protected void baseDelete(Object obj) {
		getSession().delete(obj);
	}
	
	protected Criteria createCriteria() {
		return getSession().createCriteria(entityClass);
	}
	
	protected int getRowCount(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		Long totalRows = (Long) criteria.uniqueResult();
		return totalRows.intValue();
	}
	
}
