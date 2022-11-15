package com.ericwang.jaccount.frontend;

import java.awt.*;

import javax.swing.*;

import com.ericwang.jaccount.backend.SearchSet;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecord;
import com.ericwang.jaccount.backend.pcr.PrettyConsumptionRecordController;
import com.ericwang.jaccount.frontend.widget.CategoryPicker;
import com.ericwang.jaccount.frontend.widget.DatePicker;

public class MyApplicationFrame extends JFrame {
    private CashFlowTable cashFlowTable;
    private InsertDataDialog insertDialog;
    private UpdateDataDialog updateDialog;
    private PrettyConsumptionRecordController controller;
    private JLabel sumLabel;
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

        ManagePanel managePanel = new ManagePanel(this);
        add(managePanel, BorderLayout.SOUTH);

        sumLabel = new JLabel();
        add(sumLabel, BorderLayout.NORTH);
        initSumLabel();

        initDialog();

        SearchPanel searchPanel = new SearchPanel(categories);
        add(searchPanel, BorderLayout.EAST);

        setSize(800, 480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initSumLabel() {
        int sum = 0;

        for (PrettyConsumptionRecord p : controller.getRecordList())
            sum += p.getAmountOfMoney();

        sumLabel.setText("總消費金額: " + sum + "元");
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
        private final MyApplicationFrame myApplicationFrame;
        private JButton addB, updateB, deleteB, refreshB;

        public ManagePanel(MyApplicationFrame myApplicationFrame) {
            super(new FlowLayout());
            this.myApplicationFrame = myApplicationFrame;

            addB = new JButton("新增");
            updateB = new JButton("更新");
            refreshB = new JButton("刷新");
            deleteB = new JButton("刪除");

            add(addB);
            add(updateB);
            add(deleteB);
            add(refreshB);

            setActionListeners();
        }

        private void setActionListeners() {
            addB.addActionListener(e -> {
                myApplicationFrame.insertDialog.setVisible(true);
                myApplicationFrame.cashFlowTable.add();
                myApplicationFrame.initSumLabel();
            });

            deleteB.addActionListener(e -> {
                try {
                    int check = JOptionPane.showConfirmDialog(this, "確定要刪除這筆資料嗎", "刪除", JOptionPane.DEFAULT_OPTION);

                    if (check == JOptionPane.YES_OPTION) {
                        myApplicationFrame.controller.deleteFromDb(getRecordFromController());
                        myApplicationFrame.cashFlowTable.initTable(myApplicationFrame.controller);
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "請先選擇資料");
                }
            });

            updateB.addActionListener(e -> {
                try {
                    myApplicationFrame.updateDialog.setRecord(getRecordFromController());
                    myApplicationFrame.updateDialog.setVisible(true);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "請先選擇資料");
                }
            });

            refreshB.addActionListener(e -> {
                myApplicationFrame.controller.refresh();
                myApplicationFrame.cashFlowTable.initTable(myApplicationFrame.controller);
                myApplicationFrame.initSumLabel();
            });
        }

        private PrettyConsumptionRecord getRecordFromController() {
            return myApplicationFrame.controller.getRecordList().get(myApplicationFrame.cashFlowTable.getSelectedRow());
        }
    }

    private class SearchPanel extends JPanel {
        private JPanel jPanel;
        private JLabel title;
        private JButton confirmB;

        public SearchPanel(Object[] categories) {
            super(new BorderLayout());

            jPanel = new JPanel();
            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
            DatePicker datePicker = new DatePicker();
            datePicker.getLabel().setText("查詢日期");

            jPanel.add(datePicker);
            jPanel.add(new JCheckBox("同月"));
            jPanel.add(new JCheckBox("同年"));
            jPanel.add(new JCheckBox("選擇分類"));
            jPanel.add(new CategoryPicker(categories));

            title = new JLabel("Search: ");
            confirmB = new JButton("start");

            add(jPanel, BorderLayout.CENTER);
            add(title, BorderLayout.NORTH);
            add(confirmB, BorderLayout.SOUTH);
        }

        public SearchSet getSearchSet() {
            DatePicker datePicker = (DatePicker) jPanel.getComponent(0);
            JCheckBox checkMonthBox = (JCheckBox) jPanel.getComponent(1);
            JCheckBox checkYearBox = (JCheckBox) jPanel.getComponent(2);
            JCheckBox checkCategoryBox = (JCheckBox) jPanel.getComponent(3);
            CategoryPicker categoryPicker = (CategoryPicker) jPanel.getComponent(4);

            return new SearchSet(
                    datePicker.getDate(),
                    checkMonthBox.isSelected(),
                    checkYearBox.isSelected(),
                    checkCategoryBox.isSelected(),
                    categoryPicker.getCategory());
        }
    }
}
