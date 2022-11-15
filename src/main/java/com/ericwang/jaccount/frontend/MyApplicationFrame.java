package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.ericwang.jaccount.backend.PrettyConsumptionRecordController;

public class MyApplicationFrame extends JFrame {
    private CashFlowTable cashFlowTable;
    private InsertDataDialog insertDialog;
    private UpdateDataDialog updateDialog;
    private PrettyConsumptionRecordController controller;
    private Object[] categories;

    public MyApplicationFrame(PrettyConsumptionRecordController controller, Object[] categories) {
        super("記帳本");
        this.controller = controller;
        this.categories = categories;

        setLayout(new BorderLayout());

        cashFlowTable = new CashFlowTable();
        cashFlowTable.initTable(controller);
        JScrollPane jsp = new JScrollPane(cashFlowTable);
        add(jsp, BorderLayout.CENTER);

        ManagePanel managePanel = new ManagePanel();
        add(managePanel, BorderLayout.NORTH);

        initDialog();

        setSize(800, 480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initDialog() {
        insertDialog = new InsertDataDialog(this, categories);
        insertDialog.setController(controller);
        insertDialog.setTable(cashFlowTable);
        
        updateDialog = new UpdateDataDialog(this, categories);
        updateDialog.setController(controller);
        updateDialog.setTable(cashFlowTable);
    }

    private class ManagePanel extends JPanel {
        private JButton addB, updateB,refreshB;

        public ManagePanel() {
            super(new FlowLayout());

            addB = new JButton("新增");
            updateB = new JButton("更新");
            refreshB = new JButton("刷新");

            add(addB);
            add(updateB);
            add(refreshB);

            setActionListeners();
        }

        private void setActionListeners() {
            addB.addActionListener(e -> {
                insertDialog.setVisible(true);
                cashFlowTable.add();
            });
            
            updateB.addActionListener(e->{
            	updateDialog.setVisible(true);
            });

            refreshB.addActionListener(e -> {
                controller.refresh();
                cashFlowTable.initTable(controller);
            });
        }
    }
}
