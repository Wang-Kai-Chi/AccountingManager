package com.ericwang.jaccount.frontend;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ericwang.jaccount.backend.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecord;
import com.ericwang.jaccount.backend.scr.SingleConsumptionRecordController;

public class CashFlowTable extends JTable {
	private MyTableModel tableModel;
	private String[] headers;
	
	public CashFlowTable() {
		tableModel = new MyTableModel();
		
		setModel(tableModel);
		
		headers = new String[] {
			"日期","金額", "類別", "備註"
		};
	}
	
	public void initTable(PrettyConsumptionRecordController controller) {
		tableModel.setColumnIdentifiers(headers);
		tableModel.setRowCount(controller.getRecordList().size());
		tableModel.setColumnCount(headers.length);
		
		for (int i = 0; i < controller.getRecordList().size(); i++) {
			PrettyConsumptionRecord c = controller.getRecordList().get(i);
			//tableModel.setValueAt(c.getId(),i, 0);
			tableModel.setValueAt(c.getDate(), i, 0);
			tableModel.setValueAt(c.getAmountOfMoney(), i, 1);
			tableModel.setValueAt(c.getCategory(), i, 2);
			tableModel.setValueAt(c.getDescription(), i, 3);

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