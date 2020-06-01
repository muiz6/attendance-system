package com.muiz6.system.attendance.ui;

import java.awt.*;

public class UiUtil {

    public static GridBagConstraints buildGridBagConstraints(int row,
            int col, float weightx, float weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = row;
        gbc.gridx = col;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }
}
