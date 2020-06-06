package com.muiz6.system.attendance.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class EmployeeRow {

	@FXML
	private GridPane _gridPane;

	public void set(byte id, String name, long joinDate) {
		try {
			final URL url = ClassLoader.getSystemClassLoader()
					.getResource("layout/col_employee.fxml");
			final FXMLLoader loader = new FXMLLoader(url);
			final Node node = loader.load();
			EmployeeColumn empCol = loader.getController();
			empCol.setId(id);
			empCol.setName(name);
			empCol.setJoinDate(joinDate);

			_gridPane.add(node, 0, 0);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}