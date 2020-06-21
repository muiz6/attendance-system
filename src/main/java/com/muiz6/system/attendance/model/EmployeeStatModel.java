package com.muiz6.system.attendance.model;

public class EmployeeStatModel {

	private byte _percOnTime;
	private byte _percLate;
	private byte _percAbsent;

	public byte getPercentageAbsent() {
		return _percAbsent;
	}

	public void setPercentageAbsent(byte percAbsent) {
		this._percAbsent = percAbsent;
	}

	public byte getPercentageOnTime() {
		return _percOnTime;
	}

	public void setPercentageOnTime(byte _percOnTime) {
		this._percOnTime = _percOnTime;
	}

	public byte getPercentageLate() {
		return _percLate;
	}

	public void setPercentageLate(byte _percLate) {
		this._percLate = _percLate;
	}
}
