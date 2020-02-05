package com.muiz6.attendancesystem.ui;

import java.awt.*;
import javax.swing.*;

public class UI {
    // private final JFrame

    public UI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMaximumSize(new Dimension(800, 600));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(200, 200, 200));

        frame.add(panel);
        frame.setVisible(true);
    }
}