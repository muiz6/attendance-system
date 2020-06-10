package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.model.EmployeeModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceContent implements Initializable {

	private final ArrayList<EmployeeModel> _employeeList =
			Repository.getEmployees();

	@FXML
	private ListView<Node> _listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (final EmployeeModel model : _employeeList) {
			Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_ATTENDANCE,
					c -> new AttendanceRow(model.getId(),
							model.getName(),
							model.getJoinDate()));
			_listView.getItems().add(node);
		}
	}
}
