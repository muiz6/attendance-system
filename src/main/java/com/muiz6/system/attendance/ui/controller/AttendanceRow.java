package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.ui.Strings;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import com.muiz6.system.attendance.ui.control.TimePickerDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class AttendanceRow implements Initializable {

	private final int _id;
	private final String _name;
	private final long _joinDate;
	private final short _timeIn;
	@FXML
	private Label _labelName;
	@FXML
	private Label _labelId;
	@FXML
	private Label _labelJoinDate;
	@FXML
	private ToggleButton _btnAttendance;

	AttendanceRow(int id, String name, long joinDate, short timeIn) {
		_id = id;
		_name = name;
		_joinDate = joinDate;
		_timeIn = timeIn;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_labelId.setText(Strings.PREFIX_EMPLOYEE_ID + _id);
		_labelName.setText(Strings.PREFIX_EMPLOYEE_NAME + _name);
		_labelJoinDate.setText(Strings.PREFIX_EMPLOYEE_JOIN_DATE
				+ DatePickerDialog.getDateString(_joinDate));
		switch (_timeIn) {
			case Constants.TIME_IN_ABSENT:
				_btnAttendance.setText(Strings.TEXT_ABSENT);
				break;
			case Constants.TIME_IN_HOLIDAY:
				_btnAttendance.setText(Strings.TEXT_HOLIDAY);
				_btnAttendance.setDisable(true);
				break;
			default:
				_btnAttendance.setText(Strings.TEXT_PRESENT);
				_btnAttendance.setSelected(true);
		}
	}

	public void onAttendanceBtnClick(ActionEvent event) {
		if (event.getSource() == _btnAttendance) {
			// button is just pressed and selected
			if (_btnAttendance.isSelected()) {
				Repository.markAttendance(_id, TimePickerDialog.currentTime());
				_btnAttendance.setText(Strings.TEXT_PRESENT);
			}
			else {
				String msg = MessageFormat.format("Mark Absent of {0}?",
						_name);
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
						msg,
						ButtonType.NO,
						ButtonType.YES);
				alert.setTitle("Mark Absent");
				alert.setHeaderText(null);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					Repository.markAttendance(_id, Constants.TIME_IN_ABSENT);
					_btnAttendance.setText(Strings.TEXT_ABSENT);
				}
				else {
					// restore state
					_btnAttendance.setSelected(true);
				}
			}
		}
	}
}
