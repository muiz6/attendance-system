package com.muiz6.system.attendance.ui.control;

import javafx.scene.control.RadioButton;

/**
 * A Toggle Button that does not deselect on click suitable for tabs
 */
public class TabButton extends RadioButton {

	public TabButton() {
		this.getStyleClass().remove("radio-button");
		this.getStyleClass().add("toggle-button");
	}
}
