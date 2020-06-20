package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeAttendanceRow implements Initializable {

	// fxml fields
	public Label labelDate;
	public Label labelWeekday;
	public Label labelTimeIn;
	public Label labelTimeInExpected;

	private long _date;
	private short _timeIn;
	private short _timeInExpected;

	public EmployeeAttendanceRow(long date, short timeIn, short timeInExpected) {
		_date = date;
		_timeIn = timeIn;
		_timeInExpected = timeInExpected;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelTimeIn.setText(Short.toString(_timeIn));
		labelTimeInExpected.setText(Short.toString(_timeInExpected));
		labelDate.setText(DatePickerDialog.getDateString(_date));
		labelWeekday.setText(new SimpleDateFormat("EEEE").format(new Date(_date)));
	}
}
