package biz.commo.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bb_commo_cat_contact")
public class CommoCatContact {
	private Long id;
	private Long catId;
	private Long commoId;
	private Long createTime;
	private Integer pos;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "cat_id")
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	
	@Column(name = "commo_id")
	public Long getCommoId() {
		return commoId;
	}
	public void setCommoId(Long commoId) {
		this.commoId = commoId;
	}
	
	@Column(name = "create_time")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "pos")
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
}
