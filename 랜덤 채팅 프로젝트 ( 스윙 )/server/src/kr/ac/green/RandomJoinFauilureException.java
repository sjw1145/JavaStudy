package kr.ac.green;

import java.io.Serializable;

public class RandomJoinFauilureException extends RuntimeException implements Serializable{
	public RandomJoinFauilureException() {
		super("랜덤 입장에 실패하였습니다.");
	}
}

