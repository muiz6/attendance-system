package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.ui.EmployeeItemEvent;
import com.muiz6.system.attendance.ui.Strings;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import com.muiz6.system.attendance.ui.control.TimePickerDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public abstract class AddEditEmployeeBase implements Initializable {

	// fxml fields
	public TextField textName;
	public Button btnBack;
	public Button btnAdd;
	public TextField textTimeTuesday;
	public TextField textTimeMonday;
	public TextField textTimeWednesday;
	public TextField textTimeThursday;
	public TextField textTimeSunday;
	public TextField textTimeSaturday;
	public TextField textTimeFriday;
	public TextField textDate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// show today's date on join-date button by default
		final DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		final Date date = new Date();
		textDate.setText(format.format(date));
	}

	// handles back date buttons
	public void onBtnClick(ActionEvent actionEvent) {
		final Object source = actionEvent.getSource();
		if (source == btnBack) {

			// id will be ignored for back btn
			btnBack.fireEvent(new EmployeeItemEvent((byte) 0,
					EmployeeItemEvent.BUTTON_TYPE_BACK));
		}
		else if (source == btnAdd) {
			onSubmitBtnClick();
		}
	}

	public void onTextClick(MouseEvent mouseEvent) {
		Object source = mouseEvent.getSource();
		if (source == textDate) {
			final DatePickerDialog dialog = new DatePickerDialog();
			final DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			dialog.setDate(getTextDateEpoch());
			dialog.showAndWait();
			long epoch = dialog.getResult();
			final Date date = new Date();
			date.setTime(epoch);
			final String str = format.format(date);
			textDate.setText(str);
		}
	}

	public void onTextTimeClick(MouseEvent event) {
		final TextField source = (TextField) event.getSource();

		TimePickerDialog dialog = new TimePickerDialog();
		final short time = getTime(source);
		if (time == -1) { // holiday
			dialog.setHolidayCheckboxSelected(true);
			dialog.setTime((short) 0);
		}
		else {
			dialog.setTime(getTime(source));
		}
		dialog.showAndWait();
		short result = dialog.getResult();

		// update all fields if mark all checkbox is selected
		if (dialog.isMarkForALL()) {
			final TextField[] textTimeArr = {
					textTimeMonday,
					textTimeTuesday,
					textTimeWednesday,
					textTimeThursday,
					textTimeFriday,
					textTimeSaturday,
					textTimeSunday};
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
	public short getTime(TextField field) {
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

	public long getTextDateEpoch() {
		final DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			final Date date = format.parse(textDate.getText());
			return date.getTime();
		}
		catch (ParseException e) {
			System.out.println(e.getMessage());
		}

		// else
		return 0;
	}

	public abstract void onSubmitBtnClick();
}
