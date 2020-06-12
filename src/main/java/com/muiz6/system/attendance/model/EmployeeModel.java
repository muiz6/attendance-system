package com.muiz6.system.attendance.model;

public class EmployeeModel {
	short _id;
	String _name;
	long _joinDate;
	boolean _isActive = true;

	public short getId() {
		return _id;
	}

	public void setId(short id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getJoinDate() {
		return _joinDate;
	}

	public void setJoinDate(long joinDate) {
		_joinDate = joinDate;
	}

	public void setActive(boolean state) {
		_isActive = state;
	}

	public boolean isActive() {
		return _isActive;
	}
}
