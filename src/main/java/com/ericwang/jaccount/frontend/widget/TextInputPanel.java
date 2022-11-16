package com.ericwang.jaccount.frontend.widget;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextInputPanel extends JPanel{
	private JLabel label;
	private JTextField textF;

	public TextInputPanel(String summary, int col) {
		super(new FlowLayout());

		label = new JLabel(summary);
		add(label);

		textF = new JTextField();
		textF.setColumns(col);
		add(textF);
	}

	public void setText(String s) {
		textF.setText(s);
	}

	public void init() {
		textF.setText("");
	}

	public String getText() {
		return textF.getText();
	}
}
