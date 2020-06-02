package kr.ac.green;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * JDialog : â�� �����ϴ� Ŭ����.
 * 1. �޴��� ���� �� ����
 * 2. base container
 * 3. �⺻ ���̾ƿ� �Ŵ��� : BorderLayout
 * 4. modal ���� ���� : �θ�â�� ��ɵ��� ����
 */

public class MyDialog extends JDialog {

	private JButton btnClose;
	private MyFrame owner;

	public MyDialog(MyFrame owner) {
		// �θ�â ���� , Ÿ��Ʋ , modal
		super(owner, "MyDialog", true);
		this.owner = owner;

		JLabel lblInput = new JLabel("�Է� : ");
		JTextField tfInput = new JTextField(15);
		JButton btnInput = new JButton("���");

		JPanel pnlCenter = new JPanel();
		pnlCenter.add(lblInput);
		pnlCenter.add(tfInput);
		pnlCenter.add(btnInput);

		btnClose = new JButton("Close");
		btnInput.addActionListener((ae) -> {
			// 1.7 ������ MyFrame ���������� �� ����.
			String words = tfInput.getText();
			if (owner.exists(words)) {
				JOptionPane.showMessageDialog(MyDialog.this, "�̹� ���� �մϴ�", "�˸�", JOptionPane.WARNING_MESSAGE);
				tfInput.requestFocus();
				tfInput.selectAll();

			} else {
				owner.add(words);
				dispose();
			}

		});

		JPanel pnl = new JPanel();
		pnl.add(btnClose);

		add(pnlCenter, BorderLayout.CENTER);
		add(pnl, BorderLayout.SOUTH);

		// ����
		btnClose.addActionListener((ae) -> dispose());

		setTitle("���̾�α�");
		setSize(200, 200);
		// �θ� â�� �� �߾� ��ġ
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

}
