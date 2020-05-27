package com.muiz6.system.attendance.ui;

import com.muiz6.system.attendance.Constants;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridBagLayout;

public class MyFrame extends JFrame
        implements NavigatorPanel.NavigatorInterface {

    private static final String _TITLE = "Bedford Portal";
    private static final Dimension _MIN_DIMENSION = new Dimension(800, 600);
    private JPanel _mainPanel;
    private NavigatorPanel _loginPanel;

    public MyFrame() {
        this.setTitle(_TITLE);
        this.setSize(_MIN_DIMENSION);
        this.setMinimumSize(_MIN_DIMENSION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        _mainPanel = new JPanel();
        _mainPanel.setLayout(new GridBagLayout());
        this.add(_mainPanel);

        _loginPanel = new LoginPanel(this);
        _mainPanel.add(_loginPanel);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public void navigate(int id) {
        switch (id) {
            case Constants.NAVIGATOR_ID_LOGIN_SUCCESSFUL:
                _mainPanel.removeAll();
                _mainPanel.add(new ControlPanel(this));
                break;
            case Constants.NAVIGATOR_ID_LOGIN_FAILED:

                // prompt login has failed
                JOptionPane.showMessageDialog(this, "Login Failed!");
                break;
        }

        // because panel does not repaint automatically
        _mainPanel.invalidate();
        _mainPanel.repaint();
        _mainPanel.validate();
    }
}