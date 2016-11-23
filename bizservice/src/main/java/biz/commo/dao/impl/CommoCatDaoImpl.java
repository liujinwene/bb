package biz.commo.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import biz.commo.cmd.ListCommoCatByCdCmd;
import biz.commo.dao.ICommoCatDao;
import biz.commo.po.CommoCat;
import biz.common.dao.BaseHibernateDao;

@Repository
public class CommoCatDaoImpl extends BaseHibernateDao<CommoCat> implements ICommoCatDao {

	@Override
	public void add(CommoCat entity) {
		baseSave(entity);
	}

	@Override
	public void update(CommoCat entity) {
		baseUpdate(entity);
	}

	@Override
	public void delete(CommoCat entity) {
		baseDelete(entity);
	}

	@Override
	public List<CommoCat> listByCd(ListCommoCatByCdCmd cmd) {
		Criteria criteria = createCriteria();
		addCondition(criteria, cmd);
		if(cmd.getPageSize() != null && cmd.getOffset() != null){
			criteria.setFirstResult(cmd.getOffset());
			criteria.setMaxResults(cmd.getPageSize());
		}
		return criteria.list();
	}

	private void addCondition(Criteria criteria, ListCommoCatByCdCmd cmd) {
		if(cmd.getId() != null) {
			criteria.add(Restrictions.eq("id", cmd.getId()));
		}
		if(StringUtils.isNotBlank(cmd.getName())) {
			criteria.add(Restrictions.like("name", "%"+cmd.getName()+"%"));
		}
	}

	@Override
	public CommoCat findByCd(ListCommoCatByCdCmd cmd) {
		List<CommoCat> list = listByCd(cmd);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer findCountByCd(ListCommoCatByCdCmd cmd) {
		Criteria criteria = createCriteria();
		addCondition(criteria, cmd);
		return getRowCount(criteria);
	}
	
}
