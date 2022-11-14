package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.CashFlowRecordService;
import com.ericwang.jaccount.backend.ConsumptionCategory;
import com.ericwang.jaccount.backend.ConsumptionCategoryService;
import com.ericwang.jaccount.backend.MyApplicationBackEnd;

public class MyApplicationFrame extends JFrame {
	private CashFlowTable cashFlowTable;
	private AddingRecordDialog dialog;

	public MyApplicationFrame(CashFlowRecordService cfrs, ConsumptionCategoryService ccs) {
		super("記帳本");

		setLayout(new BorderLayout());

		cashFlowTable = new CashFlowTable(cfrs);
		JScrollPane jsp = new JScrollPane(cashFlowTable);
		add(jsp, BorderLayout.CENTER);

		ManagePanel managePanel = new ManagePanel(this);
		add(managePanel, BorderLayout.NORTH);

		initDialog(ccs);
		
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initDialog(ConsumptionCategoryService ccs) {
		ArrayList<String> names = new ArrayList<>();
		
		for(ConsumptionCategory c:ccs.getRecordList())
			names.add(c.getName());
		
		dialog = new AddingRecordDialog(this, names.toArray());		
	}

	private class ManagePanel extends JPanel {
		private JButton addButton;

		public ManagePanel(JFrame frame) {
			super(new FlowLayout());

			addButton = new JButton("新增");
			
			add(addButton);

			setActionListeners(frame);
		}

		private void setActionListeners(JFrame frame) {
			addButton.addActionListener(e -> dialog.setVisible(true));
		}
	}
}
