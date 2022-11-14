package com.ericwang.jaccount.frontend;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ericwang.jaccount.backend.CashFlowRecord;
import com.ericwang.jaccount.backend.CashFlowRecordService;

public class CashFlowTable extends JTable {
	private MyTableModel tableModel;
	
	public CashFlowTable() {
		tableModel = new MyTableModel();
		
		setModel(tableModel);
	}
	
	public void initTable(CashFlowRecordService service) {
		tableModel.setColumnIdentifiers(service.getHeaders());
		tableModel.setRowCount(service.getRecordList().size());
		tableModel.setColumnCount(service.getHeaders().length);
		
		for (int i = 0; i < service.getRecordList().size(); i++) {
			CashFlowRecord c = service.getRecordList().get(i);
			tableModel.setValueAt(c.getId(), i, 0);
			tableModel.setValueAt(c.getAmount_of_money(), i, 1);
			tableModel.setValueAt(c.getDate(), i, 2);
			tableModel.setValueAt(c.getCategory_id(), i, 3);
			tableModel.setValueAt(c.getDescription(), i, 4);
		}
		
	}

	public void add() {
		tableModel.addRow(new Object[2]);
	}

	private class MyTableModel extends DefaultTableModel {

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}
}