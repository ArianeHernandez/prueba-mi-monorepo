package co.htsoft.commons.util;

public class Message<T> {

	private T data;
	private Boolean valid;
	private String msg;

	public Message() {
	}

	public Message(Boolean valid, String msg, T data) {
		this.data = data;
		this.valid = valid;
		this.msg = msg;
	}

	public Message(Boolean valid, String msg) {
		this.valid = valid;
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
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

}
