package com.muiz6.system.attendance.ui;

import com.muiz6.system.attendance.Constants;
import com.muiz6.system.attendance.ui.panel.LoginPanel;
import com.muiz6.system.attendance.ui.panel.NavigatorPanel;
import com.muiz6.system.attendance.ui.panel.WelcomePanel;
import com.muiz6.system.attendance.ui.panel.control.ControlPanel;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyFrame extends JFrame
        implements NavigatorPanel.NavigatorInterface {

    private static final String _TITLE = "Portal";
    private static final Dimension _MIN_DIMENSION = new Dimension(800, 600);
    private final JPanel _mainPanel;

    public MyFrame() {
        this.setTitle(_TITLE);
        this.setSize(_MIN_DIMENSION);
        this.setMinimumSize(_MIN_DIMENSION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        _mainPanel = new JPanel();
        _mainPanel.setLayout(new GridBagLayout());
        this.add(_mainPanel);

        // check if settings json file exists
        if (_checkJsonSettings()) {
            _mainPanel.add(new LoginPanel(this));
        }
        else {
            _mainPanel.add(new WelcomePanel(this));
        }
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public void navigate(int id) {
        switch (id) {
            case Constants.NAVIGATOR_ID_LOGIN_SUCCESSFUL:
                _mainPanel.removeAll();
                final GridBagConstraints constraints = new GridBagConstraints();
                constraints.weightx = 1;
                constraints.weighty = 1;
                constraints.fill = GridBagConstraints.BOTH;
                _mainPanel.add(new ControlPanel(this), constraints);
                break;
            case Constants.NAVIGATOR_ID_LOGIN_FAILED:

                // prompt login has failed
                JOptionPane.showMessageDialog(this, "Login Failed!");
                break;
            case Constants.NAVIGATOR_ID_HOME:
                _mainPanel.removeAll();
                _mainPanel.add(new LoginPanel(this));
                break;
        }

        // because panel does not repaint implicitly
        _mainPanel.invalidate();
        _mainPanel.repaint();
        _mainPanel.validate();
    }

    private boolean _checkJsonSettings() {
        // Connection conn = null;
        // try {
        //     // create a connection to the database
        //     // database is created if it does not exist
        //     conn = DriverManager.getConnection(Constants.PATH_TO_DATA_BASE);
        //     return true;
        // }
        // catch (SQLException e) {
        //     System.out.println(e.getMessage());
        // }
        // finally {
        //     try {
        //         if (conn != null) {
        //             conn.close();
        //         }
        //     }
        //     catch (SQLException ex) {
        //         System.out.println(ex.getMessage());
        //     }
        // }

        File fileSettings = new File("settings");

        // return true if file exists and it is not a directory
        return fileSettings.exists() && !fileSettings.isDirectory();
    }
}