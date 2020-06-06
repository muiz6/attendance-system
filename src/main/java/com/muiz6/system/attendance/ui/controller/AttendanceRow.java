package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.Strings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class AttendanceRow implements Initializable {

	private final byte _id;
	private final String _name;
	private final long _joinDate;
	@FXML
	private Label _labelName;
	@FXML
	private Label _labelId;
	@FXML
	private Label _labelJoinDate;
	@FXML
	private ToggleButton _btnAttendance;

	AttendanceRow(byte id, String name, long joinDate) {
		_id = id;
		_name = name;
		_joinDate = joinDate;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_labelId.setText(Strings.PREFIX_EMPLOYEE_ID + _id);
		_labelName.setText(Strings.PREFIX_EMPLOYEE_NAME + _name);
		_labelJoinDate.setText(Strings.PREFIX_EMPLOYEE_JOIN_DATE
				+ Util.getDate(_joinDate));
	}

	// public void set(byte id, String name, long joinDate) {
	// 	_id = id;
	// 	_name = name;
	// 	_joinDate = joinDate;
	// 	try {
	// 		final URL url = ClassLoader.getSystemClassLoader()
	// 				.getResource(Constants.RES_FXML_COL_EMPLOYEE);
	// 		final FXMLLoader loader = new FXMLLoader(url);
	// 		final Node node = loader.load();
	// 		// EmployeeColumn empCol = loader.getController();
	// 		// empCol.setId(id);
	// 		// empCol.setName(name);
	// 		// empCol.setJoinDate(joinDate);
	//
	// 		_gridPane.add(node, 0, 0);
	// 	}
	// 	catch (IOException e) {
	// 		System.out.println(e.getMessage());
	// 	}
	// }

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
