package com.muiz6.attendancesystem.ui.login;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.muiz6.attendancesystem.ui.UI;

public class LoginScreen extends JPanel{

    public LoginScreen(UI parent) {
        // layout for panel
        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(20);
        layout.setHgap(20);

        // panel = new JPanel(layout);
        this.setSize(400, 600);
        this.setLocation(200, 0);

        CompanyLogo logo = new CompanyLogo();
        JTextField input = new JTextField(25);
        JPasswordField passw = new JPasswordField(25);
        JButton btn = new JButton("Login");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input.getText().equals("abc") &&
                new String(passw.getPassword()).equals("123")) {

                    // erase passw from screen
                    passw.setText("");
                    parent.login();
                }
                else {
                    passw.setText("");
                }
            }
        });

        this.add(logo);
        this.add("Email:", input);
        this.add("Password:", passw);
        this.add(btn);

        // reposition this when parent window is resized
        parent.addComponentListener(new _ResizeAdapter());
    }
}