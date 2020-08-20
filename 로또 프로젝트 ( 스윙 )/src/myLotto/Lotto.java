package myLotto;

import java.util.ArrayList;

public class Lotto {
	private char lottoType;
	
	//로또 번호를 담을 ArrayList
	private ArrayList<Integer> lotto;
	
	//일치번호
	private Integer[] sameNumber;
	
	//결과
	private int resultRank;
	
	//============================생성자
	public Lotto (char str) {
		this();
		setLottoType(str);
	}

	private void setLottoType(char str) {
		// TODO Auto-generated method stub
		
	}
}
