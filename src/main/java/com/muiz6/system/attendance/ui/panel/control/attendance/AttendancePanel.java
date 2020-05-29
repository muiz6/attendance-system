package com.muiz6.system.attendance.ui.panel.control.attendance;

import com.muiz6.system.attendance.ui.panel.control.ListPanel;

import javax.swing.*;
import java.util.Date;

public class AttendancePanel extends ListPanel {

    @Override
    public int getItemCount() {
        return 40;
    }

    @Override
    public JComponent getItem(int i) {
        // TODO: fetch from dbase
        long time = new Date().getTime();
        return new AttendanceItem(i, "Employee " + i, time);
    }
}
