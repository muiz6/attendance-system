package com.muiz6.system.attendance.ui;

public interface ListItemEvent {

	int BUTTON_TYPE_VIEW_EMPLOYEE = 0;
	int BUTTON_TYPE_EDIT_EMPLOYEE = 1;
	int BUTTON_TYPE_ADD_EMPLOYEE = 2;

	/**
	 *
	 * @param employeeId id of employee
	 * @param buttonType type of Button within ListItemEvent interface
	 */
	void onButtonClick(byte employeeId, int buttonType);
}
