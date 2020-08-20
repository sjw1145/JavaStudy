package kr.ac.green;

import java.io.Serializable;

public class NotDeliveredException extends RuntimeException implements Serializable {
	int code = ResponseProtocol.RESPONSE_CHATTINGROOM_MSG_INPUT_NO;

	public NotDeliveredException() {
		super("입력받은 내용을 전달하지 못 함.");
	}
}

