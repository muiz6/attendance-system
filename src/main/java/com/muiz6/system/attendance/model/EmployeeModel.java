package com.muiz6.system.attendance.model;

public class EmployeeModel {
	byte _id;
	String _name;
	long _joinDate;

	public byte getId() {
		return _id;
	}

	public void setId(byte id) {
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
}
