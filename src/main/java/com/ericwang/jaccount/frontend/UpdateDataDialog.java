package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ericwang.jaccount.backend.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;

public class UpdateDataDialog extends JDialog {
	private JButton acceptB;
	private PrettyConsumptionRecord record;
	private JPanel jp;
	private PrettyConsumptionRecordController controller;
	private Object[] categories;
	private CashFlowTable table;

	public UpdateDataDialog(JFrame frame, Object[] categories) {
		super(frame, "新增資料");
		this.categories = categories;

		setLayout(new BorderLayout());

		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(new MoneyInput());
		jp.add(new DatePicker());
		jp.add(new CategoryPicker(categories));
		jp.add(new Description());

		add(jp, BorderLayout.NORTH);

		acceptB = new JButton("confirm");
		add(acceptB, BorderLayout.SOUTH);

		setActionListener();
		setSize(500, 300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public void setRecord(PrettyConsumptionRecord record) {
		this.record = record;
		MoneyInput mi = (MoneyInput) jp.getComponent(0);
		DatePicker dp = (DatePicker) jp.getComponent(1);
		CategoryPicker cp = (CategoryPicker) jp.getComponent(2);
		Description des = (Description) jp.getComponent(3);

		mi.setMoney(Integer.toString(record.getAmountOfMoney()));
		dp.setDate(record.getDate());
		cp.setCategories(record.getCategory());
		des.setDescription(record.getDescription());
	}
	
	private void updateRecord() {
		MoneyInput mi = (MoneyInput) jp.getComponent(0);
		DatePicker dp = (DatePicker) jp.getComponent(1);
		CategoryPicker cp = (CategoryPicker) jp.getComponent(2);
		Description des = (Description) jp.getComponent(3);

		record.setAmountOfMoney(mi.getMoney());
		record.setDate(dp.getDate());
		record.setCategory(cp.getCategory());
		record.setDescription(des.getString());
	}

	public void setTable(CashFlowTable table) {
		this.table = table;
	}

	public void setController(PrettyConsumptionRecordController controller) {
		this.controller = controller;
	}

	private void setActionListener() {
		acceptB.addActionListener(e -> {
			updateRecord();
			controller.updateDb(record, categories);
			table.initTable(controller);
			setVisible(false);
		});
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

		public void init() {
			textF.setText("");
		}

		public void setMoney(String s) {
			textF.setText(s);
		}

		public int getMoney() {
			return Integer.parseInt(textF.getText());
		}
	}

	private class CategoryPicker extends JPanel {
		JLabel label01;
		JComboBox<Object> dropDownList;
		Object[] categories;

		public CategoryPicker(Object[] categories) {
			super(new FlowLayout());

			label01 = new JLabel("類別");
			add(label01);

			this.categories = categories;
			dropDownList = new JComboBox<>(categories);
			add(dropDownList);
		}

		public void setCategories(String s) {
			for (int i = 0; i < categories.length; i++) {
				if (s.equals(categories[i])) {
					dropDownList.setSelectedIndex(i);
				}
			}
		}

		public Object[] getCategories() {
			return categories;
		}

		public String getCategory() {
			return (String) dropDownList.getSelectedItem();
		}
	}

	private class Description extends JPanel {
		JLabel label01;
		JTextField textF;

		public Description() {
			super(new FlowLayout());

			label01 = new JLabel("備註");
			add(label01);

			textF = new JTextField();
			textF.setColumns(25);
			add(textF);
		}

		public void setDescription(String s) {
			textF.setText(s);
		}

		public void init() {
			textF.setText("");
		}

		public String getString() {
			return textF.getText();
		}
	}
}
