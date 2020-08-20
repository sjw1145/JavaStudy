package kr.ac.green;

public class RoomCreateFailureException extends RuntimeException {

	private static final long serialVersionUID = -6049451322225179842L;
	int code = ResponseProtocol.RESPONSE_CREATE_CHATTINGROOM_NO;

	public RoomCreateFailureException() {
		super("채팅방 생성에 실패하였습니다.");
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return super.getMessage();
	}

}

 

