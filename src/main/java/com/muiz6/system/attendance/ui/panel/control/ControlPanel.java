package com.muiz6.system.attendance.ui.panel.control;

import com.muiz6.system.attendance.ui.UiUtil;
import com.muiz6.system.attendance.ui.panel.control.attendance.AttendancePanel;
import com.muiz6.system.attendance.ui.panel.control.employee.EmployeePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class ControlPanel extends JPanel implements ActionListener {

    private static final String _STRING_TAB_ATTENDANCE = "Mark Attendance";
    private static final String _STRING_TAB_EMPLOYEES = "Manage Employees";
    private static final String _STRING_TAB_HOLIDAYS = "Manage Holidays";
    private static final String _ID_ATTENDANCE = "attendance_panel";
    private static final String _ID_EMPLOYEES = "employee_panel";
    private static final String _ID_HOLIDAYS = "holidays_panel";
    private final JToggleButton _btnAttendance;
    private final JToggleButton _btnEmployees;
    private final JToggleButton _btnHolidays;
    private final JPanel _contentPanel;

    public ControlPanel() {
        this.setLayout(new GridBagLayout());

        // create tabs
        _btnAttendance = new JToggleButton(_STRING_TAB_ATTENDANCE);
        _btnAttendance.setSelected(true);
        _btnAttendance.addActionListener(this);
        _btnEmployees = new JToggleButton(_STRING_TAB_EMPLOYEES);
        _btnEmployees.addActionListener(this);
        _btnHolidays = new JToggleButton(_STRING_TAB_HOLIDAYS);
        _btnHolidays.addActionListener(this);

        // create content pane for tabs
        CardLayout contentCardLayout = new CardLayout();
        _contentPanel = new JPanel(contentCardLayout);
        _contentPanel.add(new AttendancePanel(), _ID_ATTENDANCE);
        _contentPanel.add(new EmployeePanel(), _ID_EMPLOYEES);
        _contentPanel.add(new HolidayPanel(), _ID_HOLIDAYS);

        // add components to panel
        final float hwSide = 0.2f, hwMain = 0.8f;
        this.add(_btnAttendance, UiUtil.buildGridBagConstraints(1, 0, hwSide, 1));
        this.add(_btnEmployees, UiUtil.buildGridBagConstraints(2, 0, hwSide, 1));
        this.add(_btnHolidays, UiUtil.buildGridBagConstraints(3, 0, hwSide, 1));
        final GridBagConstraints gbc = UiUtil.buildGridBagConstraints(1, 1, hwMain, 1);
        gbc.gridheight = 3;
        this.add(_contentPanel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == _btnAttendance) {
            _btnAttendance.setSelected(true);
            _btnEmployees.setSelected(false);
            _btnHolidays.setSelected(false);

            final CardLayout layout = (CardLayout) _contentPanel.getLayout();
            layout.show(_contentPanel, _ID_ATTENDANCE);
        }
        else if (source == _btnEmployees) {
            _btnAttendance.setSelected(false);
            _btnEmployees.setSelected(true);
            _btnHolidays.setSelected(false);

            final CardLayout layout = (CardLayout) _contentPanel.getLayout();
            layout.show(_contentPanel, _ID_EMPLOYEES);
        }
        else if (source == _btnHolidays) {
            _btnAttendance.setSelected(false);
            _btnEmployees.setSelected(false);
            _btnHolidays.setSelected(true);

            final CardLayout layout = (CardLayout) _contentPanel.getLayout();
            layout.show(_contentPanel, _ID_HOLIDAYS);
        }
    }
}
