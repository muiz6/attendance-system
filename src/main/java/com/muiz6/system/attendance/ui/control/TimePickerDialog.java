package com.muiz6.system.attendance.ui.control;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.controller.TimePicker;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.util.Pair;

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
}
