package biz.common.exception;

public class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187208098300448716L;
	
	private Integer status;
	private String errorMsg;
	
	public BaseException(Integer status) {
		super();
		this.status = status;
	}
	
	public BaseException(Integer status, String errorMsg) {
		super(errorMsg);
		this.status = status;
		this.errorMsg = errorMsg;
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
	
	

}
