package biz.commo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.commo.cmd.AddCommoCmd;
import biz.commo.cmd.ListCommoByCatCmd;
import biz.commo.cmd.ListCommoByCdCmd;
import biz.commo.cmd.ListCommoCatByCdCmd;
import biz.commo.cmd.ListCommoCatContactByCdCmd;
import biz.commo.dao.ICommoCatContactDao;
import biz.commo.dao.ICommoCatDao;
import biz.commo.dao.ICommoDao;
import biz.commo.po.Commo;
import biz.commo.po.CommoCat;
import biz.commo.po.CommoCatContact;
import biz.commo.resp.CommoCatResp;
import biz.commo.resp.CommoResp;
import biz.commo.service.ICommoService;
import biz.common.constant.DeleteFlag;
import biz.common.exception.BaseErrorCodes;
import biz.common.exception.BaseException;
import biz.common.utils.ConvertUtil;

@Service
public class CommoServiceImpl implements ICommoService {
	
	@Resource
	ICommoDao commoDao;
	@Resource
	ICommoCatDao commoCatDao;
	@Resource
	ICommoCatContactDao commoCatContactDao;
	
	private void picIsEmpty(String pic) {
		if(StringUtils.isEmpty(pic)) {
			throw new BaseException(BaseErrorCodes.ERROR_REQUEST, "pic is null");
		}
	}

	private void priceIsEmpty(BigDecimal price) {
		if(price == null) {
			throw new BaseException(BaseErrorCodes.ERROR_REQUEST, "price is null");
		}
	}

	private void nameIsEmpty(String name) {
		if(StringUtils.isEmpty(name)) {
			throw new BaseException(BaseErrorCodes.ERROR_REQUEST, "name is null");
		}
	}

	private void IdIsEmpty(Long id) {
		if(id == null) {
			throw new BaseException(BaseErrorCodes.ERROR_REQUEST, "id is null");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommoResp> listByCd(ListCommoByCdCmd cmd) {
		List<CommoResp> list = new ArrayList<CommoResp>();
		List<Commo> commos = commoDao.listByCd(cmd);
		if(commos != null && !commos.isEmpty()) {
			for(Commo r : commos) {
				list.add(ConvertUtil.convert(r, CommoResp.class));
			}
		}
		return list;
	}

	@Override
	@Transactional
	public void add(AddCommoCmd cmd) {
		IdIsEmpty(cmd.getId());
		nameIsEmpty(cmd.getName());
		priceIsEmpty(cmd.getPrice());
		Commo commo = ConvertUtil.convert(cmd, Commo.class);
		commo.setDeleteFlag(DeleteFlag.NO.getCode());
		commo.setCreateTime((new Date()).getTime());
		commoDao.add(commo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommoCatResp> listCommoCatByCd(ListCommoCatByCdCmd cmd) {
		List<CommoCatResp> list = new ArrayList<CommoCatResp>();
		
		List<CommoCat> commoCats = commoCatDao.listByCd(cmd);
		if(commoCats != null && !commoCats.isEmpty()) {
			for(CommoCat r : commoCats) {
				list.add(ConvertUtil.convert(r, CommoCatResp.class));
			}
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommoResp> listByCat(ListCommoByCatCmd cmd) {
		catIdIsEmpty(cmd.getCatId());
		
		List<CommoResp> list = new ArrayList<CommoResp>();
		
		ListCommoCatContactByCdCmd contactCmd = new ListCommoCatContactByCdCmd();
		contactCmd.setCatId(cmd.getCatId());
		List<CommoCatContact> contacts = commoCatContactDao.listByCd(contactCmd);
		if(contacts != null && !contacts.isEmpty()) {
			List<Long> commoIds = new ArrayList<Long>();
			for(CommoCatContact r : contacts) {
				commoIds.add(r.getCommoId());
			}
			ListCommoByCdCmd commoCmd = new ListCommoByCdCmd();
			commoCmd.setIds(commoIds);
			List<Commo> commos = commoDao.listByCd(commoCmd);
			if(commos != null && !commos.isEmpty()) {
				for(Commo r : commos) {
					list.add(ConvertUtil.convert(r, CommoResp.class));
				}
			}
		}
		return list;
	}

	private void catIdIsEmpty(Long catId) {
		if(catId == null) {
			throw new BaseException(BaseErrorCodes.ERROR_REQUEST, "catId is null");
		}
	}
	

}
