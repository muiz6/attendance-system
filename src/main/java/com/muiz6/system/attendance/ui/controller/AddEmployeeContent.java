package com.muiz6.system.attendance.ui.controller;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.Util;
import com.muiz6.system.attendance.ui.NavigationHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class AddEmployeeContent {

	private final NavigationHandler _nav;
	@FXML
	private Button _btnBack;
	@FXML
	private Button _btnTimeInSame;
	@FXML
	private Button _btnTimeOutSame;
	@FXML
	private Button _btnTimeInMonday;
	@FXML
	private Button _btnTimeOutMonday;
	@FXML
	private Button _btnTimeInTuesday;
	@FXML
	private Button _btnTimeOutTuesday;
	@FXML
	private Button _btnTimeInWednesday;
	@FXML
	private Button _btnTimeOutWednesday;
	@FXML
	private Button _btnTimeInThursday;
	@FXML
	private Button _btnTimeOutThursday;
	@FXML
	private Button _btnTimeInFriday;
	@FXML
	private Button _btnTimeOutFriday;
	@FXML
	private Button _btnTimeInSaturday;
	@FXML
	private Button _btnTimeOutSaturday;
	@FXML
	private Button _btnTimeInSunday;
	@FXML
	private Button _btnTimeOutSunday;
	@FXML
	private RadioButton _btnRadioDifferent;
	@FXML
	private RadioButton _btnRadioSame;

	public AddEmployeeContent(NavigationHandler nav) {
		_nav = nav;
	}

	public void onOptionSelect(ActionEvent event) {
		Button[] arr = {
				_btnTimeInMonday,
				_btnTimeOutMonday,
				_btnTimeInTuesday,
				_btnTimeOutTuesday,
				_btnTimeInWednesday,
				_btnTimeOutWednesday,
				_btnTimeInThursday,
				_btnTimeOutThursday,
				_btnTimeInFriday,
				_btnTimeOutFriday,
				_btnTimeInSaturday,
				_btnTimeOutSaturday,
				_btnTimeInSunday,
				_btnTimeOutSunday};
		Object source = event.getSource();
		if (source == _btnRadioSame && _btnRadioSame.isSelected()) {
			_btnTimeInSame.setDisable(false);
			_btnTimeOutSame.setDisable(false);
			for (final Button i: arr) {
				i.setDisable(true);
			}
		}
		else if (source == _btnRadioDifferent
				&& _btnRadioDifferent.isSelected()) {
			_btnTimeInSame.setDisable(true);
			_btnTimeOutSame.setDisable(true);
			for (final Button i: arr) {
				i.setDisable(false);
			}
		}
	}

	public void onBtnClick(ActionEvent actionEvent) {
		Object source = actionEvent.getSource();
		if (source == _btnBack) {
			_nav.show(Util.getFxmlNode(Constants.RES_FXML_CONTENT_EMPLOYEES,
					c -> new EmployeeContent(_nav)));
		}
	}
}
