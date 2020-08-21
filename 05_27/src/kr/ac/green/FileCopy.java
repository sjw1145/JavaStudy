package kr.ac.green;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FileCopy extends JFrame {
	private int fileInputSize = 20;

	private JTextField tfSrc;
	private JTextField tfCopy;

	private JButton btnSrc;
	private JButton btnCopy;

	private JTextField tfSize;

	private JButton btnRun;

	private JTextArea taResult;

	private JFileChooser chooser;

	private File srcFile;
	private File copyFile;

	public FileCopy() {
		init();
		setDisplay();
		addListeners();
		showFrame();
	}

	private void init() {
		tfSrc = new JTextField(fileInputSize);
		tfSrc.setEditable(false);
		tfSrc.setBorder(new LineBorder(Color.GRAY, 1));
		tfCopy = new JTextField(fileInputSize);
		tfCopy.setEditable(false);
		tfCopy.setBorder(new LineBorder(Color.GRAY, 1));

		btnSrc = new JButton("����");
		btnCopy = new JButton("����");

		tfSize = new JTextField(25);
		tfSize.setHorizontalAlignment(JTextField.CENTER);

		btnRun = new JButton("����");

		taResult = new JTextArea(7, 25);
		taResult.setEditable(false);

		chooser = new JFileChooser("c:\\");
	}

	private void setDisplay() {
		JPanel pnlNorth = new JPanel(new GridLayout(0, 1));

		JPanel pnlSrc = new JPanel();
		pnlSrc.add(tfSrc);
		pnlSrc.add(btnSrc);

		setTitledBorder(pnlSrc, "��������");

		JPanel pnlCopy = new JPanel();
		pnlCopy.add(tfCopy);
		pnlCopy.add(btnCopy);

		setTitledBorder(pnlCopy, "���纻����");

		pnlNorth.add(pnlSrc);
		pnlNorth.add(pnlCopy);

		JPanel pnlCenter = new JPanel(new BorderLayout());

		JPanel pnlSize = new JPanel();
		pnlSize.add(tfSize);
		setTitledBorder(pnlSize, "����ũ��");

		JPanel pnlRun = new JPanel();
		pnlRun.add(btnRun);

		JScrollPane scroll = new JScrollPane(taResult);
		setTitledBorder(scroll, "������");

		pnlCenter.add(pnlSize, BorderLayout.NORTH);
		pnlCenter.add(pnlRun, BorderLayout.CENTER);
		pnlCenter.add(scroll, BorderLayout.SOUTH);

		add(pnlNorth, BorderLayout.NORTH);
		add(pnlCenter, BorderLayout.CENTER);

	}

	private void setTitledBorder(JComponent cmp, String title) {
		cmp.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1), title));
	}

	private void addListeners() {
		ActionListener aListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object src = ae.getSource();
				if (src == btnSrc) {
					selectSrc();
				} else if (src == btnCopy) {
					selectCopy();
				} else {
					copy();
				}
			}
		};

		btnSrc.addActionListener(aListner);
		btnCopy.addActionListener(aListner);
		btnRun.addActionListener(aListner);
	}

	private void showFrame() {
		setTitle("���Ϻ���");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void copy() {
		boolean flag = true;
		if (srcFile == null) {
			JOptionPane.showMessageDialog(this, "���������� �����Ͻÿ�");
			flag = false;
		}

		if (flag && copyFile == null) {
			JOptionPane.showMessageDialog(this, "���纻������ �����Ͻÿ�");
			flag = false;
		}

		String size = tfSize.getText();
		if (flag && size.trim().length() == 0) {
			JOptionPane.showMessageDialog(this, "����ũ�⸦ �Է°�����");
			flag = false;
		}

		int bufferSize = 0;
		if (flag) {
			try {
				bufferSize = Integer.parseInt(size);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "������ ũ��� ���ڸ� �Է��ϼ���");
				flag = false;
			}
		}

		if (flag) {
			FileInputStream fis = null;
			FileOutputStream fos = null;

			try {
				fis = new FileInputStream(srcFile);
				fos = new FileOutputStream(copyFile);

				int count = -1;
				byte[] buffer = new byte[bufferSize];
				long time = System.currentTimeMillis();
				while ((count = fis.read(buffer)) != -1) {
					fos.write(buffer, 0, count);
				}
				time = System.currentTimeMillis() - time;
				JOptionPane.showMessageDialog(this, "����Ϸ�");

				StringBuffer buf = new StringBuffer();
				buf.append("- �۾� ���� -\n");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(a hh:mm:ss)");

				buf.append("- �۾� �Ͻ� :" + sdf.format(new Date()) + "\n");
				buf.append("���� ���� : " + srcFile.getPath() + "\n");
				buf.append("���纻 ���� : " + copyFile.getPath() + "\n");
				buf.append("- ���� ũ��  : " + srcFile.length() + "bytes\n");
				buf.append("- �ҿ� �ð� :" + time + " ms");
				
				taResult.setText(buf.toString());

			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "������ ������ �߻�");
			} finally {
				MyUtils.closeAll(fis, fos);
			}

		}
	}

	private void selectCopy() {
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			copyFile = chooser.getSelectedFile();
			tfCopy.setText(copyFile.getPath());
		}
	}

	private void selectSrc() {

		int result = chooser.showOpenDialog(this);

		// �����ϰ� Ȯ�� ������ ��
		if (result == JFileChooser.APPROVE_OPTION) {
			srcFile = chooser.getSelectedFile();
			tfSrc.setText(srcFile.getPath());
		}
	}

	public static void main(String[] args) {
		new FileCopy();
	}
}