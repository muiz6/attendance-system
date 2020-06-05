package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.model.EmployeeModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeContent implements Initializable {

	private ArrayList<EmployeeModel> _employeeList;

	@FXML
	private ListView<Node> _listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final URL fxmlResource = ClassLoader.getSystemClassLoader()
				.getResource("layout/row_employee.fxml");

		_employeeList = Repository.getEmployees();

		for (int i = 0; i < _employeeList.size(); i++) {
			try {
				FXMLLoader loader = new FXMLLoader();
				EmployeeModel model = _employeeList.get(i);
				loader.setLocation(fxmlResource);
				Node node = loader.load();
				EmployeeRow emp = loader.getController(); // call after loader.load()
				emp.setId(model.getId());
				emp.setName(model.getName());
				emp.setJoinDate(model.getJoinDate());
				_listView.getItems().add(node);
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		Button addButton = new Button("Add");
		addButton.setMaxWidth(Double.POSITIVE_INFINITY);
		_listView.getItems().add(addButton);
	}
}
