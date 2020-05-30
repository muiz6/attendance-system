package com.muiz6.system.attendance.ui.panel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.muiz6.system.attendance.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class WelcomePanel extends NavigatorPanel implements ActionListener {

    private final JTextField _textOrganization = new JTextField();
    private final JTextField _textAdminId = new JTextField();
    private final JPasswordField _password = new JPasswordField();
    private final JPasswordField _passwordConfirm = new JPasswordField();
    private final JButton _btnSave = new JButton("Save");

    public WelcomePanel(NavigatorInterface nav) {
        super(nav);

        this.setLayout(new GridLayout(0, 1));

        _password.setColumns(12);
        _passwordConfirm.setColumns(12);
        _btnSave.addActionListener(this);

        this.add(new JLabel("Welcome", SwingConstants.CENTER));
        this.add(new JLabel("Organization Name: "));
        this.add(_textOrganization);
        this.add(new JLabel("Admin ID: "));
        this.add(_textAdminId);
        this.add(new JLabel("Password: "));
        this.add(_password);
        this.add(new JLabel("Confirm Password: "));
        this.add(_passwordConfirm);
        this.add(_btnSave);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == _btnSave) {

            // check if form is empty
            if (_textAdminId.getText().length() < 3
                    || _textOrganization.getText().length() < 3
                    || _password.getPassword().length < 3
                    || _passwordConfirm.getPassword().length < 3) {
                JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                        "All fields must contain at least three characters",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

            // if password and confirm password do not match
            else if (!new String(_password.getPassword())
                    .equals(new String(_passwordConfirm.getPassword()))) {
                JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                        "Passwords do not match",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
            else {
                Map<String, String> settingMap = new HashMap<>();
                settingMap.put(Constants.KEY_ORGANIZATION_NAME,
                        _textOrganization.getText());
                settingMap.put(Constants.KEY_ADMIN_ID,
                        _textAdminId.getText());
                settingMap.put(Constants.KEY_PASSWORD,
                        new String(_password.getPassword()));

                // store settings as a json file
                try (Writer writer = new FileWriter("settings")) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(settingMap, writer);

                    // move to login screen
                    this.getNavigator().navigate(Constants.NAVIGATOR_ID_HOME);
                }
                catch (java.io.IOException e) {
                    JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                            "Save operation not successful!",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
}
