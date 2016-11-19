package biz.commo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import biz.commo.cmd.AddCommoCmd;
import biz.commo.cmd.ListCommoByCatCmd;
import biz.commo.cmd.ListCommoByCdCmd;
import biz.commo.cmd.ListCommoCatByCdCmd;
import biz.commo.resp.CommoCatResp;
import biz.commo.resp.CommoResp;
import biz.commo.service.ICommoService;
import biz.common.exception.BaseErrorCodes;
import biz.common.resp.BaseResp;
import biz.common.utils.GsonUtil;

@Controller
@RequestMapping("/front/commo")
public class FrontCommoController {
	
	@Resource
	private ICommoService commoService;
	
	@RequestMapping("/listByCd")
	@ResponseBody
	public String listCommoCate(ListCommoByCdCmd cmd) {
		List<CommoResp> commos = commoService.listByCd(cmd);
		BaseResp result = new BaseResp(BaseErrorCodes.SUCCESS, commos);
		return GsonUtil.toJson(result);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(AddCommoCmd cmd) {
		commoService.add(cmd);
		BaseResp result = new BaseResp(BaseErrorCodes.SUCCESS);
		return GsonUtil.toJson(result);
	}
	
	@RequestMapping("/listCommoCatByCd")
	@ResponseBody
	public String listCommoCatByCd(ListCommoCatByCdCmd cmd) {
		List<CommoCatResp> commos = commoService.listCommoCatByCd(cmd);
		BaseResp result = new BaseResp(BaseErrorCodes.SUCCESS, commos);
		return GsonUtil.toJson(result);
	}
	
	@RequestMapping("/listByCat")
	@ResponseBody
	public String listByCat(ListCommoByCatCmd cmd) {
		List<CommoResp> commos = commoService.listByCat(cmd);
		BaseResp result = new BaseResp(BaseErrorCodes.SUCCESS, commos);
		return GsonUtil.toJson(result);
	}
}
