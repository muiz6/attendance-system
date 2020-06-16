package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HolidayContent implements Initializable {

	// fxml fields
	public VBox rowContainer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Long> holidayList = Repository.getHolidayList();
		for (final Long i: holidayList) {
			final Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_HOLIDAY,
					c -> new HolidayRow(i));
			rowContainer.getChildren().add(node);
		}
	}
}
