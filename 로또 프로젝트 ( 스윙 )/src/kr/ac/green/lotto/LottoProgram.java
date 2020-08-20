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

	// ��÷ ��ȣ ����
	private Lotto addResultNumber() {
		ArrayList<Integer> temp = resultNumCreate();
		Lotto resultLotto = new Lotto(temp);
		return resultLotto;
	}

	// ��÷ ���
	public void lottoResult(ArrayList<Lotto> userLotto) {
		ArrayList<Integer> tempResult = getResultNumber().getLotto();

		// ���ʽ� ��ȣ �����ϰ� ����
		int bonus_num = tempResult.remove(BONUS_NUMBER_INDEX);

		itr = userLotto.iterator();
		while (itr.hasNext()) {

			Lotto temp = (Lotto) itr.next();

			// temp.getLotto()�� �״�� ����ϸ� �����Ͱ� �սǵ�
			ArrayList<Integer> tempItem = new ArrayList<Integer>();

			for (Integer number : temp.getLotto()) {
				tempItem.add(number);
			}

			// ��ġ�ϴ� ��ȣ�� ������
			if (tempItem.retainAll(tempResult)) {

				Integer[] sameNumber = new Integer[tempItem.size()];

				for (int i = 0; i < tempItem.size(); i++) {
					sameNumber[i] = tempItem.get(i);
				}

				// �ζ� Ŭ������ ��ġ�ϴ� ��ȣ �߰�.
				temp.setSameNumber(sameNumber);
				// ��� ��� ����
				temp.setResult(resultRank(tempItem, bonus_num));
			}
		}
	}

	public ArrayList<Integer> numberCreate() {
		return lottoNumberCreate();
	}

	// ��÷��ȣ ������
	private ArrayList<Integer> resultNumCreate() {
		ArrayList<Integer> resultLotto;

		Integer bonusNumber;

		resultLotto = lottoNumberCreate();

		do {
			bonusNumber = (int) (Math.random() * 45) + 1;
			// ��ȣ�� ���� ���� ������ �߰�
			if (!resultLotto.contains(bonusNumber)) {
				resultLotto.add(bonusNumber);
			}

		} while (resultLotto.size() == BONUS_NUMBER_INDEX);

		return resultLotto;
	}

	// �ζ� ��ȣ �ڵ� ����
	private ArrayList<Integer> lottoNumberCreate() {
		HashSet<Integer> temp = new HashSet<Integer>();

		while (temp.size() <= LOTTO_NUMBER_MAX) {
			temp.add((int) (Math.random() * 45) + 1);
		}

		ArrayList<Integer> list = new ArrayList<Integer>(temp);
		Collections.sort(list);

		return list;
	}

	// ��� ����(������� �ζ� ��ȣ�� ���ʽ� ��ȣ)
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

	// 2���� �� ī��Ʈ ���
	public String rankChance(ArrayList<UserNumberPanelItem> userItem) {
		String value = null;
		// �ζ� ��������
		ArrayList<Lotto> copyItem = new ArrayList<Lotto>();

		for (int i = 0; i < userItem.size(); i++) {
			copyItem.add(userItem.get(i).getUserLotto());
		}

		int count = 1;

		boolean isCheck = true;

		ArrayList<Integer> temp;

		while (isCheck) {
			// ��÷��ȣ �����ϱ�
			ArrayList<Integer> lottoA = resultNumCreate();

			// ���ʽ���ȣ ����
			int bonusNumber = lottoA.remove(BONUS_NUMBER_INDEX);

			for (int i = 0; i < copyItem.size(); i++) {
				// ����� �ζ� ����
				temp = new ArrayList<Integer>(copyItem.get(i).getLotto());

				// retain all
				temp.retainAll(lottoA);

				int n = temp.size();
				if (n == RANK_2 && copyItem.get(i).getLotto().contains(bonusNumber)) {
					value = copyItem.get(i).getLottoType() + "��°�� �ζǰ� " + count + " ���� 2�� ��÷";
					isCheck = false;
				} else if (n == RANK_1) {
					value = copyItem.get(i).getLottoType() + "��°�� �ζǰ� " + count + " ���� 1�� ��÷";
					isCheck = false;
				}
				count++;
			}
		}
		return value;
	}

}