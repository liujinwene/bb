package biz.common.resp;

import biz.common.exception.BaseErrorCodes;

public class BaseResp {
	public static final String VERSION_1 = "1.0.0";
	
	private String version;
	private Integer status;
	private String errorMsg;
	private Object response;
	
	public BaseResp() {
		this.version = VERSION_1;
		this.status = BaseErrorCodes.SUCCESS;
	}
	
	public BaseResp(Integer status) {
		this.version = VERSION_1;
		this.status = status;
	}
	
	public BaseResp(Integer status, String errorMsg) {
		this.version = VERSION_1;
		this.status = status;
		this.errorMsg = errorMsg;
	}
	
	public BaseResp(Object response) {
		this.version = VERSION_1;
		this.status = BaseErrorCodes.SUCCESS;
		this.response = response;
	}
	
	public BaseResp(Integer status, Object response) {
		this.version = VERSION_1;
		this.status = status;
		this.response = response;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
}