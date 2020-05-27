package com.muiz6.system.attendance.ui;

import javax.swing.*;

public class ControlPanel extends NavigatorPanel {

    public ControlPanel(NavigatorInterface nav) {
        super(nav);

        JLabel label = new JLabel();
        label.setText("Container Panel");
        this.add(label);
    }
}
