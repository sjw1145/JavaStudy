package kr.ac.green;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KickImgDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public KickImgDlg(MainFrame mf) {
		super(mf, true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel(new ImageIcon("img.jpg"));
			contentPanel.add(lblNewLabel);
		}
		setTitle("강퇴당함 ㅋㅋㅋ 메롱");
		pack();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}