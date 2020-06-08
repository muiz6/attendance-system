package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.EmployeeItemEvent;
import com.muiz6.system.attendance.ui.Strings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeRow implements Initializable {

	private final byte _id;
	private final String _name;
	private final long _joinDate;
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

	public EmployeeRow(byte id, String name, long joinDate) {
		_id = id;
		_name = name;
		_joinDate = joinDate;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_labelId.setText(Strings.PREFIX_EMPLOYEE_ID + _id);
		_labelName.setText(Strings.PREFIX_EMPLOYEE_NAME + _name);
		_labelJoinDate.setText(Strings.PREFIX_EMPLOYEE_JOIN_DATE
				+ Util.getDate(_joinDate));
	}

	public void onBtnClick(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == _btnView) {
			_btnView.fireEvent(new EmployeeItemEvent(_id,
					EmployeeItemEvent.BUTTON_TYPE_VIEW_EMPLOYEE));
		}
		else if (source == _btnEdit) {
			_btnEdit.fireEvent(new EmployeeItemEvent(_id,
					EmployeeItemEvent.BUTTON_TYPE_EDIT_EMPLOYEE));
		}
	}
}
