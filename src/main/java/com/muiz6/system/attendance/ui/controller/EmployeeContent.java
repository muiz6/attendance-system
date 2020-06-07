package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.model.EmployeeModel;
import com.muiz6.system.attendance.ui.ListItemEvent;
import com.muiz6.system.attendance.ui.NavigationHandler;
import com.muiz6.system.attendance.ui.Strings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeContent implements Initializable, ListItemEvent {

	private final ArrayList<EmployeeModel> _employeeList =
			Repository.getEmployees();
	private final Button _addButton = new Button(Strings.ADD_EMPLOYEE_BTN_TEXT);
	private final NavigationHandler _nav;
	@FXML
	private ListView<Node> _listView;

	public EmployeeContent(NavigationHandler nav) {
		_nav = nav;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (final EmployeeModel model : _employeeList) {
			Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_EMPLOYEE,
					c -> new EmployeeRow(this,
							model.getId(),
							model.getName(),
							model.getJoinDate()));
			_listView.getItems().add(node);
		}
		_addButton.setMaxWidth(Double.POSITIVE_INFINITY);
		_addButton.setOnAction((actionEvent)->{

			// id will be ignored for add btn
			this.onButtonClick((byte) 0,
					ListItemEvent.BUTTON_TYPE_ADD_EMPLOYEE);
		});
		_listView.getItems().add(_addButton);
	}

	@Override
	public void onButtonClick(byte employeeId, int buttonType) {
		switch (buttonType) {
			case ListItemEvent.BUTTON_TYPE_VIEW_EMPLOYEE:
				_nav.show(Util
						.getFxmlNode(Constants.RES_FXML_CONTENT_VIEW_EMPLOYEE,
								c -> new ViewEmployeeContent(employeeId)));
				break;

			case ListItemEvent.BUTTON_TYPE_EDIT_EMPLOYEE:
				_nav.show(Util
						.getFxmlNode(Constants.RES_FXML_CONTENT_EDIT_EMPLOYEE,
								c -> new EditEmployeeContent(employeeId)));
				break;

			case ListItemEvent.BUTTON_TYPE_ADD_EMPLOYEE:
				_nav.show(Util
						.getFxmlNode(Constants.RES_FXML_CONTENT_ADD_EMPLOYEE,
								c -> new AddEmployeeContent(_nav)));
				break;
		}
	}
}
