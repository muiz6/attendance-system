package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.EmployeeItemEvent;
import com.muiz6.system.attendance.ui.control.TabButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationPanel implements Initializable,
		EventHandler<EmployeeItemEvent> {

	@FXML
	private TabButton _btnAttendance;
	@FXML
	private TabButton _btnEmployees;
	@FXML
	private TabButton _btnHolidays;
	@FXML
	private StackPane _stackPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// start by content of first tab (attendance tab)
		_stackPane.getChildren().add(Util
				.getFxmlNode(Constants.RES_FXML_CONTENT_ATTENDANCE));
		_stackPane.addEventFilter(EmployeeItemEvent.CUSTOM, this);
	}

	public void onTabClick(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == _btnAttendance) {
			_stackPane.getChildren().clear();
			_stackPane.getChildren().add(Util
					.getFxmlNode(Constants.RES_FXML_CONTENT_ATTENDANCE));
		}
		else if (source == _btnEmployees) {
			_stackPane.getChildren().clear();
			_stackPane.getChildren().add(Util
					.getFxmlNode(Constants.RES_FXML_CONTENT_EMPLOYEES));
		}
		else if(source == _btnHolidays) {
			_stackPane.getChildren().clear();
			_stackPane.getChildren().add(Util
					.getFxmlNode(Constants.RES_FXML_CONTENT_HOLIDAYS));
		}
	}

	@Override
	public void handle(EmployeeItemEvent event) {
		final int employeeId = event.getEmployeeId();
		switch (event.getButtonType()) {
			case EmployeeItemEvent.BUTTON_TYPE_ADD_EMPLOYEE:
				_stackPane.getChildren().clear();
				_stackPane.getChildren().add(Util.getFxmlNode(Constants
						.RES_FXML_CONTENT_ADD_EMPLOYEE));
				break;

			case EmployeeItemEvent.BUTTON_TYPE_VIEW_EMPLOYEE:
				_stackPane.getChildren().clear();
				_stackPane.getChildren().add(Util.getFxmlNode(Constants
						.RES_FXML_CONTENT_VIEW_EMPLOYEE,
						c -> new ViewEmployeeContent(employeeId)));
				break;

			case EmployeeItemEvent.BUTTON_TYPE_EDIT_EMPLOYEE:
				_stackPane.getChildren().clear();
				_stackPane.getChildren().add(Util.getFxmlNode(Constants
						.RES_FXML_CONTENT_EDIT_EMPLOYEE,
						c -> new EditEmployeeContent(employeeId)));
				break;

			case EmployeeItemEvent.BUTTON_TYPE_BACK:
				_stackPane.getChildren().clear();
				_stackPane.getChildren().add(Util.getFxmlNode(Constants
						.RES_FXML_CONTENT_EMPLOYEES));
				break;
		}
	}
}
