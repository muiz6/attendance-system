package com.muiz6.system.attendance.ui.event;

import javafx.event.Event;
import javafx.event.EventType;

public class NavigationContentEvent extends Event {

	public static final EventType<NavigationContentEvent> UPDATE_EVENT =
			new EventType<>(ANY, "navigation_content_event_updste");
	public static final int TYPE_HOLIDAY_CONTENT = 2;
	private final int _type;

	public NavigationContentEvent(int contentType) {
		super(UPDATE_EVENT);
		_type = contentType;
	}

	public int getContentType() {
		return _type;
	}
}
