package com.muiz6.system.attendance.dto;

public abstract class WeekdayDto {

	private short _timeMonday;
	private short _timeTuesday;
	private short _timeWednesday;
	private short _timeThursday;
	private short _timeFriday;
	private short _timeSaturday;
	private short _timeSunday;

	public short getTimeInMonday() {
		return _timeMonday;
	}

	public void setTimeInMonday(short timeMonday) {
		this._timeMonday = timeMonday;
	}

	public short getTimeInTuesday() {
		return _timeTuesday;
	}

	public void setTimeInTuesday(short timeTuesday) {
		this._timeTuesday = timeTuesday;
	}

	public short getTimeInWednesday() {
		return _timeWednesday;
	}

	public void setTimeInWednesday(short timeWednesday) {
		this._timeWednesday = timeWednesday;
	}

	public short getTimeInThursday() {
		return _timeThursday;
	}

	public void setTimeInThursday(short timeThursday) {
		this._timeThursday = timeThursday;
	}

	public short getTimeInFriday() {
		return _timeFriday;
	}

	public void setTimeInFriday(short timeFriday) {
		this._timeFriday = timeFriday;
	}

	public short getTimeInSaturday() {
		return _timeSaturday;
	}

	public void setTimeInSaturday(short timeSaturday) {
		this._timeSaturday = timeSaturday;
	}

	public short getTimeInSunday() {
		return _timeSunday;
	}

	public void setTimeInSunday(short timeSunday) {
		this._timeSunday = timeSunday;
	}
}
