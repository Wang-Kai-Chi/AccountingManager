package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;
import com.ericwang.jaccount.frontend.widget.CategoryPicker;
import com.ericwang.jaccount.frontend.widget.DatePicker;

public class MyApplicationFrame extends JFrame {
	private CashFlowTable cashFlowTable;
	private InsertDataDialog insertDialog;
	private UpdateDataDialog updateDialog;
	private PrettyConsumptionRecordController controller;
	private JLabel sumLabel;
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

		sumLabel = new JLabel();
		add(sumLabel, BorderLayout.NORTH);
		initSumLabel();

		initDialog();
		
		SearchPanel searchPanel = new SearchPanel(categories);
		add(searchPanel, BorderLayout.EAST);

		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void initSumLabel() {
		int sum = 0;

		for (PrettyConsumptionRecord p : controller.getRecordList())
			sum += p.getAmountOfMoney();

		sumLabel.setText("總消費金額: " + sum + "元");
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
				initSumLabel();
			});

			deleteB.addActionListener(e -> {
				try {
					int check = JOptionPane.showConfirmDialog(this, "確定要刪除這筆資料嗎", "刪除", JOptionPane.DEFAULT_OPTION);

					if (check == JOptionPane.YES_OPTION) {
						controller.deleteFromDb(getRecordFromController());
						cashFlowTable.initTable(controller);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "請先選擇資料");
				}
			});

			updateB.addActionListener(e -> {
				try {
					updateDialog.setRecord(getRecordFromController());
					updateDialog.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "請先選擇資料");
				}
			});

			refreshB.addActionListener(e -> {
				controller.refresh();
				cashFlowTable.initTable(controller);
				initSumLabel();
			});
		}

		private PrettyConsumptionRecord getRecordFromController() {
			return controller.getRecordList().get(cashFlowTable.getSelectedRow());
		}
	}
	
	private class SearchPanel extends JPanel{
		private JPanel jPanel;
		private JLabel title;
		private JButton confirmB;
		
		public SearchPanel(Object[] categories) {
			super(new BorderLayout());
			
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
			DatePicker datePicker = new DatePicker();
			datePicker.getLabel().setText("查詢日期");
			
			jPanel.add(datePicker);
			jPanel.add(new JCheckBox("同月"));
			jPanel.add(new JCheckBox("同年"));
			jPanel.add(new CategoryPicker(categories));
			
			title = new JLabel("Search: ");
			confirmB = new JButton("start");
			
			add(jPanel, BorderLayout.CENTER);
			add(title, BorderLayout.NORTH);
			add(confirmB, BorderLayout.SOUTH);
		}
	}
}
