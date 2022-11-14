package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddingRecordDialog extends JDialog {
	private JButton acceptB;

	public AddingRecordDialog(JFrame frame, Object[] categories) {
		super(frame, "新增資料");

		setLayout(new BorderLayout());

		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(new TypePicker());
		jp.add(new MoneyInput());
		jp.add(new CategoryPicker(categories));
		jp.add(new DatePicker());
	    
		add(jp,BorderLayout.NORTH);
		
		acceptB = new JButton("confirm");
		add(acceptB, BorderLayout.SOUTH);

		setActionListener();
		setSize(500, 300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	private void setActionListener() {
		acceptB.addActionListener(e->setVisible(false));
	}

	private class TypePicker extends JPanel {
		JLabel label01;
		JComboBox<String> dropDownList;

		public TypePicker() {
			super(new FlowLayout());

			label01 = new JLabel("選擇類型");
			add(label01);

			String[] content = new String[] { "支出", "收入" };
			dropDownList = new JComboBox<>(content);
			dropDownList.setSelectedIndex(0);

			add(dropDownList);
		}
		
		public String getType() {
			return (String) dropDownList.getSelectedItem();
		}
	}
	
	private class MoneyInput extends JPanel {
		JLabel label01;
		JTextField textF;

		public MoneyInput() {
			super(new FlowLayout());

			label01 = new JLabel("金額");
			add(label01);

			textF = new JTextField();
			textF.setColumns(15);
			add(textF);
		}
		
		public int getMoney() {
			return Integer.parseInt(textF.getText());
		}
	}
	
	private class CategoryPicker extends JPanel {
		JLabel label01;
		JComboBox<Object> dropDownList;

		public CategoryPicker(Object[] categories){
			super(new FlowLayout());

			label01 = new JLabel("類別");
			add(label01);

			dropDownList = new JComboBox<>(categories);
			add(dropDownList);
		}
		
		public String getCategory() {
			return (String) dropDownList.getSelectedItem();
		}
	}
	
}
