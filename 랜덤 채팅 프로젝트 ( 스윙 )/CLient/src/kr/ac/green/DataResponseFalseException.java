package kr.ac.green;

import java.io.Serializable;

public class DataResponseFalseException extends RuntimeException implements Serializable{
	private int code = ResponseProtocol.RESPONSE_ALL_USER_DATA_NO;
	public DataResponseFalseException() {
		super("요청한 데이터를 전송하지 못했습니다");
	}
}

 
