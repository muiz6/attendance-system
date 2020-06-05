package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.control.TabButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javax.swing.text.html.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavigationPanel implements Initializable {

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

		_stackPane.getChildren().add(Util
				.getFxmlNode("layout/content_attendance.fxml"));
	}

	public void onTabClick(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == _btnAttendance) {
			_stackPane.getChildren().removeAll();
			_stackPane.getChildren().add(Util
					.getFxmlNode("layout/content_attendance.fxml"));
		}
		else if (source == _btnEmployees) {
			_stackPane.getChildren().removeAll();
			_stackPane.getChildren().add(Util
					.getFxmlNode("layout/content_employees.fxml"));
		}
		else if(source == _btnHolidays) {
			_stackPane.getChildren().removeAll();
			_stackPane.getChildren().add(Util
					.getFxmlNode("layout/content_holidays.fxml"));
		}
	}
}
