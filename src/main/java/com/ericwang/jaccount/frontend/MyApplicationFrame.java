package com.ericwang.jaccount.frontend;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

import com.ericwang.jaccount.backend.SearchSet;
import com.ericwang.jaccount.backend.SqlCommandCollection;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
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
				controller.getFromDb();
				cashFlowTable.initTable(controller);
				initSumLabel();
			});
		}

		private PrettyConsumptionRecord getRecordFromController() {
			return controller.getRecordList().get(cashFlowTable.getSelectedRow());
		}
	}

	private class SearchPanel extends JPanel {
		private JPanel jPanel;
		private JLabel title;
		private JButton confirmB;
		private SearchSetAnalyst analyst;

		public SearchPanel(Object[] categories) {
			super(new BorderLayout());

			analyst = new SearchSetAnalyst();

			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
			DatePicker datePicker = new DatePicker();
			datePicker.getLabel().setText("查詢日期");

			jPanel.add(datePicker);
			jPanel.add(new JCheckBox("同月"));
			jPanel.add(new JCheckBox("同年"));
			jPanel.add(new JCheckBox("選擇分類"));
			jPanel.add(new CategoryPicker(categories));

			title = new JLabel("Search: ");
			confirmB = new JButton("start");

			add(jPanel, BorderLayout.CENTER);
			add(title, BorderLayout.NORTH);
			add(confirmB, BorderLayout.SOUTH);

			setActionListener();
		}

		private void setActionListener() {
			confirmB.addActionListener(e -> {
				runAnalyst();
				controller.getFromDb(analyst.getSql(), analyst.getSearchSet());
				cashFlowTable.initTable(controller);
			});
		}
		
		private void runAnalyst() {
			analyst.setSearchSet(getSearchSet());
			analyst.analyze();
			System.out.println(analyst.getCursor());
			
		}

		public SearchSet getSearchSet() {
			DatePicker datePicker = (DatePicker) jPanel.getComponent(0);
			JCheckBox checkMonthBox = (JCheckBox) jPanel.getComponent(1);
			JCheckBox checkYearBox = (JCheckBox) jPanel.getComponent(2);
			JCheckBox checkCategoryBox = (JCheckBox) jPanel.getComponent(3);
			CategoryPicker categoryPicker = (CategoryPicker) jPanel.getComponent(4);

			return new SearchSet(datePicker.getDate(), checkMonthBox.isSelected(), checkYearBox.isSelected(),
					checkCategoryBox.isSelected(), categoryPicker.getCategory());
		}
	}
}

class SearchSetAnalyst {
	private SearchSet searchSet;
	private String sql;
	private HashMap<String, String> sqlMap;
	private String[] keys;
	private String cursor;

	public SearchSetAnalyst() {
		sqlMap = new HashMap();

		keys = new String[] { "same day", "same month", "same month and same year", "same month and year and category",
				"category", "same year", "same month and category", "same year and category" };

		SqlCommandCollection command = new SqlCommandCollection();
		sqlMap.put(keys[0], command.sameday);
		sqlMap.put(keys[1], command.sameMonth);
		sqlMap.put(keys[2], command.sameMonthNYear);
		sqlMap.put(keys[3], command.sameMonthNYearNCategory);
		sqlMap.put(keys[4], command.category);
		sqlMap.put(keys[5], command.sameYear);
		sqlMap.put(keys[6], command.sameMonthNcategory);
		sqlMap.put(keys[7], command.sameYearNCategory);
	}

	public String getSql() {
		return sqlMap.get(cursor);
	}

	public void setSearchSet(SearchSet searchSet) {
		this.searchSet = searchSet;
	}
	
	

	public SearchSet getSearchSet() {
		return searchSet;
	}

	public String getCursor() {
		return cursor;
	}

	public void analyze() {
		if (searchSet.isSameMonth()) {
			if (searchSet.isSameYear()) {
				if (searchSet.isCategory()) {
					cursor = keys[3];
				} else {
					cursor = keys[2];
				}
			} else if (searchSet.isCategory())
				cursor = keys[6];
			else
				cursor = keys[1];
		} else if (searchSet.isSameYear()) {
			if (searchSet.isCategory())
				cursor = keys[7];
			else {
				cursor = keys[5];
			}
		} else if (searchSet.isCategory()) {
			cursor = keys[4];
		} else {
			cursor = keys[0];
		}
	}
}