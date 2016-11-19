package biz.commo.service;

import java.util.List;

import biz.commo.cmd.AddCommoCmd;
import biz.commo.cmd.ListCommoByCatCmd;
import biz.commo.cmd.ListCommoByCdCmd;
import biz.commo.cmd.ListCommoCatByCdCmd;
import biz.commo.resp.CommoCatResp;
import biz.commo.resp.CommoResp;

public interface ICommoService {
	List<CommoResp> listByCd(ListCommoByCdCmd cmd);
	void add(AddCommoCmd cmd);
	List<CommoCatResp> listCommoCatByCd(ListCommoCatByCdCmd cmd);
	List<CommoResp> listByCat(ListCommoByCatCmd cmd);
}
