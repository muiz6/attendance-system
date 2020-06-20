package com.muiz6.system.attendance.model;

public class EmployeeAttendanceItemModel {
	private short _timeIn;
	private short _expectedTimeIn;
	private long _date;

	public short getTimeIn() {
		return _timeIn;
	}

	public void setTimeIn(short timeIn) {
		this._timeIn = timeIn;
	}

	public short getExpectedTimeIn() {
		return _expectedTimeIn;
	}

	public void setExpectedTimeIn(short expectedTimeIn) {
		this._expectedTimeIn = expectedTimeIn;
	}

	public long getDate() {
		return _date;
	}

	public void setDate(long timestamp) {
		this._date = timestamp;
	}
}
