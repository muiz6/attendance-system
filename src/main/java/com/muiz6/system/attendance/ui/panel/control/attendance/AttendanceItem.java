package com.muiz6.system.attendance.ui.panel.control.attendance;

import com.muiz6.system.attendance.ui.Util;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class AttendanceItem extends JPanel implements ActionListener {

    private final JToggleButton _btnPresent;
    private final String _employeeName;

    public AttendanceItem(long id, String name, long timeIn) {
        this.setLayout(new GridBagLayout());

        _employeeName = name;
        JLabel employeeName = new JLabel("Name: " + name);

        JLabel employeeId = new JLabel("ID: " + id);

        // create format for time
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        final Date date = new Date();
        date.setTime(timeIn);
        JLabel employeeTimeIn = new JLabel("Time In: " + sdf.format(timeIn));

        _btnPresent = new JToggleButton("A");
        _btnPresent.addActionListener(this);

        this.add(employeeName, Util.buildGridBagConstraints(0 , 0, 1 , 0));
        this.add(employeeId, Util.buildGridBagConstraints(1, 0, 1, 0));
        this.add(employeeTimeIn, Util.buildGridBagConstraints(2, 0, 1, 0));
        GridBagConstraints btnPresentGbc =
                Util.buildGridBagConstraints(0, 1, 0, 1);
        btnPresentGbc.gridheight = 3;
        btnPresentGbc.fill = GridBagConstraints.BOTH;
        this.add(_btnPresent, btnPresentGbc);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == _btnPresent) {
            if (_btnPresent.isSelected()) {
                _btnPresent.setText("P");
                // TODO: mark present here
            }
            else {
                final String msg = "Mark Absent of " + _employeeName + "?";
                int option = JOptionPane.showConfirmDialog(this.getTopLevelAncestor(),
                        msg,
                        "Mark Absent",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == 0) { // user selects yes
                    _btnPresent.setText("A");
                }
                else {
                    _btnPresent.setSelected(true);
                }
            }
        }
    }
}
