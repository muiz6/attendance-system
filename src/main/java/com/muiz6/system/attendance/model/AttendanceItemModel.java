package com.muiz6.system.attendance.model;

public class AttendanceItemModel extends EmployeeItemModel {

	private short _timeIn;

	public void setTimeIn(short timeIn) {
		_timeIn = timeIn;
	}

	public short getTimeIn() {
		return _timeIn;
	}
}
