package com.muiz6.system.attendance.ui.panel.control.employee;

import com.muiz6.system.attendance.ui.UiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeItem extends JPanel implements ActionListener {

    private static final String _SUFFIX_NAME = "Name: ";
    private static final String _SUFFIX_ID = "ID: ";
    private static final String _SUFFIX_JOIN_DATE = "Join Date: ";
    private static final String _BTN_VIEW_TEXT = "View";
    private static final String _BTN_EDIT_TEXT = "Edit";
    private final JButton _btnView;
    private final JButton _btnEdit;

    public EmployeeItem(long id, String name, long joinTimeStamp) {
        this.setLayout(new GridBagLayout());

        // create display content
        final JLabel labelName = new JLabel(_SUFFIX_NAME + name);
        final JLabel labelId = new JLabel(_SUFFIX_ID + id);
        final JLabel labelJoinDate = new JLabel(_SUFFIX_JOIN_DATE + joinTimeStamp);

        // add view and edit buttons
        _btnView = new JButton(_BTN_VIEW_TEXT);
        _btnView.addActionListener(this);
        _btnEdit = new JButton(_BTN_EDIT_TEXT);
        _btnEdit.addActionListener(this);

        // add components
        this.add(labelName, UiUtil.buildGridBagConstraints(0, 0, 1, 0));
        this.add(labelId, UiUtil.buildGridBagConstraints(1, 0, 1, 0));
        this.add(labelJoinDate, UiUtil.buildGridBagConstraints(2, 0, 1, 0));
        final GridBagConstraints gbcBtnView = UiUtil.buildGridBagConstraints(0, 1, 0, 1);
        gbcBtnView.gridheight = 3;
        gbcBtnView.fill = GridBagConstraints.BOTH;
        this.add(_btnView, gbcBtnView);
        final GridBagConstraints gbcBtnEdit = UiUtil.buildGridBagConstraints(0, 2, 0, 1);
        gbcBtnEdit.gridheight = 3;
        gbcBtnEdit.fill = GridBagConstraints.BOTH;
        this.add(_btnEdit, gbcBtnEdit);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
