package com.muiz6.system.attendance.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class AddEmployeeContent {

	@FXML
	private Button _btnTimeSame;
	@FXML
	private Button _btnTimeMonday;
	@FXML
	private Button _btnTimeTuesday;
	@FXML
	private Button _btnTimeWednesday;
	@FXML
	private Button _btnTimeSunday;
	@FXML
	private Button _btnTimeSaturday;
	@FXML
	private Button _btnTimeFriday;
	@FXML
	private Button _btnTimeThursday;
	@FXML
	private RadioButton _btnRadioDifferent;
	@FXML
	private RadioButton _btnRadioSame;

	public void onOptionSelect(ActionEvent event) {
		Button[] arr = {
				_btnTimeMonday,
				_btnTimeTuesday,
				_btnTimeWednesday,
				_btnTimeThursday,
				_btnTimeFriday,
				_btnTimeSaturday,
				_btnTimeSunday};
		Object source = event.getSource();
		if (source == _btnRadioSame && _btnRadioSame.isSelected()) {
			_btnTimeSame.setDisable(false);
			for (final Button i: arr) {
				i.setDisable(true);
			}
		}
		else if (source == _btnRadioDifferent
				&& _btnRadioDifferent.isSelected()) {
			_btnTimeSame.setDisable(true);
			for (final Button i: arr) {
				i.setDisable(false);
			}
		}
	}
}
