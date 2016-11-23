package biz.commo.dao;

import java.util.List;

import biz.commo.cmd.ListCommoCatContactByCdCmd;
import biz.commo.po.CommoCatContact;

public interface ICommoCatContactDao {
	void add(CommoCatContact entity);
	void update(CommoCatContact entity);
	void delete(CommoCatContact entity);
	List<CommoCatContact> listByCd(ListCommoCatContactByCdCmd cmd);
	CommoCatContact findByCd(ListCommoCatContactByCdCmd cmd);
	Integer findCountByCd(ListCommoCatContactByCdCmd cmd);
}
