package com.ericwang.jaccount.frontend;

import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DatePicker extends JPanel{
	private JLabel label01;
	private JDatePickerImpl datePicker;
	
	public DatePicker() {
		super(new FlowLayout());
		
		label01 = new JLabel("日期");
		
		UtilDateModel model = new UtilDateModel();
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		add(label01);
		add(datePicker);
	}
	
	public void init() {
		datePicker.getJFormattedTextField().setText("");
	}
	
	public String getDate() {
		return datePicker.getJFormattedTextField().getText();
	}
	
	private class DateLabelFormatter extends AbstractFormatter {
		
		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		
		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}
		
		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}
			
			return "";
		}
		
	}
}
