package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.control.TabButton;
import com.muiz6.system.attendance.ui.event.NavigationContentEvent;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HolidayContent implements Initializable {

	// fxml fields
	public ListView<Node> rowContainer;
	public Button btnAdd;
	public HBox tabContainer;
	public TabButton btnTab1;

	private final ToggleGroup _toggleGroup = new ToggleGroup();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_populateRowContainer(Repository.getHolidayList(1));

		// setup first tab button separately bcz it will always exist
		btnTab1.setToggleGroup(_toggleGroup);
		btnTab1.setOnAction(event -> {
			_populateRowContainer(Repository.getHolidayList(1));
			rowContainer.refresh();
		});

		// add pagination tabs
		final int totalResults = Repository.getHolidayTotalCount();
		int tabCount = totalResults / 50;
		if (totalResults % 50 != 0) {
			tabCount++;
		}
		for (int i = 2; i <= tabCount; i++) {
			TabButton btnTab = new TabButton();
			btnTab.setText(Integer.toString(i));
			final int page = i;
			btnTab.setOnAction(event -> {
				_populateRowContainer(Repository.getHolidayList(page));
				rowContainer.refresh();
			});
			btnTab.setToggleGroup(_toggleGroup);
			tabContainer.getChildren().add(btnTab);
		}
	}

	public void onAddBtnClick(ActionEvent event) {
		if (event.getSource() == btnAdd) {
			DatePickerDialog dialog = new DatePickerDialog();
			dialog.setDate(System.currentTimeMillis());
			dialog.showAndWait();
			long selectedDate = dialog.getResult();
			if (selectedDate != DatePickerDialog.CANCELLED) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
						"This action cannot be undone!",
						ButtonType.YES,
						ButtonType.NO);
				alert.setHeaderText(MessageFormat.format("Mark {0} as holiday?",
						DatePickerDialog.getDateString(selectedDate)));
				alert.showAndWait();
				if (alert.getResult() == ButtonType.YES) {
					Repository.markHoliday(selectedDate, success -> {
						if (success) {
							rowContainer.fireEvent(new NavigationContentEvent(
									NavigationContentEvent
											.TYPE_HOLIDAY_CONTENT));
						}
					});
				}
			}
		}
	}

	private void _populateRowContainer(ArrayList<Long> list) {
		rowContainer.getItems().clear();
		for (final Long i: list) {
			final Node node = Util.getFxmlNode(Constants.RES_FXML_ROW_HOLIDAY,
					c -> new HolidayRow(i));
			rowContainer.getItems().add(node);
		}
	}
}
