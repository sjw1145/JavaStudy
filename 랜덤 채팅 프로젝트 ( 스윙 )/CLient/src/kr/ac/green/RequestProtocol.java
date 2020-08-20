package kr.ac.green;

public class RequestProtocol {
//	서버접속
	// 닉네임을 서버에 전송하여 대기실입장을 요청
	public static final int REQUEST_NICK_CHECK = 100;	
//  채팅 대기실
	// 채팅방 입장을 요청
	public static final int REQUEST_ENTER_CHATTINGROOM = 120;
	// 채팅방 랜덤입장
	public static final int REQUEST_RANDOM_ENTER_CHATTINGROOM = 130;
	// 채팅방 생성을 요청
	public static final int REQUEST_CREATE_CHATTINGROOM = 140; 
	// 대기실에 메시지 전송 요청
	public static final int REQUEST_WATINGROOM_MSG_INPUT = 160;
//  채팅방
	// 방장이 다른 클라이언트에게 방장 권한 위임 요청
	public static final int REQUEST_MASTER_ENTRUST = 200;
	// 방장권한을 받겠다는 응답
	public static final int REQUEST_MASTER_ENTRUST_RECEIVE = 201;
	// 방장권한을 받지 않겠다는 응답
	public static final int REQUEST_MASTER_ENTRUST_NOT_RECEIVE = 202;

	// 방설정 변경 요청
	public static final int REQUEST_CHANGE_SETTING_ROOM = 220; 

	// 현재 채팅방 나가기 요청
	public static final int REQUEST_CHATTINGROOM_EXIT = 230;	
	// 방장이 현재 채팅방 나가기 요청
	public static final int REQUEST_CHATTINGROOM_HOST_EXIT = 231;
	// 입력한 내용을 모든 클라이언트에게 전송해달라고 요청
	public static final int REQUEST_CHATTINGROOM_MSG_INPUT = 240;

	// 대기실에 있는 유저 정보를 요청
	public static final int REQUEST_ALL_USER_DATA = 250;

	// 유저 검색을 요청
	public static final int REQUEST_SEARCH_USER = 260; 

	// 대기실 유저 초대 요청
	public static final int REQUEST_USER_INVITE = 270; 
	// 초대를 받는다
	public static final int REQUEST_USER_INVITE_ACCEPT = 271;	
	// 초대를 받지않는다
	public static final int REQUEST_USER_INVITE_NOT_ACCEPT = 272;
	
	public static final int REQUEST_USER_INVITE_OVERLAP = 273;

	// 강제 퇴장
	public static final int REQUEST_USER_FORCED_EXIT = 280;
	
	// 다른 클라이언트에게 귓속말 요청
	public static final int REQUEST_WHISPER = 300; 
	
	public static final int REQUEST_USER_OUT = 444;

}
 
