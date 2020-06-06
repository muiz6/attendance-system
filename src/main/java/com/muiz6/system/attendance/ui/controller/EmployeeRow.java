package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.ListItemEvent;
import com.muiz6.system.attendance.ui.Strings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * must call EmployeeRow.set() after loading fxml or else there'll be NPE
 */
public class EmployeeRow implements Initializable {

	private final byte _id;
	private final String _name;
	private final long _joinDate;
	private final ListItemEvent _listItemEvent;
	@FXML
	private Label _labelName;
	@FXML
	private Label _labelId;
	@FXML
	private Label _labelJoinDate;
	@FXML
	private Button _btnView;
	@FXML
	private Button _btnEdit;

	public EmployeeRow(ListItemEvent event, byte id, String name, long joinDate) {
		_id = id;
		_name = name;
		_joinDate = joinDate;
		_listItemEvent = event;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_labelId.setText(Strings.PREFIX_EMPLOYEE_ID + _id);
		_labelName.setText(Strings.PREFIX_EMPLOYEE_NAME + _name);
		_labelJoinDate.setText(Strings.PREFIX_EMPLOYEE_JOIN_DATE
				+ Util.getDate(_joinDate));
	}

	// public void set(ListItemEvent event, byte id, String name, long joinDate) {
	// 	_employeeId = id;
	// 	_listItemEvent = event;
	// 	try {
	// 		final URL url = ClassLoader.getSystemClassLoader()
	// 				.getResource(Constants.RES_FXML_COL_EMPLOYEE);
	// 		final FXMLLoader loader = new FXMLLoader(url);
	// 		final Node node = loader.load();
	// 		EmployeeColumn empCol = loader.getController();
	// 		empCol.setId(id);
	// 		empCol.setName(name);
	// 		empCol.setJoinDate(joinDate);
	//
	// 		_gridPane.add(node, 0, 0);
	// 	}
	// 	catch (IOException e) {
	// 		System.out.println(e.getMessage());
	// 	}
	// }

	public void onBtnClick(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == _btnView) {
			_listItemEvent.onButtonClick(_id,
					ListItemEvent.BUTTON_TYPE_VIEW_EMPLOYEE);
		}
		else if (source == _btnEdit) {
			_listItemEvent.onButtonClick(_id,
					ListItemEvent.BUTTON_TYPE_EDIT_EMPLOYEE
			);
		}
	}
}
