package biz.commo.dao;

import java.util.List;

import biz.commo.cmd.ListCommoCatByCdCmd;
import biz.commo.po.CommoCat;

public interface ICommoCatDao {
	void add(CommoCat entity);
	void update(CommoCat entity);
	void delete(CommoCat entity);
	List<CommoCat> listByCd(ListCommoCatByCdCmd cmd);
	CommoCat findByCd(ListCommoCatByCdCmd cmd);
	Integer findCountByCd(ListCommoCatByCdCmd cmd);
}
