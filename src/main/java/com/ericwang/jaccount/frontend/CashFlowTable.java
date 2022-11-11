package com.ericwang.jaccount.frontend;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ericwang.jaccount.backend.CashFlowRecordRepository;

public class CashFlowTable extends JTable{
	private MyTableModel tableModel;
	private CashFlowRecordRepository cashFlowRecordRepository;
	
	public CashFlowTable(CashFlowRecordRepository cashFlowRecordRepository) {
		tableModel = new MyTableModel();
		setModel(tableModel);
		
		this.cashFlowRecordRepository = cashFlowRecordRepository;
	}
	
	private class MyTableModel extends DefaultTableModel{

		@Override
		public int getRowCount() {
			return 7;
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		@Override
		public Object getValueAt(int row, int column) {
			return super.getValueAt(row, column);
		}
		
	}
}