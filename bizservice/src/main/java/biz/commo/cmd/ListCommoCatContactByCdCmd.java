package biz.commo.cmd;

import biz.common.cmd.PageCmd;

public class ListCommoCatContactByCdCmd extends PageCmd {
	private Long id;
	private Long catId;
	private Long commoId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public Long getCommoId() {
		return commoId;
	}
	public void setCommoId(Long commoId) {
		this.commoId = commoId;
	}
}
