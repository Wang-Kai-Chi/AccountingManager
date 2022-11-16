package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
import com.ericwang.jaccount.frontend.widget.CategoryPicker;
import com.ericwang.jaccount.frontend.widget.DatePicker;
import com.ericwang.jaccount.frontend.widget.TextInputPanel;

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
		jp.add(new TextInputPanel("金額", 10));
		jp.add(new DatePicker());
		jp.add(new CategoryPicker(categories));
		jp.add(new TextInputPanel("備註", 25));

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
			try {
				addRecordToController();
				controller.insertIntoDb(record, categories);
				table.initTable(controller);
				setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "你沒有完成填寫");
			}
		});
	}

	private void addRecordToController() {
		TextInputPanel mi = (TextInputPanel) jp.getComponent(0);
		DatePicker dp = (DatePicker) jp.getComponent(1);
		CategoryPicker cp = (CategoryPicker) jp.getComponent(2);
		TextInputPanel des = (TextInputPanel) jp.getComponent(3);

		record = new PrettyConsumptionRecord(dp.getDate(), Integer.parseInt(mi.getText()), cp.getCategory(), des.getText());

		controller.getRecordList().add(record);

		mi.init();
		dp.init();
		des.init();
	}
}
