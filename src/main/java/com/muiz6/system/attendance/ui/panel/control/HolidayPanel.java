package com.muiz6.system.attendance.ui.panel.control;

import javax.swing.*;
import java.awt.*;

public class HolidayPanel extends JPanel {;

    private static final String[] _DAYS = {
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
    };
    private final JToggleButton[] _btnDays;

    public HolidayPanel() {
        this.setLayout(new GridLayout(1, 1));

        // create scrollable date panel
        JPanel datePanel =  new JPanel(new GridLayout(0, 1));
        datePanel.add(new JLabel("Date", SwingConstants.CENTER));
        datePanel.add(new JButton("Add"));
        JScrollPane scrollPane =  new JScrollPane(datePanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        for (byte i = 80; i >= 0; i--) {
            datePanel.add(new JLabel("Item "+ i, SwingConstants.CENTER));
        }

        // create week days panel
        JPanel dayPanel = new JPanel(new GridLayout(0, 1));
        dayPanel.add(new JLabel("Days of Week", SwingConstants.CENTER));
        _btnDays = new JToggleButton[_DAYS.length];
        for (byte i = 0; i < _DAYS.length; i++) {
            _btnDays[i] = new JToggleButton();
            _btnDays[i].setText(_DAYS[i]);
            dayPanel.add(_btnDays[i]);
        }

        this.add(scrollPane);
        this.add(dayPanel);
    }
}
