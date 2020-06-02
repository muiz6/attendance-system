package com.muiz6.system.attendance.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationPanel implements Initializable {

	@FXML
	private StackPane _stackPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		_stackPane.getChildren().add(new Button("Dynamic Button"));
	}
}
