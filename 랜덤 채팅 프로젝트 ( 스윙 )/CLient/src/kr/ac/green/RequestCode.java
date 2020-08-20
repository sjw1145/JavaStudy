package kr.ac.green;

import java.io.Serializable;

public  abstract class RequestCode implements Serializable {
	private int code;

	public RequestCode() {
	}

	public RequestCode(int code) {

		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}