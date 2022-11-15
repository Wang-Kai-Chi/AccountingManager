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

import com.ericwang.jaccount.backend.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;
import com.ericwang.jaccount.frontend.widget.CategoryPicker;
import com.ericwang.jaccount.frontend.widget.DatePicker;
import com.ericwang.jaccount.frontend.widget.Description;
import com.ericwang.jaccount.frontend.widget.MoneyInput;

public class InsertDataDialog extends JDialog {
	private JButton acceptB;
	private PrettyConsumptionRecord record;
	private JPanel jp;
	private PrettyConsumptionRecordController controller;
	private Object[] categories;
	private CashFlowTable table;

	public InsertDataDialog(JFrame frame, Object[] categories) {
		super(frame, "新增資料");
		this.categories = categories;

		setLayout(new BorderLayout());

		jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		// jp.add(new TypePicker());
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

	public void setTable(CashFlowTable table) {
		this.table = table;
	}

	public void setController(PrettyConsumptionRecordController controller) {
		this.controller = controller;
	}

	private void setActionListener() {
		acceptB.addActionListener(e -> {
			addRecordToController();
			controller.insertIntoDb(record, categories);
			table.initTable(controller);
			setVisible(false);
		});
	}

	private void addRecordToController() {
		MoneyInput mi = (MoneyInput) jp.getComponent(0);
		DatePicker dp = (DatePicker) jp.getComponent(1);
		CategoryPicker cp = (CategoryPicker) jp.getComponent(2);
		Description des = (Description) jp.getComponent(3);

		record = new PrettyConsumptionRecord(dp.getDate(),mi.getMoney(), cp.getCategory(), des.getString());

		controller.getRecordList().add(record);

		mi.init();
		dp.init();
		des.init();
	}
}
