package com.muiz6.system.attendance.ui;

import javafx.event.Event;
import javafx.event.EventType;

public class EmployeeItemEvent extends Event {

	public static final EventType<EmployeeItemEvent> CUSTOM =
			new EventType<>(ANY, "employee_item_event");
	public static final int BUTTON_TYPE_VIEW_EMPLOYEE = 0;
	public static final int BUTTON_TYPE_EDIT_EMPLOYEE = 1;
	public static final int BUTTON_TYPE_ADD_EMPLOYEE = 2;
	public static final int BUTTON_TYPE_BACK = 3;
	private final byte _id;
	private final int _buttonType;

	public EmployeeItemEvent(byte id, int buttonType) {
		super(EmployeeItemEvent.CUSTOM);

		_id = id;
		_buttonType = buttonType;
	}

	public byte getEmployeeId() {
		return _id;
	}

	public int getButtonType() {
		return _buttonType;
	}
}
