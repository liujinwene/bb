package biz.commo.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import biz.commo.cmd.ListCommoByCdCmd;
import biz.commo.dao.ICommoDao;
import biz.commo.po.Commo;
import biz.common.dao.BaseHibernateDao;

@SuppressWarnings("unchecked")
@Repository
public class CommoDaoImpl extends BaseHibernateDao<Commo> implements ICommoDao {

	@Override
	public void add(Commo commo) {
		baseSave(commo);
	}

	@Override
	public void update(Commo commo) {
		baseUpdate(commo);
	}

	@Override
	public void delete(Commo commo) {
		baseDelete(commo);
	}

	@Override
	public List<Commo> listByCd(ListCommoByCdCmd cmd) {
		Criteria criteria = createCriteria();
		addCondition(criteria, cmd);
		if(cmd.getPageSize() != null && cmd.getOffset() != null){
			criteria.setFirstResult(cmd.getOffset());
			criteria.setMaxResults(cmd.getPageSize());
		}
		return criteria.list();
	}

	private void addCondition(Criteria criteria, ListCommoByCdCmd cmd) {
		if(cmd.getId() != null) {
			criteria.add(Restrictions.eq("id", cmd.getId()));
		}
		if(StringUtils.isNotBlank(cmd.getName())) {
			criteria.add(Restrictions.like("name", "%"+cmd.getName()+"%"));
		}
		if(cmd.getDeleteFlag() != null) {
			criteria.add(Restrictions.eq("deleteFlag", cmd.getDeleteFlag()));
		}
		if(cmd.getIds() != null && !cmd.getIds().isEmpty()) {
			Criterion condition = null;
			for(Long r : cmd.getIds()) {
				if(condition == null) {
					condition = Restrictions.eq("id", r);
				} else {
					condition = Restrictions.or(condition, Restrictions.eq("id", r));
				}
			}
			criteria.add(condition);
		}
	}

	@Override
	public Commo findByCd(ListCommoByCdCmd cmd) {
		List<Commo> list = listByCd(cmd);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer findCountByCd(ListCommoByCdCmd cmd) {
		Criteria criteria = createCriteria();
		addCondition(criteria, cmd);
		return getRowCount(criteria);
	}

	
}
