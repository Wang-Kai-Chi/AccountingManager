package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
import com.ericwang.jaccount.frontend.widget.CategoryPicker;
import com.ericwang.jaccount.frontend.widget.DatePicker;
import com.ericwang.jaccount.frontend.widget.Description;
import com.ericwang.jaccount.frontend.widget.MoneyInput;

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
}
