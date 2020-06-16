package com.muiz6.system.attendance.ui.control;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.controller.TimePicker;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.util.Pair;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePickerDialog extends Dialog<Short> {

	private final TimePicker _controller;

	public TimePickerDialog() {
		this.setTitle("Time Picker");
		final Pair<Node, TimePicker> pair =
				Util.getFxmlNodeAndController(Constants.RES_FXML_TIME_PICKER,
						c -> new TimePicker(this));
		this.getDialogPane().setContent(pair.getKey());
		_controller = pair.getValue();
	}

	/**
	 * @return current time in number of minutes since start of day
	 */
	public static short currentTime() {
		Date date = new Date();
		int h = Integer.parseInt(new SimpleDateFormat("HH").format(date));
		int m = Integer.parseInt(new SimpleDateFormat("mm").format(date));
		short time = (short) (h * 60 + m);
		return time;
	}

	/**
	 * set time of the day in minutes eg. 12:30pm will be 12*60 + 30 = 750 minutes
	 * @param minutes: time in minutes
	 */
	public void setTime(short minutes) {
		_controller.setTime(minutes);
	}

	public void disableHolidayCheckbox(boolean state) {
		_controller.disableHolidayCheckbox(state);
	}

	public void setHolidayCheckboxSelected(boolean state) {
		_controller.setHolidayCheckboxSelected(state);
	}

	public boolean isMarkForALL() {
		return _controller.isMarkForAll();
	}
}
