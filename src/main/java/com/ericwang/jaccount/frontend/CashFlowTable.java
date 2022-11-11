package com.ericwang.jaccount.frontend;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ericwang.jaccount.backend.CashFlowRecordRepository;
import com.ericwang.jaccount.backend.SingleConsumptionRecordController;

public class CashFlowTable extends JTable{
	private MyTableModel tableModel;
	private SingleConsumptionRecordController controller;
	
	public CashFlowTable(SingleConsumptionRecordController controller) {
		this.controller = controller;
		
		tableModel = new MyTableModel();
		tableModel.setColumnIdentifiers(controller.getHeaders());
		setModel(tableModel);
	}
	
	private class MyTableModel extends DefaultTableModel{

		@Override
		public int getRowCount() {
			return controller.getRows();
		}

		@Override
		public int getColumnCount() {
			return controller.getCols();
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@Override
		public Object getValueAt(int row, int column) {
			return controller.getData(row+1, column+1);
		}
		
	}
}