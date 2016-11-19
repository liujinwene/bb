package biz.commo.dao;

import java.util.List;

import biz.commo.cmd.ListCommoByCdCmd;
import biz.commo.po.Commo;

public interface ICommoDao {
	void add(Commo commo);
	void update(Commo commo);
	void delete(Commo commo);
	List<Commo> listByCd(ListCommoByCdCmd cmd);
	Commo findByCd(ListCommoByCdCmd cmd);
	Integer findCountByCd(ListCommoByCdCmd cmd);
}
