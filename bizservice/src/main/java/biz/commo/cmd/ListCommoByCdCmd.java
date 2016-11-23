package biz.commo.cmd;

import java.util.List;

import biz.common.cmd.PageCmd;


public class ListCommoByCdCmd extends PageCmd {
	private Long id;
	private String name;
	private Byte deleteFlag;
	private List<Long> ids;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Byte getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Byte deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
