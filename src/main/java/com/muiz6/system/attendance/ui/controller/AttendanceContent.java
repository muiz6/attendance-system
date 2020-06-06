package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.model.EmployeeModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AttendanceContent implements Initializable {

	private ArrayList<EmployeeModel> _employeeList;

	@FXML
	private ListView<Node> _listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// final URL fxmlResource = ClassLoader.getSystemClassLoader()
		// 		.getResource(Constants.RES_FXML_ROW_ATTENDANCE);

		_employeeList = Repository.getEmployees();

		for (final EmployeeModel model : _employeeList) {
			// try {
			// 	FXMLLoader loader = new FXMLLoader(fxmlResource);
			// 	Node node = loader.load();
			// 	AttendanceRow emp = loader.getController(); // call after loader.load()
			// 	emp.set(model.getId(), model.getName(), model.getJoinDate());
			// 	_listView.getItems().add(node);
			//
			//
			// }
			// catch (IOException e) {
			// 	System.out.println(e.getMessage());
			// }

			Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_ATTENDANCE,
					c -> new AttendanceRow(model.getId(),
							model.getName(),
							model.getJoinDate()));
			_listView.getItems().add(node);
		}
	}
}
