package com.muiz6.system.attendance.ui.panel.control.attendance;

import com.muiz6.system.attendance.ui.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AttendancePanel extends JPanel {

    private final JScrollPane _scrollPane;
    private final JPanel _contentPanel;

    public AttendancePanel() {
        this.setLayout(new BorderLayout());

        // create container that will hold list items
        _contentPanel = new JPanel();
        // new BoxLayout(_contentPanel, BoxLayout.Y_AXIS);
        _contentPanel.setLayout(new GridBagLayout());

        // create scroll pane for container
        _scrollPane = new JScrollPane(_contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        long time = new Date().getTime();
        for (int i = 0; i < 40; i++) {
            GridBagConstraints gbc = Util.buildGridBagConstraints(i, 0, 1, 0);
            gbc.insets = new Insets(25, 25, 25, 25);
            _contentPanel.add(new AttendanceItem(i,"Employee " + i, time), gbc);
        }

        this.add(_scrollPane, BorderLayout.CENTER);
    }
}
