package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;

public class MyApplicationFrame extends JFrame {
	private CashFlowTable cashFlowTable;
	private InsertDataDialog insertDialog;
	private UpdateDataDialog updateDialog;
	private PrettyConsumptionRecordController controller;
	private Object[] categories;

	public MyApplicationFrame(PrettyConsumptionRecordController controller, Object[] categories) {
		super("記帳本");
		this.controller = controller;
		this.categories = categories;

		setLayout(new BorderLayout());

		cashFlowTable = new CashFlowTable();
		cashFlowTable.initTable(controller);
		JScrollPane jsp = new JScrollPane(cashFlowTable);
		add(jsp, BorderLayout.CENTER);

		ManagePanel managePanel = new ManagePanel();
		add(managePanel, BorderLayout.SOUTH);

		initDialog();

		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initDialog() {
		insertDialog = new InsertDataDialog(this, categories);
		insertDialog.setController(controller);
		insertDialog.setTable(cashFlowTable);

		updateDialog = new UpdateDataDialog(this, categories);
		updateDialog.setController(controller);
		updateDialog.setTable(cashFlowTable);
	}

	private class ManagePanel extends JPanel {
		private JButton addB, updateB, deleteB, refreshB;

		public ManagePanel() {
			super(new FlowLayout());

			addB = new JButton("新增");
			updateB = new JButton("更新");
			refreshB = new JButton("刷新");
			deleteB = new JButton("刪除");

			add(addB);
			add(updateB);
			add(deleteB);
			add(refreshB);

			setActionListeners();
		}

		private void setActionListeners() {
			addB.addActionListener(e -> {
				insertDialog.setVisible(true);
				cashFlowTable.add();
			});

			deleteB.addActionListener(e -> {
				try {
					int check = JOptionPane.showConfirmDialog(this, "確定要刪除這筆資料嗎", "刪除", JOptionPane.DEFAULT_OPTION);
					
					if (check == JOptionPane.YES_OPTION) {
						controller.deleteFromDb(getRecordFromSelectedRow());
						cashFlowTable.initTable(controller);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "請先選擇資料");
				}
			});

			updateB.addActionListener(e -> {
				try {
					updateDialog.setRecord(getRecordFromSelectedRow());
					updateDialog.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "請先選擇資料");
				}
			});

			refreshB.addActionListener(e -> {
				controller.refresh();
				cashFlowTable.initTable(controller);
			});
		}

		private PrettyConsumptionRecord getRecordFromSelectedRow() {
			int id = (int) cashFlowTable.getValueAt(cashFlowTable.getSelectedRow(), 0);
			int money = (int) cashFlowTable.getValueAt(cashFlowTable.getSelectedRow(), 1);
			String date = (String) cashFlowTable.getValueAt(cashFlowTable.getSelectedRow(), 2);
			String category = (String) cashFlowTable.getValueAt(cashFlowTable.getSelectedRow(), 3);
			String des = (String) cashFlowTable.getValueAt(cashFlowTable.getSelectedRow(), 4);

			return new PrettyConsumptionRecord(id, money, date, category, des);
		}
	}
}
