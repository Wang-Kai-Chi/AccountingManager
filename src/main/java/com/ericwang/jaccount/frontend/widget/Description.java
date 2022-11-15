package com.ericwang.jaccount.frontend.widget;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Description extends JPanel {
	JLabel label01;
	JTextField textF;

	public Description() {
		super(new FlowLayout());

		label01 = new JLabel("備註");
		add(label01);

		textF = new JTextField();
		textF.setColumns(25);
		add(textF);
	}

	public void setDescription(String s) {
		textF.setText(s);
	}

	public void init() {
		textF.setText("");
	}

	public String getString() {
		return textF.getText();
	}
}