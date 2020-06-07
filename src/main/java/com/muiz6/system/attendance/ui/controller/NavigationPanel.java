package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.control.TabButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationPanel implements Initializable,
		EmployeeContent.NavigationHandler {

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
	}

	@Override
	public void show(Node content) {
		_stackPane.getChildren().clear();
		_stackPane.getChildren().add(content);
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
					.getFxmlNode(Constants.RES_FXML_CONTENT_EMPLOYEES,
							c -> new EmployeeContent(this)));
		}
		else if(source == _btnHolidays) {
			_stackPane.getChildren().clear();
			_stackPane.getChildren().add(Util
					.getFxmlNode(Constants.RES_FXML_CONTENT_HOLIDAYS));
		}
	}
}
