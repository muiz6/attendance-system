package com.muiz6.system.attendance.ui;

import com.muiz6.system.attendance.ui.panel.control.ControlPanel;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

	private static final String _TITLE = "Bedford Portal";
	private static final Dimension _MIN_DIMENSION = new Dimension(800, 600);

	public MyFrame() {
		this.setTitle(_TITLE);
		this.setMinimumSize(_MIN_DIMENSION);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.add(new ControlPanel());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}