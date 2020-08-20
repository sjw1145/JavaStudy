package kr.ac.green;

import java.io.Serializable;

public class NotDeliveredException extends RuntimeException implements Serializable {
	int code = ResponseProtocol.RESPONSE_CHATTINGROOM_MSG_INPUT_NO;

	public NotDeliveredException() {
		super("서버에 문제가 있어 전송하지 못했습니다.");
	}
}
 

