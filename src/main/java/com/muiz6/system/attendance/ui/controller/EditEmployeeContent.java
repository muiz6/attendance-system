package com.muiz6.system.attendance.ui.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class EditEmployeeContent extends AddEditEmployeeBase {

	private final int _id;

	public EditEmployeeContent(int employeeId) {
		_id = employeeId;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);


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
