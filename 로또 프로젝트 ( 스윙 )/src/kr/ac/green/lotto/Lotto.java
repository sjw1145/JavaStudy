package kr.ac.green.lotto;

import java.util.ArrayList;

public class Lotto {
	private char lottoType;

	// 로또 번호를 담을 ArrayList
	private ArrayList<Integer> lotto;

	// 일치번호
	private Integer[] sameNumber;

	// 결과
	private int resultRank;

	public int getResult() {
		return resultRank;
	}

	public void setResult(int resultRank) {
		this.resultRank = resultRank;
	}

	public Integer[] getSameNumber() {
		return sameNumber;
	}

	public void setSameNumber(Integer[] sameNumber) {
		this.sameNumber = sameNumber;
	}
	
	public Lotto(char str) {
		this();
		setLottoType(str);
	}

	public Lotto() {
		lotto = new ArrayList<Integer>();
		
	}

	public Lotto(ArrayList<Integer> lotto) {
		this.lotto = lotto;
	}

	public ArrayList<Integer> getLotto() {
		return lotto;
	}

	public void setLotto(ArrayList<Integer> lotto) {
		this.lotto = lotto;
	}

	public char getLottoType() {
		return lottoType;
	}

	public void setLottoType(char lottoType) {
		this.lottoType = lottoType;
	}

	public void addNumber(Integer num) {
		lotto.add(num);
	}

	public int indexOf(Integer num) {
		return lotto.indexOf(num);
	}

	public int remove(int n) {
		return lotto.remove(n);
	}

}