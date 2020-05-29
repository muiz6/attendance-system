package com.muiz6.system.attendance.ui.panel.control.employee;

import com.muiz6.system.attendance.ui.panel.control.ListPanel;

import javax.swing.*;
import java.util.Date;

public class EmployeePanel extends ListPanel {

    @Override
    public int getItemCount() {
        return 40;
    }

    @Override
    public JComponent getItem(int i) {
        // TODO: fethc from dbase
        long time = new Date().getTime();
        return new EmployeeItem(i, "Employee " + i, time);
    }
}
