package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.model.EmployeeItemModel;
import com.muiz6.system.attendance.ui.event.EmployeeItemEvent;
import com.muiz6.system.attendance.ui.Strings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeContent implements Initializable {

	private final ArrayList<EmployeeItemModel> _employeeList =
			Repository.getEmployees();
	private final Button _addButton = new Button(Strings.ADD_EMPLOYEE_BTN_TEXT);
	@FXML
	private ListView<Node> _listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (final EmployeeItemModel model : _employeeList) {
			Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_EMPLOYEE,
					c -> new EmployeeRow(model.getId(),
							model.getName(),
							model.getJoinDate()));
			_listView.getItems().add(node);
		}
		_addButton.setMaxWidth(Double.POSITIVE_INFINITY);
		_addButton.setOnAction(actionEvent -> {

			// id will be ignored for add btn
			_addButton.fireEvent(new EmployeeItemEvent((byte) 0,
					EmployeeItemEvent.BUTTON_TYPE_ADD_EMPLOYEE));
		});
		_listView.getItems().add(_addButton);
	}
}
