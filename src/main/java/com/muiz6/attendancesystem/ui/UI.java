package com.muiz6.attendancesystem.ui;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class UI {
    private String title = "Bedford International School and Academy Portal";
    private JFrame frame;
    private JPanel panel;

    public UI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(this.title);
        frame.setSize(800, 600);
        frame.setMaximumSize(new Dimension(800, 600));
        frame.setLayout(null);
        frame.setBackground(new Color(167, 219, 198));

        // layout for panel
        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(20);
        layout.setHgap(20);

        panel = new JPanel(layout);
        panel.setSize(400, 600);
        panel.setLocation(200, 0);

        JTextField input = new JTextField(25);
        input.setMaximumSize(new Dimension(300, 50));
        input.setAlignmentX(Component.CENTER_ALIGNMENT);
        input.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPasswordField passw = new JPasswordField(25);
        passw.setMaximumSize(new Dimension(300, 50));
        passw.setAlignmentX(Component.CENTER_ALIGNMENT);
        passw.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton btn = new JButton("Login");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add("Email:", input);
        panel.add("Password:", passw);
        panel.add(btn);

        frame.add(panel);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                int x = (component.getWidth() - panel.getWidth()) / 2;
                panel.setLocation(x, 0);
                panel.setSize(panel.getWidth(), component.getHeight());
            }
        });
        frame.setVisible(true);
    }
}