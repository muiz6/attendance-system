package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HolidayContent implements Initializable {

	// fxml fields
	public VBox rowContainer;
	public Button btnAdd;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Long> holidayList = Repository.getHolidayList();
		for (final Long i: holidayList) {
			final Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_HOLIDAY,
					c -> new HolidayRow(i));
			rowContainer.getChildren().add(node);
		}
	}

	public void onAddBtnClick(ActionEvent actionEvent) {
		DatePickerDialog dialog = new DatePickerDialog();
		dialog.setDate(System.currentTimeMillis());
		dialog.showAndWait();
		long selectedDate = dialog.getResult();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
				"This action cannot be undone!",
				ButtonType.YES,
				ButtonType.NO);
		alert.setHeaderText(MessageFormat.format("Mark {0} as holiday?",
				DatePickerDialog.getDate(selectedDate)));
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			Repository.markHoliday(selectedDate);
		}
	}
}
