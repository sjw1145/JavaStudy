package kr.ac.green.lotto;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * UserNumberPanelItem �� ǥ���ϱ� ���� Ŭ����
 */

public class UserNumberPanel extends Panel {

	private ArrayList<UserNumberPanelItem> itemList;

	public UserNumberPanel(ArrayList<Lotto> userLotto) {
		itemList = new ArrayList<UserNumberPanelItem>();
		
		// �ζ��� �� ��ŭ �г��� �����Ѵ�.
		for (int i = 0; i < userLotto.size(); i++) {
			itemList.add(new UserNumberPanelItem(userLotto.get(i)));
		}

		setDisplay();
	}

	public ArrayList<UserNumberPanelItem> getItemList() {
		return itemList;
	}

	private void setDisplay() {
		setLayout(new GridLayout(0, 1));

		// �ζ��� �г� �� ��ŭ ���� �гο� �߰��Ѵ�.
		for (int i = 0; i < itemList.size(); i++) {
			add(itemList.get(i));
		}
	}
}