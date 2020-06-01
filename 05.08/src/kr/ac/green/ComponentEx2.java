package kr.ac.green;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ComponentEx2 extends JFrame {
	
	public ComponentEx2() {
		//�ܹ� �Է�
		JTextField tfName = new JTextField(20);
		tfName.setText("���⿡ �Է��ϼ���");
		//tfName.setEditable(false); (���� ����)
		
		//�ƿ� �� ���� ���� (Ȱ��ȭ ����)
		tfName.setEnabled(false);
		
		JLabel lblName = new JLabel("�̸�");
		
		JPanel pnlName = new JPanel();
		pnlName.add(lblName);
		pnlName.add(tfName);
		
		//�幮 �Է�(��ũ�� �̶� ���� ����)
		JTextArea taInput = new JTextArea(6, 30);
		taInput.setText("����������������������");
		taInput.append("\n��������");
		
		//�ڵ� �� �ٲ�
		taInput.setLineWrap(true);
		//�ڵ� �� �ٲ��� �� � �������� �ٲ� ���ΰ� (�Ʒ��� �ܾ����)
		taInput.setWrapStyleWord(true);
		//��ũ�� �޾��ְ� ���� ���� �����ڷ� ����
		//�г��ε� ��ũ�� �ٰ� �޷��ִ� ��...
		JScrollPane scroll = new JScrollPane(taInput);
		//��ũ�� �ٸ� ���� �� ���ΰ�?
		
		/*
			�׻� ǥ��
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
			�ʿ��� ���� ǥ��
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

			��ũ�ѹ� ǥ����å ����
		*/
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPasswordField pwPass = new JPasswordField();
		pwPass.setText("hello");
		
		//�н����� â�� �Է����� �� �����ִ� ��..
		pwPass.setEchoChar('*');
		char[] password = pwPass.getPassword();
		//���ڹ迭 -> ��Ʈ��
		System.out.println(String.valueOf(password));
		
		System.out.println(password);
		
		
		add(pnlName, BorderLayout.NORTH);
		//add(taInput, BorderLayout.CENTER);
		add(scroll, BorderLayout.CENTER);
		add(pwPass, BorderLayout.SOUTH);
		
		setTitle("Component2");
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComponentEx2();
	}
}
