package com.muiz6.system.attendance.dto;

public class NewEmployeeDto extends WeekdayDto {

	private String _name;
	private long _joinDate;

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public long getJoinDate() {
		return _joinDate;
	}

	public void setJoinDate(long joinDate) {
		this._joinDate = joinDate;
	}
}
