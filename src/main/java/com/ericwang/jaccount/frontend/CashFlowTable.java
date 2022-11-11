package com.ericwang.jaccount.frontend;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ericwang.jaccount.backend.CashFlowRecord;
import com.ericwang.jaccount.backend.CashFlowRecordRepository;
import com.ericwang.jaccount.backend.SingleConsumptionRecordController;

public class CashFlowTable extends JTable {
	private MyTableModel tableModel;
	private CashFlowRecordRepository repo;

	public CashFlowTable(CashFlowRecordRepository repo) {
		this.repo = repo;

		tableModel = new MyTableModel();
		tableModel.setColumnIdentifiers(repo.getHeaders());
		setModel(tableModel);
	}

	private class MyTableModel extends DefaultTableModel {

		@Override
		public int getRowCount() {
			return repo.getRecordList().size();
		}

		@Override
		public int getColumnCount() {
			return repo.getHeaders().length;
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@Override
		public Object getValueAt(int row, int column) {
			String s = "";
			CashFlowRecord c = repo.getRecordList().get(row);
			
			switch(column) {
				case 0:
					s = Integer.toString(c.getId());
					break;
				case 1:
					s = Integer.toString(c.getAmount_of_money());
					break;
				case 2:
					s = c.getDate();
					break;
				case 3:
					s = Integer.toString(c.getCategory_id());
					break;
				case 4:
					s = c.getDescription();
					break;
			}
			return s;
		}

	}
}