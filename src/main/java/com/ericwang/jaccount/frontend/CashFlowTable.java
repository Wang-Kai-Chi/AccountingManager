package com.ericwang.jaccount.frontend;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ericwang.jaccount.backend.scr.SingleConsumptionRecord;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecordController;

public class CashFlowTable extends JTable {
	private MyTableModel tableModel;
	
	public CashFlowTable() {
		tableModel = new MyTableModel();
		
		setModel(tableModel);
	}
	
	public void initTable(SingleConsumptionRecordController controller) {
		tableModel.setColumnIdentifiers(controller.getHeaders());
		tableModel.setRowCount(controller.getRecordList().size());
		tableModel.setColumnCount(controller.getHeaders().length);
		
		for (int i = 0; i < controller.getRecordList().size(); i++) {
			SingleConsumptionRecord c = controller.getRecordList().get(i);
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