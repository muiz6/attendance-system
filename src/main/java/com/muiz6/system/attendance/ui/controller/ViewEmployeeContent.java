package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Repository;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.model.EmployeeAttendanceItemModel;
import com.muiz6.system.attendance.model.EmployeeModel;
import com.muiz6.system.attendance.ui.control.DatePickerDialog;
import com.muiz6.system.attendance.ui.control.TabButton;
import com.muiz6.system.attendance.ui.event.EmployeeItemEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewEmployeeContent implements Initializable {

	// fxml fields
	public Button btnBack;
	public HBox tabContainer;
	public TabButton btnTab1;
	public Label labelJoinDate;
	public Label labelName;
	public Label labelId;
	public ListView<Node> rowContainer;

	private final ToggleGroup _toggleGroup = new ToggleGroup();
	private final EmployeeModel _employee;

	public ViewEmployeeContent(int employeeId) {
		_employee = Repository.getEmployee(employeeId);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (_employee != null) {
			labelId.setText(Integer.toString(_employee.getId()));
			labelName.setText(_employee.getName());
			labelJoinDate.setText(DatePickerDialog
					.getDateString(_employee.getJoinDate()));

			btnTab1.setToggleGroup(_toggleGroup);
			btnTab1.setOnAction(event -> _populateRowContainer(Repository
					.getEmployeeAttendanceList(_employee.getId(), 1)));
			final int recordCount = Repository
					.getEmployeeAttendanceCount(_employee.getId());
			int tabCount = recordCount / 50;
			if (recordCount % 50 != 0) {
				tabCount++;
			}
			for (int i = 2; i <= tabCount; i++) {
				final TabButton tab = new TabButton();
				tab.setText(Integer.toString(i));
				tab.setToggleGroup(_toggleGroup);
				final int page = i;
				tab.setOnAction(event -> _populateRowContainer(Repository
						.getEmployeeAttendanceList(_employee.getId(), page)));
				tabContainer.getChildren().add(tab);
			}

			btnTab1.fireEvent(new ActionEvent());
		}
		else {
			final Alert alert = new Alert(Alert.AlertType.ERROR,
					"Employee not found!",
					ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			Platform.runLater(() -> {
				btnBack.fireEvent(new ActionEvent()); // go back to employee list
			});
		}
	}

	public void onBtnClick(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == btnBack) {
			btnBack.fireEvent(new EmployeeItemEvent(0,
					EmployeeItemEvent.BUTTON_TYPE_BACK));
		}
	}

	private void _populateRowContainer(ArrayList<EmployeeAttendanceItemModel> list) {
		rowContainer.getItems().clear();
		for (final EmployeeAttendanceItemModel i: list) {
			final Node node = Util.getFxmlNode(
					Constants.RES_FXML_ROW_EMPLOYEE_ATTENDANCE,
					c -> new EmployeeAttendanceRow(i.getDate(),
							i.getTimeIn(),
							i.getExpectedTimeIn()));
			rowContainer.getItems().add(node);
		}
	}
}
