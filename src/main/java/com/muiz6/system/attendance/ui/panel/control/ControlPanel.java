package com.muiz6.system.attendance.ui.panel.control;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.ui.panel.NavigatorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends NavigatorPanel implements ActionListener {

    private static final String _ID_ATTENDANCE = "attendance_panel";
    private static final String _ID_EMPLOYEES = "employee_panel";
    private static final String _ID_HOLIDAYS = "holidays_panel";
    private static final String _ID_SETTINGS = "setting_panel";
    private final JButton _btnBack;
    private final JButton _btnAttendance;
    private final JButton _btnEmployees;
    private final JButton _btnHolidays;
    private final JButton _btnSettings;
    private final JPanel _contentPanel;

    public ControlPanel(NavigatorInterface nav) {
        super(nav);

        this.setLayout(new GridBagLayout());

        // create title bar
        _btnBack = new JButton("Back");
        _btnBack.addActionListener(this);
        final JLabel title = new JLabel("Control Panel", SwingConstants.CENTER);

        // create tabs
        _btnAttendance = new JButton("Mark Attendance");
        _btnAttendance.addActionListener(this);
        _btnEmployees = new JButton("Manage Employees");
        _btnEmployees.addActionListener(this);
        _btnHolidays = new JButton("Manage Holidays");
        _btnHolidays.addActionListener(this);
        _btnSettings = new JButton("Settings");
        _btnSettings.addActionListener(this);

        // create content pane for tabs
        CardLayout contentCardLayout = new CardLayout();
        _contentPanel = new JPanel(contentCardLayout);
        _contentPanel.add(new AttendancePanel(), _ID_ATTENDANCE);
        _contentPanel.add(new EmployeePanel(), _ID_EMPLOYEES);
        _contentPanel.add(new HolidayPanel(), _ID_HOLIDAYS);
        _contentPanel.add(new SettingPanel(), _ID_SETTINGS);

        // add components to panel
        final float hwSide = 0.2f, hwMain = 0.8f;
        this.add(_btnBack, _buildGridBagConstraints(0,0, hwSide, 0));
        this.add(title, _buildGridBagConstraints(0, 1, hwMain, 0));
        this.add(_btnAttendance, _buildGridBagConstraints(1, 0, hwSide, 1));
        this.add(_btnEmployees, _buildGridBagConstraints(2, 0, hwSide, 1));
        this.add(_btnHolidays, _buildGridBagConstraints(3, 0, hwSide, 1));
        this.add(_btnSettings, _buildGridBagConstraints(4, 0, hwSide, 1));
        final GridBagConstraints gbc = _buildGridBagConstraints(1, 1, hwMain, 1);
        gbc.gridheight = 4;
        this.add(_contentPanel, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == _btnBack) {
            this.getNavigator().navigate(Constants.NAVIGATOR_ID_HOME);
        }
        else if (source == _btnAttendance) {
            final CardLayout layout = (CardLayout) _contentPanel.getLayout();
            layout.show(_contentPanel, _ID_ATTENDANCE);
        }
        else if (source == _btnEmployees) {
            final CardLayout layout = (CardLayout) _contentPanel.getLayout();
            layout.show(_contentPanel, _ID_EMPLOYEES);
        }
        else if (source == _btnHolidays) {
            final CardLayout layout = (CardLayout) _contentPanel.getLayout();
            layout.show(_contentPanel, _ID_HOLIDAYS);
        }
        else if (source == _btnSettings) {
            final CardLayout layout = (CardLayout) _contentPanel.getLayout();
            layout.show(_contentPanel, _ID_SETTINGS);
        }
    }

    private GridBagConstraints _buildGridBagConstraints(int row, int col, float weightx, float weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = row;
        gbc.gridx = col;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }
}
