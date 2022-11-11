package com.ericwang.jaccount.frontend;

import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AddingRecordDialog extends JDialog{

	public AddingRecordDialog(JFrame frame) {
		super(frame, "新增資料");
		
		setSize(500, 300);
		setVisible(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
