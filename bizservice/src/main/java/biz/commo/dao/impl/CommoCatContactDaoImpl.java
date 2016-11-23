package biz.commo.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import biz.commo.cmd.ListCommoCatContactByCdCmd;
import biz.commo.dao.ICommoCatContactDao;
import biz.commo.po.CommoCatContact;
import biz.common.dao.BaseHibernateDao;

@Repository
public class CommoCatContactDaoImpl extends BaseHibernateDao<CommoCatContact> implements ICommoCatContactDao {

	@Override
	public void add(CommoCatContact entity) {
		baseSave(entity);
	}

	@Override
	public void update(CommoCatContact entity) {
		baseUpdate(entity);
	}

	@Override
	public void delete(CommoCatContact entity) {
		baseDelete(entity);
	}

	@Override
	public List<CommoCatContact> listByCd(ListCommoCatContactByCdCmd cmd) {
		Criteria criteria = createCriteria();
		addCondition(criteria, cmd);
		if(cmd.getPageSize() != null && cmd.getOffset() != null){
			criteria.setFirstResult(cmd.getOffset());
			criteria.setMaxResults(cmd.getPageSize());
		}
		return criteria.list();
	}

	private void addCondition(Criteria criteria, ListCommoCatContactByCdCmd cmd) {
		if(cmd.getId() != null) {
			criteria.add(Restrictions.eq("id", cmd.getId()));
		}
		if(cmd.getCatId() != null) {
			criteria.add(Restrictions.eq("catId", cmd.getCatId()));
		}
		if(cmd.getCommoId() != null) {
			criteria.add(Restrictions.eq("commoId", cmd.getCommoId()));
		}
	}

	@Override
	public CommoCatContact findByCd(ListCommoCatContactByCdCmd cmd) {
		List<CommoCatContact> list = listByCd(cmd);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer findCountByCd(ListCommoCatContactByCdCmd cmd) {
		Criteria criteria = createCriteria();
		addCondition(criteria, cmd);
		return getRowCount(criteria);
	}

}
