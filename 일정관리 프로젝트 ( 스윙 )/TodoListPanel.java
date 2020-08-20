
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TodoListPanel extends JPanel {
	private JLabel lblTodo;
	private JLabel lblIcon;

	private PlanDTO planDTO;
	private PlanListModal planListModal;

	public TodoListPanel(PlanListModal planListModal ,PlanDTO planDTO) {
		this.planListModal = planListModal;
		this.planDTO = planDTO;
		init();
		setDisplay();
		addListener();
		
		lblTodo.setText(planDTO.getPlanTitle());
		JLabel lblIcon2 = planDTO.getLblIcon();
		if(lblIcon2 != null) {
			lblIcon.setIcon(lblIcon2.getIcon());
		}
	}

	public TodoListPanel(JLabel l, String s) {
		init();
		setDisplay();
		addListener();
		lblTodo.setText(s);
	}

	private void init() {
		lblTodo = new JLabel();
		lblIcon = new JLabel();
	}

	private void setDisplay() {
		setLayout(new BorderLayout());
		add(lblTodo, BorderLayout.CENTER);
		add(lblIcon, BorderLayout.WEST);

		setOpaque(true);
		setBackground(Color.WHITE);

		setBorder(new LineBorder(Color.GRAY, 1));
		
		setPreferredSize(new Dimension(250, 30));

	}

	private void addListener() {
		MouseListener mListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new PlanInfoDialog(planListModal,planDTO);
			}
		};

		lblTodo.addMouseListener(mListener);
	}
}
