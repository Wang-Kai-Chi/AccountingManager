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
    private RecordDialog dialog;
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
        dialog = new RecordDialog(this, categories);
        dialog.setController(controller);
    }

    private class ManagePanel extends JPanel {
        private JButton addB, refreshB;

        public ManagePanel() {
            super(new FlowLayout());

            addB = new JButton("新增");
            refreshB = new JButton("刷新");

            add(addB);
            add(refreshB);

            setActionListeners();
        }

        private void setActionListeners() {
            addB.addActionListener(e -> {
                dialog.setVisible(true);
                cashFlowTable.add();
            });

            refreshB.addActionListener(e -> {
                controller.insertNewRecordIfIdIsZero(categories);
                controller.refresh();
                cashFlowTable.initTable(controller);
            });
        }
    }
}
