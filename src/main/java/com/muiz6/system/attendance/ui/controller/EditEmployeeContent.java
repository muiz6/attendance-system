package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.model.EmployeeModel;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditEmployeeContent extends AddEditEmployeeBase {

	public TextField textId; // fxml field
	private final EmployeeModel _employee;

	public EditEmployeeContent(int employeeId) {
		_employee = Repository.getEmployee(employeeId);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// employee maybe null do to some problem
		if (_employee == null) {
			final Alert alert = new Alert(Alert.AlertType.ERROR,
					"Employee not found!",
					ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			Platform.runLater(() -> {
				btnBack.fireEvent(new ActionEvent()); // go back to employee list
			});
		}
		else {
			textId.setText(Integer.toString(_employee.getId()));
			textName.setText(_employee.getName());
			textDate.setText(DatePickerDialog.getDate(_employee.getJoinDate()));
			setTime(_employee.getTimeInMonday(), textTimeMonday);
			setTime(_employee.getTimeInTuesday(), textTimeTuesday);
			setTime(_employee.getTimeInWednesday(), textTimeWednesday);
			setTime(_employee.getTimeInThursday(), textTimeThursday);
			setTime(_employee.getTimeInFriday(), textTimeFriday);
			setTime(_employee.getTimeInSaturday(), textTimeSaturday);
			setTime(_employee.getTimeInSunday(), textTimeSunday);
		}
	}

	@Override
	public void onSubmitBtnClick() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION,
				"Button Clicked!",
				ButtonType.OK);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
}
