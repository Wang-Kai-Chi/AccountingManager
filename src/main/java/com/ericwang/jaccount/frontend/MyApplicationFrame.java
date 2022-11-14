package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.CashFlowRecordService;
import com.ericwang.jaccount.backend.ConsumptionCategory;
import com.ericwang.jaccount.backend.ConsumptionCategoryService;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;

public class MyApplicationFrame extends JFrame {
	private CashFlowTable cashFlowTable;
	private AddingRecordDialog dialog;
	private CashFlowRecordService cfrs;

	public MyApplicationFrame(CashFlowRecordService cfrs, ConsumptionCategoryService ccs) {
		super("記帳本");
		this.cfrs = cfrs;

		setLayout(new BorderLayout());

		cashFlowTable = new CashFlowTable(cfrs);
		JScrollPane jsp = new JScrollPane(cashFlowTable);
		add(jsp, BorderLayout.CENTER);

		ManagePanel managePanel = new ManagePanel(this);
		add(managePanel, BorderLayout.NORTH);

		initDialog(ccs);

		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initDialog(ConsumptionCategoryService ccs) {
		ArrayList<String> names = new ArrayList<>();

		for (ConsumptionCategory c : ccs.getRecordList())
			names.add(c.getName());

		dialog = new AddingRecordDialog(this, names.toArray());
		dialog.setCashFlowRecordService(cfrs);
	}

	private class ManagePanel extends JPanel {
		private JButton addB, refreshB;
		
		public ManagePanel(JFrame frame) {
			super(new FlowLayout());
			
			addB = new JButton("新增");
			refreshB = new JButton("刷新");

			add(addB);
			add(refreshB);

			setActionListeners();
		}

		private void setActionListeners() {
			addB.addActionListener(e -> {
				dialog.setVisible(true);
				cashFlowTable.add();
			});
			
			refreshB.addActionListener(e -> {
				cfrs.insertNewRecordIfIdIsZero();
				cfrs.refresh();
				cashFlowTable.initTable();
			});
		}
	}
}
