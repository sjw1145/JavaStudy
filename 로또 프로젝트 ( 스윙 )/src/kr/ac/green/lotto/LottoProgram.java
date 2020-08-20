package kr.ac.green.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class LottoProgram {
	public static final int LOTTO_NUMBER_MAX = 5;
	public static final int BONUS_NUMBER_INDEX = 6;

	public static final int RANK_1 = 6;
	public static final int RANK_2 = 5;
	public static final int RANK_3 = 5;
	public static final int RANK_4 = 4;
	public static final int RANK_5 = 3;

	private Iterator itr;

	private Lotto resultNumber;

	public Lotto getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(Lotto resultNumber) {
		this.resultNumber = resultNumber;
	}

	public void createResultNumber() {
		setResultNumber(addResultNumber());
	}

	// 당첨 번호 생성
	private Lotto addResultNumber() {
		ArrayList<Integer> temp = resultNumCreate();
		Lotto resultLotto = new Lotto(temp);
		return resultLotto;
	}

	// 당첨 결과
	public void lottoResult(ArrayList<Lotto> userLotto) {
		ArrayList<Integer> tempResult = getResultNumber().getLotto();

		// 보너스 번호 삭제하고 저장
		int bonus_num = tempResult.remove(BONUS_NUMBER_INDEX);

		itr = userLotto.iterator();
		while (itr.hasNext()) {

			Lotto temp = (Lotto) itr.next();

			// temp.getLotto()를 그대로 사용하면 데이터가 손실됨
			ArrayList<Integer> tempItem = new ArrayList<Integer>();

			for (Integer number : temp.getLotto()) {
				tempItem.add(number);
			}

			// 일치하는 번호가 있으면
			if (tempItem.retainAll(tempResult)) {

				Integer[] sameNumber = new Integer[tempItem.size()];

				for (int i = 0; i < tempItem.size(); i++) {
					sameNumber[i] = tempItem.get(i);
				}

				// 로또 클래스에 일치하는 번호 추가.
				temp.setSameNumber(sameNumber);
				// 등수 결과 리턴
				temp.setResult(resultRank(tempItem, bonus_num));
			}
		}
	}

	public ArrayList<Integer> numberCreate() {
		return lottoNumberCreate();
	}

	// 당첨번호 생성기
	private ArrayList<Integer> resultNumCreate() {
		ArrayList<Integer> resultLotto;

		Integer bonusNumber;

		resultLotto = lottoNumberCreate();

		do {
			bonusNumber = (int) (Math.random() * 45) + 1;
			// 번호가 존재 하지 않으면 추가
			if (!resultLotto.contains(bonusNumber)) {
				resultLotto.add(bonusNumber);
			}

		} while (resultLotto.size() == BONUS_NUMBER_INDEX);

		return resultLotto;
	}

	// 로또 번호 자동 생성
	private ArrayList<Integer> lottoNumberCreate() {
		HashSet<Integer> temp = new HashSet<Integer>();

		while (temp.size() <= LOTTO_NUMBER_MAX) {
			temp.add((int) (Math.random() * 45) + 1);
		}

		ArrayList<Integer> list = new ArrayList<Integer>(temp);
		Collections.sort(list);

		return list;
	}

	// 등수 리턴(사용자의 로또 번호와 보너스 번호)
	private int resultRank(ArrayList<Integer> temp, int bonus_num) {
		int n = temp.size();

		int result = 0;

		switch (n) {
		case RANK_1:
			result = 1;
			break;
		case RANK_3:
			if (n == RANK_2 && temp.contains(bonus_num)) {
				result = 2;
			}
			result = 3;
			break;
		case RANK_4:
			result = 4;
			break;
		case RANK_5:
			result = 5;
			break;
		}

		return result;
	}

	// 2등이 될 카운트 계산
	public String rankChance(ArrayList<UserNumberPanelItem> userItem) {
		String value = null;
		// 로또 가져오기
		ArrayList<Lotto> copyItem = new ArrayList<Lotto>();

		for (int i = 0; i < userItem.size(); i++) {
			copyItem.add(userItem.get(i).getUserLotto());
		}

		int count = 1;

		boolean isCheck = true;

		ArrayList<Integer> temp;

		while (isCheck) {
			// 당첨번호 생성하기
			ArrayList<Integer> lottoA = resultNumCreate();

			// 보너스번호 제거
			int bonusNumber = lottoA.remove(BONUS_NUMBER_INDEX);

			for (int i = 0; i < copyItem.size(); i++) {
				// 사용자 로또 복사
				temp = new ArrayList<Integer>(copyItem.get(i).getLotto());

				// retain all
				temp.retainAll(lottoA);

				int n = temp.size();
				if (n == RANK_2 && copyItem.get(i).getLotto().contains(bonusNumber)) {
					value = copyItem.get(i).getLottoType() + "번째의 로또가 " + count + " 만에 2등 당첨";
					isCheck = false;
				} else if (n == RANK_1) {
					value = copyItem.get(i).getLottoType() + "번째의 로또가 " + count + " 만에 1등 당첨";
					isCheck = false;
				}
				count++;
			}
		}
		return value;
	}

}