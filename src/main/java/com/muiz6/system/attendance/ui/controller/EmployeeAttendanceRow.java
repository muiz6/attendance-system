package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.ui.Strings;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import com.muiz6.system.attendance.ui.control.TimePickerDialog;
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

	private final long _date;
	private final short _timeIn;
	private final short _timeInExpected;

	public EmployeeAttendanceRow(long date, short timeIn, short timeInExpected) {
		_date = date;
		_timeIn = timeIn;
		_timeInExpected = timeInExpected;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
		if (_timeIn == Constants.TIME_IN_ABSENT) {
			labelTimeIn.setText(Strings.TEXT_ABSENT);
		}
		else if (_timeIn == Constants.TIME_IN_HOLIDAY) {
			labelTimeIn.setText(Strings.TEXT_HOLIDAY);
		}
		else {
			final Date date = TimePickerDialog.getDate(_timeIn);
			labelTimeIn.setText(format.format(date));
		}

		if (_timeInExpected == Constants.TIME_IN_HOLIDAY) {
			labelTimeInExpected.setText(Strings.TEXT_HOLIDAY);
		}
		else {
			final Date date = TimePickerDialog.getDate(_timeInExpected);
			labelTimeInExpected.setText(format.format(date));
		}
		labelDate.setText(DatePickerDialog.getDateString(_date));
		labelWeekday.setText(new SimpleDateFormat("EEEE").format(new Date(_date)));
	}
}
