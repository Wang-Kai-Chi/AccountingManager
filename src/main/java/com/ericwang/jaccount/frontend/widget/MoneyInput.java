package com.ericwang.jaccount.frontend.widget;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoneyInput extends JPanel {
	JLabel label01;
	JTextField textF;

	public MoneyInput() {
		super(new FlowLayout());

		label01 = new JLabel("金額");
		add(label01);

		textF = new JTextField();
		textF.setColumns(15);
		add(textF);
	}

	public void init() {
		textF.setText("");
	}

	public void setMoney(String s) {
		textF.setText(s);
	}

	public int getMoney() {
		return Integer.parseInt(textF.getText());
	}
}