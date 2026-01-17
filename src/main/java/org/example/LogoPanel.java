package org.example;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    private final Image logo;
    private final boolean stretchToFit; // New flag to control behavior

    // Constructor 1: Default (Keep original size, centered)
    // Use this for HUAlogo
    public LogoPanel(Image logo) {
        this(logo, false);
    }

    // Constructor 2: Custom (Allows stretching)
    // Use this for the Welcome logo
    public LogoPanel(Image logo, boolean stretchToFit) {
        this.logo = logo;
        this.stretchToFit = stretchToFit;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (logo != null) {
            Graphics2D g2 = (Graphics2D) g.create();

            // Set common transparency
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));

            // Improve image scaling quality
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            if (stretchToFit) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                //Stretch to fill the window
                g2.drawImage(logo, 0, 0, getWidth(), getHeight(), this);
            } else {
                // --- OLD BEHAVIOR: Center original size ---
                int imgW = logo.getWidth(this);
                int imgH = logo.getHeight(this);

                int x = (getWidth() - imgW) / 2;
                int y = (getHeight() - imgH) / 2;

                g2.drawImage(logo, x, y, this);
            }

            g2.dispose();
        }
    }
}