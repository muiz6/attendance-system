package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HolidayRow implements Initializable {

	// fxml fields
	public Label labelDay;
	public Label labelDate;

	private final String _date;
	private final String _day;

	public HolidayRow(long timestamp) {
		_date = DatePickerDialog.getDateString(timestamp);
		_day = new SimpleDateFormat("EEEE").format(new Date(timestamp));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelDay.setText(_day);
		labelDate.setText(_date);
	}
}
