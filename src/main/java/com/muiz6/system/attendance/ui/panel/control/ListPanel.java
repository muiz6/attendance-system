package com.muiz6.system.attendance.ui.panel.control;

import com.muiz6.system.attendance.ui.UiUtil;

import javax.swing.*;
import java.awt.*;

public abstract class ListPanel extends JPanel {

    private final JScrollPane _scrollPane;
    private final JPanel _contentPanel;

    public ListPanel() {
        this.setLayout(new BorderLayout());

        // create container that will hold list items
        _contentPanel = new JPanel();
        // new BoxLayout(_contentPanel, BoxLayout.Y_AXIS);
        _contentPanel.setLayout(new GridBagLayout());

        // create scroll pane for container
        _scrollPane = new JScrollPane(_contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        for (int i = 0; i < this.getItemCount(); i++) {
            GridBagConstraints gbc = UiUtil.buildGridBagConstraints(i, 0, 1, 0);
            gbc.insets = new Insets(25, 25, 25, 25);
            _contentPanel.add(this.getItem(i), gbc);
        }

        this.add(_scrollPane, BorderLayout.CENTER);
    }

    public abstract int getItemCount();

    public abstract JComponent getItem(int i);
}
