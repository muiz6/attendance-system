package com.muiz6.system.attendance.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;

import java.net.URL;
import java.util.ResourceBundle;

public class TimePicker implements Initializable {

	private final Dialog<Short> _dialog;
	@FXML
	private Button _btnOk;
	@FXML
	private CheckBox _checkBoxMarkHoliday;
	@FXML
	private ComboBox<String> _comboBoxPeriod;
	@FXML
	private ComboBox<Integer> _comboBoxMinute;
	@FXML
	private ComboBox<Integer> _comboBoxHour;

	public TimePicker(Dialog<Short> dialog) {
		_dialog = dialog;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		for (int i = 0; i < 60; i++) {
			_comboBoxMinute.getItems().add(i);
		}

		for (int i = 1; i <= 12; i++) {
			_comboBoxHour.getItems().add(i);
		}

		_comboBoxPeriod.getSelectionModel().selectLast();
		_comboBoxMinute.getSelectionModel().selectFirst();
		_comboBoxHour.getSelectionModel().selectFirst();
	}

	public void onCheckBoxClick(ActionEvent actionEvent) {
		if (actionEvent.getSource() == _checkBoxMarkHoliday) {
			if (_checkBoxMarkHoliday.isSelected()) {
				_comboBoxHour.setDisable(true);
				_comboBoxMinute.setDisable(true);
				_comboBoxPeriod.setDisable(true);
			}
			else {
				_comboBoxHour.setDisable(false);
				_comboBoxMinute.setDisable(false);
				_comboBoxPeriod.setDisable(false);
			}
		}
	}

	public void onButtonClick(ActionEvent actionEvent) {
		if (actionEvent.getSource() == _btnOk) {
			if (_checkBoxMarkHoliday.isSelected()) {
				_dialog.setResult((short) -1); // holiday state
			}
			else {
				int h = _comboBoxHour.getSelectionModel()
						.getSelectedItem();
				final int m = _comboBoxMinute.getSelectionModel()
						.getSelectedItem();
				final String period = _comboBoxPeriod.getSelectionModel()
						.getSelectedItem();
				if (period.equals("am")) {
					h %= 12;
				}
				else { // period is pm
					h = h % 12 + 12;
				}
				short timeInMinutes = (short) (h * 60 + m);
				_dialog.setResult(timeInMinutes);
			}
			_dialog.close();
		}
	}

	public void setTime(short timeInMinutes) {
		int h = timeInMinutes / 60;
		h %= 12;
		if (h == 0) {
			h = 12;
		}
		final int indexM = timeInMinutes % 60;
		_comboBoxHour.getSelectionModel().select(h - 1);
		_comboBoxMinute.getSelectionModel().select(indexM);
		if (timeInMinutes >= 720) {
			_comboBoxPeriod.getSelectionModel().selectLast();
		}
		else {
			_comboBoxPeriod.getSelectionModel().selectFirst();
		}
	}

	public void disableHolidayCheckbox(boolean state) {
		_checkBoxMarkHoliday.setDisable(state);
	}
}
