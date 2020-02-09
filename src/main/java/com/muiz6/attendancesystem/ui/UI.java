package com.muiz6.attendancesystem.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.muiz6.attendancesystem.ui.login.LoginScreen;

public class UI extends JFrame {

    // default serial version id
    private static final long serialVersionUID = 1L;

    private String title = "Bedford International School and Academy Portal";

    public UI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(this.title);
        this.setSize(800, 600);
        this.setMaximumSize(new Dimension(800, 600));
        this.setLayout(null);
        this.setBackground(new Color(167, 219, 198));

        JPanel panel = new LoginScreen(this);

        this.add(panel);

        this.setVisible(true);
    }

    public void login() {
        this.removeAll();

        // TODO: replace menu screen
        this.add(new JPanel());
    }
}