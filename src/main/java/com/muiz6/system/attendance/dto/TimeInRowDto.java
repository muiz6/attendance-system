package com.muiz6.system.attendance.dto;

public class TimeInRowDto extends WeekdayDto {

	private int _id;
	private long _date;

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public long getDate() {
		return _date;
	}

	public void setDate(long date) {
		this._date = date;
	}
}
