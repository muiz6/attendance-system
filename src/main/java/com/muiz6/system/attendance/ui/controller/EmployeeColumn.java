package com.muiz6.system.attendance.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeColumn {

	private static final String _PREFIX_ID = "ID: ";
	private static final String _PREFIX_NAME = "Name: ";
	private static final String _PREFIX_JOIN_DATE = "Join Date: ";
	private static final SimpleDateFormat _DATE_FORMAT =
			new SimpleDateFormat("dd-MMM-yy");

	@FXML
	private Label _labelName;
	@FXML
	private Label _labelId;
	@FXML
	private Label _labelJoinDate;

	public void setId(byte id) {
		_labelId.setText(_PREFIX_ID + id);
	}

	public void setName(String name) {
		_labelName.setText(_PREFIX_NAME + name);
	}

	public void setJoinDate(long joinDate) {
		Date date = new Date(joinDate);
		_labelJoinDate.setText(_PREFIX_JOIN_DATE + _DATE_FORMAT.format(date));
	}
}
