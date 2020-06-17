package TablePack;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

class Human {
	private String name;
	private int age;
	private String addr;
	private String tel;

	public Human(String name, int age, String addr, String tel) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return name + "(" + age + ", " + addr + ", " + tel + ")";
	}

}

class TablePanel extends JPanel {
	private Human[] list;
	private HumanTableModel tableModel;

	public TablePanel(Human[] list) {
		super(new BorderLayout());
		setList(list);
		setOpaque(true);

		HumanTableModel model = new HumanTableModel();
		model.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table updateaed!!");
			}
		});

		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);

		add(scroll, BorderLayout.CENTER);
	}

	public void setList(Human[] list) {
		this.list = list;
	}

	public Human[] getList() {
		return list;
	}

	public static void createAndShowGUI() {
		// TODO Auto-generated method stub
		
	}

}

/* Custom TableModel for Human */
class HumanTableModel extends AbstractTableModel {
	// header
	private String[] colsName = { "Name", "Age", "Address", "Tel" };

	public HumanTableModel() {
	}

	/* If show your table's header, must implements this method */
	@Override
	public String getColumnName(int col) {
		return colsName[col];
	}

	/* If edit your table's cell, must implemnts this method */
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		Human temp = list[row];

		System.out.println("before : " + temp);
		String data = value.toString();
		switch (col) {
		case 0:
			temp.setName(data);
			break;
		case 1:
			temp.setAge(Integer.parseInt(data));
			break;
		case 2:
			temp.setAddr(data);
			break;
		case 3:
			temp.setTel(data);
			break;
		}
		System.out.println("modified : " + temp);

	}

	/*
	 * AbstractTableModel 의 추상 메서드
	 * 
	 * 1. getValueAt(int rowIndex, int columnIndex) : Object 2. getColumnCount()
	 * : int 3. getRowCount() : int
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object cellData = "undefined";

		Human temp = list[rowIndex];
		switch (columnIndex) {
		case 0:
			cellData = temp.getName();
			break;
		case 1:
			cellData = new Integer(temp.getAge());
			break;
		case 2:
			cellData = temp.getAddr();
			break;
		case 3:
			cellData = temp.getTel();
			break;
		}
		return cellData;
	}

	@Override
	public int getColumnCount() {
		return colsName.length;
	}

	@Override
	public int getRowCount() {
		return list.length;
	}

	public static void createAndShowGUI() {
		JFrame f = new JFrame();

		Human[] list = { new Human("김연아", 24, "서울", "010-1234-5678"), new Human("에일리", 27, "부산", "010-5437-5678"),
				new Human("문근영", 28, "인천", "010-3727-5678"), new Human("전지현", 32, "울산", "010-5432-5678"),
				new Human("김혜수", 40, "부산", "010-2436-5678"), };

		TablePanel contentPane = new TablePanel(list);
		f.setContentPane(contentPane);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
}

public class TableBasic {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				TablePanel.createAndShowGUI();
			}
		});
	}

}
