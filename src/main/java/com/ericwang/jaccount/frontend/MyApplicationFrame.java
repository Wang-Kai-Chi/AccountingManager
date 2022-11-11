package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.CashFlowRecordRepository;

public class MyApplicationFrame extends JFrame{
	private CashFlowTable cashFlowTable;
	private JButton addButton;
	
	public MyApplicationFrame(CashFlowRecordRepository repo) {
		super("記帳本");
		
		setLayout(new BorderLayout());
		
		cashFlowTable = new CashFlowTable(repo);
		JScrollPane jsp = new JScrollPane(cashFlowTable);
		add(jsp, BorderLayout.CENTER);
		
		JPanel jPanel = new JPanel(new FlowLayout());
		addButton = new JButton("add");
		jPanel.add(addButton);
		add(jPanel, BorderLayout.NORTH);
		
		setActionListeners(this);
		
		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setActionListeners(JFrame frame) {
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = new String[] {
					"1", "2", "3"
				};
				JOptionPane.showInputDialog(frame, "輸入金額", "新增消費", 
						JOptionPane.PLAIN_MESSAGE, null, options, "1");
			}
		});
	}
}
