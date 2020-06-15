package com.muiz6.system.attendance.model;

import com.muiz6.system.attendance.dto.NewEmployeeDto;

public class EmployeeModel extends NewEmployeeDto {

	private int _id;

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		_id = id;
	}
}
