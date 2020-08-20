
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class EmojiDialog extends JDialog {
	public static final LineBorder boder = new LineBorder(Color.BLUE, 1);

	private MakePlanPanel owner;
	private JLabel icon;

	ArrayList<JLabel> arIcons = new ArrayList<JLabel>();
	public static final String[] name = { "work_study.png", "work_exam.png", "event_shopping_grocery.png",
			"event_shopping.png", "place_hospital.png", "place_cafe.png", "event_cake.png", "event_concert.png",
			"event_family.png", "event_graduation.png", "event_haircut_female.png", "event_haircut_male.png", "food_soju.png",
			"food_barbeque.png", "food_mara.png", "food_meat.png", "food_noodles.png", "food_pizza.png",
			"food_sushi.png", "food-basket.png", "sport_football.png", "sport_hiking.png",
			"sport_pilates.png", "sport_swimming.png", "sport_weight.png", "sports_run.png", "mark_stars.png", };

	// 맨 밑 확인, 닫기 버튼
	private JButton btnOk;
	private JButton btnDelete;

	// =========================================
	// 생성자
	public EmojiDialog(MakePlanPanel owner) {
		this.owner = owner;

		setModal(true);
		init();
		setDisplay();
		addListener();
		showFrame();
	}

	// ==========================================
	// 메서드
	private void init() {

		// 1. 파일명이 String 배열인 이미지들을 JLabel에 넣고
		// 2. 각각의 JLabel 배경색 흰색 설정 
		// 2. 각각의 JLabel에 listener 붙이기, 선택된 이미지 테두리 설정
		// 3. 생성된 JLabel들을 ArrayList에 넣기
		for (int idx = 0; idx < name.length; idx++) {
			JLabel temp = new JLabel(new ImageIcon(name[idx]), JLabel.CENTER);
			Decoration.setLblWhite(temp);
			temp.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					icon = (JLabel) e.getSource();
					icon.setBorder(new BevelBorder(BevelBorder.LOWERED));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					icon.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					icon = (JLabel) e.getSource();
					System.out.println(icon);
					owner.setIcon(icon.getIcon());
				}
			});

			arIcons.add(temp);
		}

		// 맨 밑 닫기 버튼
		btnOk = new JButton("확인");
		btnDelete = new JButton("삭제");
		
		btnOk.setBackground(Color.WHITE);
		btnDelete.setBackground(Color.WHITE);
	}

	// pnlCenter에 arIcons(아이콘 이미지를 담고 있는 ArrayList)를 부착
	private JPanel returnPnlCenter() {
		JPanel pnlCenter = new JPanel(new GridLayout(3, 3));
		Decoration.setPnlWhite(pnlCenter);
		for (int idx = 0; idx < arIcons.size(); idx++) {
			pnlCenter.add(arIcons.get(idx));
		}
		return pnlCenter;
	}

	private void setDisplay() {
		setLayout(new BorderLayout());
		JPanel pnlSouth = new JPanel();
		returnPnlCenter();

		pnlSouth.add(btnOk);
		pnlSouth.add(btnDelete);

		add(returnPnlCenter(), BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);
		
		Decoration.setPnlWhite(pnlSouth);
	}

	private void addListener() {
		//확인 버튼 누르면 창 끄기
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		//삭제 버튼 누르면 이모티콘 취소
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setIcon(null);
			}
		});

	}

	private void showFrame() {
		setSize(370, 190);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
}