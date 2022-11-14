package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecordController;
import com.ericwang.jaccount.backend.cc.ConsumptionCategory;
import com.ericwang.jaccount.backend.cc.ConsumptionCategoryController;

public class MyApplicationFrame extends JFrame {
	private CashFlowTable cashFlowTable;
	private RecordDialog dialog;
	private PrettyConsumptionRecordController pcrc;

	public MyApplicationFrame(PrettyConsumptionRecordController pcrc, Object[] categories) {
		super("記帳本");
		this.pcrc = pcrc;

		setLayout(new BorderLayout());

		cashFlowTable = new CashFlowTable();
		cashFlowTable.initTable(pcrc);
		JScrollPane jsp = new JScrollPane(cashFlowTable);
		add(jsp, BorderLayout.CENTER);

		ManagePanel managePanel = new ManagePanel(this);
		add(managePanel, BorderLayout.NORTH);

		initDialog(categories);

		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initDialog(Object[] categories) {
		dialog = new RecordDialog(this, categories);
		dialog.setController(pcrc);
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
				//pcrc.insertNewRecordIfIdIsZero();
				pcrc.refresh();
				cashFlowTable.initTable(pcrc);
			});
		}
	}
}
