package kr.ac.green;

import java.io.Serializable;

public class PushDataDTO extends RequestCode implements Serializable {

	
	private Object data;
	
	public PushDataDTO(int code, Object data) {
		super(code);
		this.data=data;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data){
		this.data=data;
	}
}
