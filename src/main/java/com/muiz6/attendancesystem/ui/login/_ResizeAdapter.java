package com.muiz6.attendancesystem.ui.login;

import java.awt.event.*;

import javax.swing.*;

class _ResizeAdapter extends ComponentAdapter{

    @Override
    public void componentResized(ComponentEvent e) {
        JFrame component = (JFrame) e.getSource();
        JPanel child = (JPanel) component.getComponents()[0];

        int x = (component.getWidth() - child.getWidth()) / 2;

        child.setLocation(x, 0);
        child.setSize(child.getWidth(), component.getHeight());
    }
}