package com.ericwang.jaccount.frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AddingRecordDialog extends JDialog {
	private JButton acceptB;

	public AddingRecordDialog(JFrame frame) {
		super(frame, "新增資料");

		setLayout(new BorderLayout());

		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		jp.add(new TypePicker());
		jp.add(new MoneyInput());
		jp.add(new CategoryPicker());
		setupDatePicker(jp);
	    
		add(jp,BorderLayout.NORTH);
		
		acceptB = new JButton("confirm");
		add(acceptB, BorderLayout.SOUTH);

		setActionListener();
		setSize(500, 300);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	private void setupDatePicker(JPanel jp) {
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		jp.add(datePicker);
		
	}
	
	private void setActionListener() {
		acceptB.addActionListener(e->setVisible(false));
	}

	private class TypePicker extends JPanel {
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
	}
	
	private class MoneyInput extends JPanel {
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
	}
	
	private class CategoryPicker extends JPanel {
		JLabel label01;
		JComboBox<String> dropDownList;

		public CategoryPicker(){
			super(new FlowLayout());

			label01 = new JLabel("類別");
			add(label01);

			String[] cate = new String[] {"food", "transportation", "bill", "clothes"};
			dropDownList = new JComboBox<>(cate);
			add(dropDownList);
		}
	}
	
	public class DateLabelFormatter extends AbstractFormatter {

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
