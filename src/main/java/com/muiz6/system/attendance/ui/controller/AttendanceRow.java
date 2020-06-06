package com.muiz6.system.attendance.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;

public class AttendanceRow {

	private int _id;
	private String _name;
	private long _joinDate;
	@FXML
	private GridPane _gridPane;
	@FXML
	private ToggleButton _btnAttendance;

	public void set(byte id, String name, long joinDate) {
		_id = id;
		_name = name;
		_joinDate = joinDate;
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

	public void onAttendanceBtnClick(ActionEvent actionEvent) {
		// button is just pressed and selected
		if (_btnAttendance.isSelected()) {
			_btnAttendance.setText("P");
		}
		else {
			String msg = MessageFormat.format("Mark Absent of {0}?",
					_name);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
					msg,
					ButtonType.NO,
					ButtonType.YES);
			alert.setTitle("Mark Absent");
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				_btnAttendance.setText("A");
			}
			else {
				// restore state
				_btnAttendance.setSelected(true);
			}
		}
	}
}
