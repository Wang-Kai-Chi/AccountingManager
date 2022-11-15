package com.ericwang.jaccount.frontend.widget;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TypePicker extends JPanel {
	JLabel label01;
	JComboBox<String> dropDownList;

	public TypePicker() {
		super(new FlowLayout());

		label01 = new JLabel("選擇類型");
		add(label01);

		String[] content = new String[] { "支出", "收入" };
		dropDownList = new JComboBox<>(content);
		dropDownList.setSelectedIndex(0);

		add(dropDownList);
	}

	public String getType() {
		return (String) dropDownList.getSelectedItem();
	}
}