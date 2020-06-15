package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.model.EmployeeModel;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditEmployeeContent extends AddEditEmployeeBase {

	private final EmployeeModel _employee;

	// fxml fields
	public Button btnDisableEmployee;
	public TextField textId;

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
		EmployeeModel employee = new EmployeeModel();
		employee.setId(_employee.getId());
		employee.setName(textName.getText());
		employee.setJoinDate(_employee.getJoinDate());
		employee.setTimeInMonday(getTime(textTimeMonday));
		employee.setTimeInTuesday(getTime(textTimeTuesday));
		employee.setTimeInWednesday(getTime(textTimeWednesday));
		employee.setTimeInThursday(getTime(textTimeThursday));
		employee.setTimeInFriday(getTime(textTimeFriday));
		employee.setTimeInSaturday(getTime(textTimeSaturday));
		employee.setTimeInSunday(getTime(textTimeSunday));
		Repository.updateEmployee(employee, (success) -> {
			if (success) {
				final Alert alert = new Alert(Alert.AlertType.INFORMATION,
						"Employee Updated Successfully",
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

	public void onBtnDisableClick(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnDisableEmployee) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
					"This action cannot be undone!",
					ButtonType.YES,
					ButtonType.NO);
			alert.setHeaderText("Disable Employee?");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				btnAdd.setDisable(true);
				Repository.disableEmployee(_employee.getId());
			}
		}
	}
}
