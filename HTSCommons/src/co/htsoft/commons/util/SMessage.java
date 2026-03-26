package co.htsoft.commons.util;

public class SMessage {

	private Boolean valid;
	private String msg;
	private String codigo;

	public SMessage() {
	}

	public SMessage(Boolean valid, String msg) {
		this.valid = valid;
		this.msg = msg;
	}
	
	public SMessage(Boolean valid, String msg, String codigo) {
		this.valid = valid;
		this.msg = msg;
		this.codigo = codigo;
	}
	
	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
