package kr.ac.green;

public class RequestProtocol {

	public static final int REQUEST_NICK_CHECK = 100;
	public static final int REQUEST_ENTER_CHATTINGROOM = 120;
	public static final int REQUEST_RANDOM_ENTER_CHATTINGROOM = 130;
	public static final int REQUEST_CREATE_CHATTINGROOM = 140;
	public static final int REQUEST_WATINGROOM_MSG_INPUT = 160;
	public static final int REQUEST_MASTER_ENTRUST = 200;
	public static final int REQUEST_MASTER_ENTRUST_RECEIVE = 201;
	public static final int REQUEST_MASTER_ENTRUST_NOT_RECEIVE = 202;
	public static final int REQUEST_CHANGE_SETTING_ROOM = 220;
	public static final int REQUEST_CHATTINGROOM_EXIT = 230;
	public static final int REQUEST_CHATTINGROOM_HOST_EXIT = 231;
	public static final int REQUEST_CHATTINGROOM_MSG_INPUT = 240;
	public static final int REQUEST_ALL_USER_DATA = 250;
	public static final int REQUEST_SEARCH_USER = 260;
	public static final int REQUEST_USER_INVITE = 270;
	public static final int REQUEST_USER_INVITE_ACCEPT = 271;
	public static final int REQUEST_USER_INVITE_NOT_ACCEPT = 272;
	public static final int REQUEST_USER_INVITE_OVERLAP = 273;
	public static final int REQUEST_USER_FORCED_EXIT = 280;
	public static final int REQUEST_WHISPER = 300;
	public static final int REQUEST_USER_OUT = 444;

}
