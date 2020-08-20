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
 * UserNumberPanelItem 을 표현하기 위한 클래스
 */

public class UserNumberPanel extends Panel {

	private ArrayList<UserNumberPanelItem> itemList;

	public UserNumberPanel(ArrayList<Lotto> userLotto) {
		itemList = new ArrayList<UserNumberPanelItem>();
		
		// 로또의 수 만큼 패널을 생성한다.
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

		// 로또의 패널 수 만큼 현재 패널에 추가한다.
		for (int i = 0; i < itemList.size(); i++) {
			add(itemList.get(i));
		}
	}
}