package com.muiz6.system.attendance.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeContent implements Initializable {

	@FXML
	private ListView _listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final URL fxmlResource = ClassLoader.getSystemClassLoader()
				.getResource("layout/row_employee.fxml");

		for (int i = 0; i < 10; i++) {
			try {
				Parent node = FXMLLoader.load(fxmlResource);
				_listView.getItems().add(node);
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}
}
