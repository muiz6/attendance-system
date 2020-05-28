package com.muiz6.system.attendance.ui.panel;

import com.muiz6.system.attendance.Constants;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends NavigatorPanel implements ActionListener {

    private static final Dimension _MIN_DIMENSION = new Dimension(480, 480);
    private static final String _LOGIN_BTN_TEXT = "Login";
    private final JTextField _textId;
    private final JPasswordField _textPassword;
    private final JButton _btnLogin;

    public LoginPanel(NavigatorInterface nav) {
        super(nav);

        this.setSize(_MIN_DIMENSION);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //

        final JLabel labelId = new JLabel();
        labelId.setText("Enter ID: ");
        this.add(labelId);
        _textId = new JTextField();
        this.add(_textId);

        final JLabel labelPassword = new JLabel();
        labelPassword.setText("Enter Password: ");
        this.add(labelPassword);
        _textPassword = new JPasswordField();
        this.add(_textPassword);

        _btnLogin = new JButton();
        _btnLogin.setText(_LOGIN_BTN_TEXT);
        _btnLogin.addActionListener(this);
        this.add(_btnLogin);
    }

    // handle login btn click event
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final String enteredPassword = new String(_textPassword.getPassword());

        // TODO: use firebase authentication
        if (_textId.getText().equals("adnan")
                && enteredPassword.equals("12345")) {
            this.getNavigator().navigate(Constants.NAVIGATOR_ID_LOGIN_SUCCESSFUL);
        }
        else {
            this.getNavigator() .navigate(Constants.NAVIGATOR_ID_LOGIN_FAILED);
        }
    }
}