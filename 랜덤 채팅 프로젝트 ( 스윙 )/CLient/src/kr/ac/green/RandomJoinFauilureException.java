package kr.ac.green;

import java.io.Serializable;

public class RandomJoinFauilureException extends RuntimeException implements Serializable{
	int code = ResponseProtocol.RESPONSE_RANDOM_ENTER_CHATTINGROOM_NO;
	public RandomJoinFauilureException() {
//		JOptionPane.showMessageDialog(this, "어떤 방에도 입장 할 수 없음");
		
	}

}

 

