package com.muiz6.system.attendance.ui.panel.control;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.ui.panel.NavigatorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends NavigatorPanel implements ActionListener {

    private final JButton _btnBack;
    private final JButton _btnAttendance;
    private final JButton _btnModifyEmployee;
    private final JButton _btnHolidays;
    private final JButton _btnSettings;

    public ControlPanel(NavigatorInterface nav) {
        super(nav);

        this.setLayout(new GridBagLayout());

        _btnBack = new JButton();
        _btnBack.setText("Back");
        _btnBack.addActionListener(this);
        final JLabel title = new JLabel("Control Panel", SwingConstants.CENTER);

        _btnAttendance = new JButton();
        _btnAttendance.setText("Mark Attendance");
        _btnModifyEmployee = new JButton();
        _btnModifyEmployee.setText("Modify Employee");
        _btnHolidays = new JButton();
        _btnHolidays.setText("Manage Holidays");
        _btnSettings = new JButton();
        _btnSettings.setText("Settings");

        final JLabel temp = new JLabel();
        temp.setText("CardLayout");

        // add components to panel
        final float hwSide = 0.2f, hwMain = 0.8f;
        this.add(_btnBack, _buildGridBagConstraints(0,0, hwSide, 0));
        this.add(title, _buildGridBagConstraints(0, 1, hwMain, 0));
        this.add(_btnAttendance, _buildGridBagConstraints(1, 0, hwSide, 1));
        this.add(_btnModifyEmployee, _buildGridBagConstraints(2, 0, hwSide, 1));
        this.add(_btnHolidays, _buildGridBagConstraints(3, 0, hwSide, 1));
        this.add(_btnSettings, _buildGridBagConstraints(4, 0, hwSide, 1));
        final GridBagConstraints gbc = _buildGridBagConstraints(1, 1, hwMain, 1);
        gbc.gridheight = 4;
        this.add(temp, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == _btnBack) {
            getNavigator().navigate(Constants.NAVIGATOR_ID_HOME);
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
