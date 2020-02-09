package com.muiz6.attendancesystem.ui.login;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.imageio.ImageIO;

public class CompanyLogo extends JPanel {
    private BufferedImage image;

    public CompanyLogo() {
        try {
            // URL resource = SoundPlayer.class.getResource(filename);
            // Media soundMedia = new Media(resource.toExternalForm());
            image = ImageIO.read(new File(getClass().getResourceAsStream("/company_logo.jpg").toString()));
        } catch (IOException ex) {
            System.out.println("Image not Found!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}