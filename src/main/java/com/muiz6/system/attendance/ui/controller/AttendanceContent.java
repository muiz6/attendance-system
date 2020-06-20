package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.model.AttendanceItemModel;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceContent implements Initializable {

	private final ArrayList<AttendanceItemModel> _employeeList =
			Repository.getAttendanceList();
	@FXML
	private Label _labelToday;
	@FXML
	private ListView<Node> _listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_labelToday.setText(DatePickerDialog.getDateString(System.currentTimeMillis()));

		for (final AttendanceItemModel model : _employeeList) {
			Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_ATTENDANCE,
					c -> new AttendanceRow(model.getId(),
							model.getName(),
							model.getJoinDate(),
							model.getTimeIn()));
			_listView.getItems().add(node);
		}
	}
}
