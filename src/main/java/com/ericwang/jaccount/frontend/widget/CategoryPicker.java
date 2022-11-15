package com.ericwang.jaccount.frontend.widget;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CategoryPicker extends JPanel{
	JLabel label01;
	JComboBox<Object> dropDownList;
	Object[] categories;

	public CategoryPicker(Object[] categories) {
		super(new FlowLayout());

		label01 = new JLabel("類別");
		add(label01);

		this.categories = categories;
		dropDownList = new JComboBox<>(categories);
		add(dropDownList);
	}

	public void setCategories(String s) {
		for (int i = 0; i < categories.length; i++) {
			if (s.equals(categories[i])) {
				dropDownList.setSelectedIndex(i);
			}
		}
	}

	public Object[] getCategories() {
		return categories;
	}

	public String getCategory() {
		return (String) dropDownList.getSelectedItem();
	}
}