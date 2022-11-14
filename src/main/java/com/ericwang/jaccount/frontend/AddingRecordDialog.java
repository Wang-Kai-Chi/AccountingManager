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

import com.ericwang.jaccount.backend.CashFlowRecord;
import com.ericwang.jaccount.backend.CashFlowRecordService;

public class AddingRecordDialog extends JDialog {
	private JButton acceptB;
	private CashFlowRecord cashFlowRecord;
	private JPanel jp;
	private CashFlowRecordService cashFlowRecordService;
	public AddingRecordDialog(JFrame frame, Object[] categories) {
		super(frame, "新增資料");

		setLayout(new BorderLayout());

		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(new TypePicker());
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

	public void setCashFlowRecordService(CashFlowRecordService cashFlowRecordService) {
		this.cashFlowRecordService = cashFlowRecordService;
	}

	public CashFlowRecord getCashFlowRecord() {
		return cashFlowRecord;
	}

	private void setActionListener() {
		acceptB.addActionListener(e -> {
			setVisible(false);
			writeRecord();
		});
	}

	private void writeRecord() {
		MoneyInput mi = (MoneyInput) jp.getComponent(1);
		DatePicker dp = (DatePicker) jp.getComponent(2);
		CategoryPicker cp = (CategoryPicker) jp.getComponent(3);
		Description des = (Description) jp.getComponent(4);

		cashFlowRecord = new CashFlowRecord(mi.getMoney(), dp.getDate(), cp.getCategory(), des.getString());

		for (int i = 0; i < cp.getCategories().length; i++) {
			if (cp.getCategory().equals((String) cp.getCategories()[i]))
				cashFlowRecord.setCategory_id(i + 1);
		}

		cashFlowRecordService.getRecordList().add(cashFlowRecord);

		System.out.println(cashFlowRecord);
		
		for(CashFlowRecord c:cashFlowRecordService.getRecordList())
			System.out.println(c);
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
		Object[] categories;

		public CategoryPicker(Object[] categories) {
			super(new FlowLayout());

			label01 = new JLabel("類別");
			add(label01);

			this.categories = categories;
			dropDownList = new JComboBox<>(categories);
			add(dropDownList);
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

		public String getString() {
			return textF.getText();
		}
	}
}
