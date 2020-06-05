package com.muiz6.system.attendance.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceRow {

	@FXML
	private GridPane _gridPane;
	@FXML
	private ToggleButton _btnAttendance;

	public void set(byte id, String name, long joinDate) {
		try {
			final URL url = ClassLoader.getSystemClassLoader()
					.getResource("layout/col_employee.fxml");
			final FXMLLoader loader = new FXMLLoader(url);
			final Node node = loader.load();
			EmployeeColumn empCol = loader.getController();
			empCol.setId(id);
			empCol.setName(name);
			empCol.setJoinDate(joinDate);

			_gridPane.add(node, 0, 0);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// public void onAttendanceBtnClick(ActionEvent actionEvent) {
	// 	// button is just pressed and selected
	// 	if (_btnAttendance.isSelected()) {
	// 		_btnAttendance.setText("P");
	// 	}
	// 	else {
	//
	// 		_btnAttendance.setText("A");
	// 	}
	// }
}
