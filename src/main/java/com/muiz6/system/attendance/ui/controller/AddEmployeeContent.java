package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.dto.NewEmployeeDto;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeContent extends AddEditEmployeeBase {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// show today's date on join-date button by default
		textDate.setText(DatePickerDialog.getDate(System.currentTimeMillis()));
	}

	@Override
	public void onSubmitBtnClick() {
		final NewEmployeeDto employee =  new NewEmployeeDto();
		employee.setName(textName.getText());
		employee.setJoinDate(getTextDateEpoch());
		employee.setTimeInMonday(getTime(textTimeMonday));
		employee.setTimeInTuesday(getTime(textTimeTuesday));
		employee.setTimeInWednesday(getTime(textTimeWednesday));
		employee.setTimeInThursday(getTime(textTimeThursday));
		employee.setTimeInFriday(getTime(textTimeFriday));
		employee.setTimeInSaturday(getTime(textTimeSaturday));
		employee.setTimeInSunday(getTime(textTimeSunday));
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
