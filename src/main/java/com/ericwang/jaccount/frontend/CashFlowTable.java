package com.ericwang.jaccount.frontend;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ericwang.jaccount.backend.CashFlowRecord;
import com.ericwang.jaccount.backend.CashFlowRecordService;

public class CashFlowTable extends JTable {
	private MyTableModel tableModel;
	private CashFlowRecordService service;

	public CashFlowTable(CashFlowRecordService service) {
		this.service = service;

		tableModel = new MyTableModel();
		tableModel.setColumnIdentifiers(service.getHeaders());
		setModel(tableModel);
	}

	private class MyTableModel extends DefaultTableModel {

		@Override
		public int getRowCount() {
			return service.getRecordList().size();
		}

		@Override
		public int getColumnCount() {
			return service.getHeaders().length;
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@Override
		public Object getValueAt(int row, int column) {
			String s = "";
			CashFlowRecord c = service.getRecordList().get(row);
			
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