package util;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String msg;
	private String code;

	public ServiceException(String msg, String code) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
