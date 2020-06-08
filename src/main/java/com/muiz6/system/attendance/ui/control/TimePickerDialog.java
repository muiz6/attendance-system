package com.muiz6.system.attendance.ui.control;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.controller.TimePicker;
import javafx.scene.Node;
import javafx.scene.control.Dialog;

public class TimePickerDialog extends Dialog<Short> {

	public TimePickerDialog() {
		setTitle("Time Picker");

		Node node = Util.getFxmlNode(Constants.RES_FXML_TIME_PICKER,
				c -> new TimePicker(this));
		getDialogPane().setContent(node);
	}
}
