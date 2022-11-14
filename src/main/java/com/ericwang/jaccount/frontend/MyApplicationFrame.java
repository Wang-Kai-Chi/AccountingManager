package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.CashFlowRecordService;

public class MyApplicationFrame extends JFrame {
	private CashFlowTable cashFlowTable;
	private CashFlowRecordService service;
	private AddingRecordDialog dialog;

	public MyApplicationFrame(CashFlowRecordService service) {
		super("記帳本");

		this.service = service;

		setLayout(new BorderLayout());

		cashFlowTable = new CashFlowTable(service);
		JScrollPane jsp = new JScrollPane(cashFlowTable);
		add(jsp, BorderLayout.CENTER);

		ManagePanel managePanel = new ManagePanel(this);
		add(managePanel, BorderLayout.NORTH);

		dialog = new AddingRecordDialog(this);

		setSize(800, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class ManagePanel extends JPanel {
		private JButton addButton, refreshButton;

		public ManagePanel(JFrame frame) {
			super(new FlowLayout());

			addButton = new JButton("新增");
			refreshButton = new JButton("刷新");
			add(addButton);
			add(refreshButton);

			setActionListeners(frame);
		}

		private void setActionListeners(JFrame frame) {
			addButton.addActionListener(e -> dialog.setVisible(true));

			refreshButton.addActionListener(e -> {
				service.refresh();
				repaint();
			});
		}
	}
}
