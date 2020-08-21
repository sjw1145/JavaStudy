import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class AutoCompletedEx extends JFrame {
	private JComboBox cb;
	private JTextField tf;
	private String[] words = {"apple", "apply", "adapter"};
	private DefaultComboBoxModel model;
	private Vector<String> vec;
	
	public AutoCompletedEx() {
		vec = new Vector<String>();
		model = new DefaultComboBoxModel<>(vec);
		cb = new JComboBox(model);
		cb.setEditable(true);
		
		cb.setUI(new BasicComboBoxUI(){
			@Override
			protected JButton createArrowButton() {
				return new JButton() {
					public int getWidth() {
						return 0;
					}
				};
			}
		});
		
		tf = (JTextField)(cb.getEditor().getEditorComponent());
		
	}
	
}
