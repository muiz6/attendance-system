package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.dto.NewEmployeeDto;
import com.muiz6.system.attendance.ui.EmployeeItemEvent;
import com.muiz6.system.attendance.ui.Strings;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import com.muiz6.system.attendance.ui.control.TimePickerDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddEmployeeContent implements Initializable {

	@FXML
	private TextField _textName;
	@FXML
	private Button _btnBack;
	@FXML
	private Button _btnAdd;
	@FXML
	private TextField _textTimeTuesday;
	@FXML
	private TextField _textTimeMonday;
	@FXML
	private TextField _textTimeWednesday;
	@FXML
	private TextField _textTimeThursday;
	@FXML
	private TextField _textTimeSunday;
	@FXML
	private TextField _textTimeSaturday;
	@FXML
	private TextField _textTimeFriday;
	@FXML
	private TextField _textDate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// show today's date on join-date button by default
		final DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		final Date date = new Date();
		_textDate.setText(format.format(date));
	}

	// handles back date buttons
	public void onBtnClick(ActionEvent actionEvent) {
		final Object source = actionEvent.getSource();
		if (source == _btnBack) {

			// id will be ignored for back btn
			_btnBack.fireEvent(new EmployeeItemEvent((byte) 0,
					EmployeeItemEvent.BUTTON_TYPE_BACK));
		}
		else if (source == _btnAdd) {
			final NewEmployeeDto employee =  new NewEmployeeDto();
			employee.setName(_textName.getText());
			employee.setJoinDate(_getTextDateEpoch());
			employee.setTimeInMonday(_getTime(_textTimeMonday));
			employee.setTimeInTuesday(_getTime(_textTimeTuesday));
			employee.setTimeInWednesday(_getTime(_textTimeWednesday));
			employee.setTimeInThursday(_getTime(_textTimeThursday));
			employee.setTimeInFriday(_getTime(_textTimeFriday));
			employee.setTimeInSaturday(_getTime(_textTimeSaturday));
			employee.setTimeInSunday(_getTime(_textTimeSunday));
			Repository.addEmployee(employee, success -> {
				if (success) {
					final Alert alert = new Alert(Alert.AlertType.INFORMATION,
							"Employee Added Successfully",
							ButtonType.OK);
					alert.setTitle("Success");
					alert.setHeaderText(null);
					alert.showAndWait();
				}
				else {
					final Alert alert = new Alert(Alert.AlertType.WARNING,
							"Something went wrong!",
							ButtonType.OK);
					alert.setTitle("Failure");
					alert.setHeaderText(null);
					alert.showAndWait();
				}
			});
		}
	}

	public void onTextClick(MouseEvent mouseEvent) {
		Object source = mouseEvent.getSource();
		if (source == _textDate) {
			final DatePickerDialog dialog = new DatePickerDialog();
			final DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			dialog.setDate(_getTextDateEpoch());
			dialog.showAndWait();
			long epoch = dialog.getResult();
			final Date date = new Date();
			date.setTime(epoch);
			final String str = format.format(date);
			_textDate.setText(str);
		}
	}

	public void onTextTimeClick(MouseEvent event) {
		final TextField source = (TextField) event.getSource();

		TimePickerDialog dialog = new TimePickerDialog();
		final short time = _getTime(source);
		if (time == -1) { // holiday
			dialog.setHolidayCheckboxSelected(true);
			dialog.setTime((short) 0);
		}
		else {
			dialog.setTime(_getTime(source));
		}
		dialog.showAndWait();
		short result = dialog.getResult();

		// update all fields if mark all checkbox is selected
		if (dialog.isMarkForALL()) {
			final TextField[] textTimeArr = {
					_textTimeMonday,
					_textTimeTuesday,
					_textTimeWednesday,
					_textTimeThursday,
					_textTimeFriday,
					_textTimeSaturday,
					_textTimeSunday};
			if (result == -1) {
				for (final TextField textTime: textTimeArr) {
					textTime.setText(Strings.TEXT_HOLIDAY);
				}
			}
			else {
				for (final TextField textTime: textTimeArr) {
					_setTime(result, textTime);
				}
			}
		}
		else {

			// result can only be -1 for time in buttons as mark holiday
			if (result == -1) {
				source.setText(Strings.TEXT_HOLIDAY);
			} else {
				_setTime(dialog.getResult(), source);
			}
		}
	}

	// call only on time-buttons
	private short _getTime(TextField field) {
		if (field.getText().equals(Strings.TEXT_HOLIDAY)) {
			return -1; // -1 marks holiday state
		}

		// else
		final String timeString = field.getText();
		final int colonIndex = timeString.indexOf(':');
		final int spaceIndex = timeString.indexOf(' ');
		final String period = timeString.substring(spaceIndex + 1);
		final int m = Integer
				.parseInt(timeString.substring(colonIndex + 1, spaceIndex));
		int h = Integer.parseInt(timeString.substring(0, colonIndex));
		if (period.equals("am")) {
			h %= 12;
		}
		else { // else period is pm
			h = h % 12 + 12;
		}
		return (short) (h * 60 + m);
	}

	private void _setTime(short timeInMinutes, TextField btn) {
		int h = timeInMinutes / 60;
		h %= 12;
		if (h == 0) {
			h = 12;
		}
		final int m = timeInMinutes % 60;
		String timeString = h + ":";
		if (m < 10) {
			timeString += "0" + m;
		}
		else {
			timeString += m;
		}
		if (timeInMinutes >= 720) {
			timeString += " pm";
		}
		else {
			timeString += " am";
		}
		btn.setText(timeString);
	}

	private long _getTextDateEpoch() {
		final DatePickerDialog dialog = new DatePickerDialog();
		final DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			final Date date = format.parse(_textDate.getText());
			return date.getTime();
		}
		catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		// else
		return 0;
	}
}
