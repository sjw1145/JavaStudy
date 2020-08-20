package kr.ac.green;

public class ResponseProtocol {
	// 서버접속
	public static final int RESPONSE_NICK_CHECK_OK = 1000;
	public static final int RESPONSE_NICK_CHECK_NOTIFY = 1101;
	public static final int RESPONSE_NICK_CHECKC_NO = 1102;

	// 채팅 대기실
	public static final int RESPONSE_ENTER_CHATTINGROOM_OK = 1120;
	public static final int RESPONSE_ENTER_CHATTINGROOM_NOTIFY = 1121;
	public static final int RESPONSE_ENTER_CHATTINGROOM_UPDATE = 1122;
	public static final int RESPONSE_ENTER_CHATTINGROOM_UI_UPDATE = 1123;

	public static final int RESPONSE_RANDOM_ENTER_CHATTINGROOM_OK = 1130;
	public static final int RESPONSE_RANDOM_ENTER_CHATTINGROOM_NOTIFY = 1131;
	public static final int RESPONSE_RANDOM_ENTER_CHATTINGROOM_NO = 1132;
	public static final int RESPONSE_RANDOM_ENTER_CHATTINGROOM_UPDATE_UI = 1133;

	public static final int RESPONSE_CREATE_CHATTINGROOM_OK = 1140;
	public static final int RESPONSE_CREATE_CHATTINGROOM_NO = 1141;
	public static final int RESPONSE_WATING_MSG_INPUT_NO = 1160;
	public static final int RESPONSE_WATINGROOM_MSG_INPUT_OK = 1161;
	// 채팅방

	public static final int RESPONSE_MASTER_ENTRUST_OK = 1200;
	public static final int RESPONSE_MASTER_ENTRUST_OK_NOTIFY = 1201;
	public static final int RESPONSE_MASTER_ENTERUST_NO = 1202;
	public static final int RESPONSE_MASTER_ENTRUST_DELIVERY = 1203;
	public static final int RESPONSE_CHANGE_SETTING_ROOM_NO = 1221;
	public static final int RESPONSE_CHANGE_SETTING_ROOM_NOTIFY = 1222;
	public static final int RESPONSE_DUE_TO_SETTING_ROOM_WAITINGROOM_UPDATE = 1223;
	public static final int RESPONSE_CHATTINGROOM_EXIT_OK = 1230;
	public static final int RESPONSE_CHATTINGROOM_NOTIFY = 1231;
	public static final int RESPONSE_CHATTINGROOM_EXPLOSION = 1232;
	public static final int RESPONSE_DUE_TO_EXPLOSION_WAITINGROOM_UPDATE = 1233;

	public static final int RESPONSE_CHATTINGROOM_MSG_INPUT_OK = 1240;
	public static final int RESPONSE_CHATTINGROOM_MSG_INPUT_NO = 1241;
	public static final int RESPONSE_ALL_USER_DATA_OK = 1250;
	public static final int RESPONSE_ALL_USER_DATA_NO = 1251;
	public static final int RESPONSE_SEARCH_USER_OK = 1260;
	public static final int RESPONSE_SEARCH_USER_NO = 1261;
	
	
	public static final int RESPONSE_USER_INVITE_DELIVERY_OK = 1270;
	public static final int RESPONSE_DUE_TO_USER_INVITE_WAITINGROOM_UPDATE = 1271;
	public static final int RESPONSE_USER_INVITE_NOTIFY = 1272;
	public static final int RESPONSE_USER_INVITE_DELIVERY_NO = 1273;
	public static final int RESPONSE_USER_INVITE_DELIVERY = 1274;
	public static final int RESPONSE_USER_INVITE_DELIVERY_FALSE = 1275;
	public static final int RESPONSE_USER_INVITE_OVERLAP_MSG = 1276;
	
	// 귓속말
	public static final int RESPONSE_WHISPER_OK = 1301;
	public static final int RESPONSE_WHISPER_NO = 1302;
	//대기실나가기
	public static final int RESPONSE_USER_OUT_OK = 4444;
	public static final int RESPONSE_USER_OUT_UPDATE = 4445;
	
	public static final int RESPONSE_USER_FORCED_EXIT_OK = 1280;
	public static final int RESPONSE_USER_FORCED_EXIT_NOTIFY = 1281;
	public static final int RESPONSE_USER_FORCED_EXIT_NO = 1282;
	
	
}
 
