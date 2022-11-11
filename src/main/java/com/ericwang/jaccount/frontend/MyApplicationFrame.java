package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.MyApplicationBackEnd;

public class MyApplicationFrame extends JFrame{
	private CashFlowTable cashFlowTable;
	private MyApplicationBackEnd backEnd;
	
	public MyApplicationFrame(MyApplicationBackEnd backEnd) {
		super("記帳本");
		
		setLayout(new BorderLayout());
		
		cashFlowTable = new CashFlowTable(backEnd.getController());
		JScrollPane jsp = new JScrollPane(cashFlowTable);
		add(jsp, BorderLayout.CENTER);
		
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
