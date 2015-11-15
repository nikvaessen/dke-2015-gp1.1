package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by baxie on 13-11-15.
 */
public class ImageHolderPanel extends JPanel {
    private BufferedImage image;

    public ImageHolderPanel(BufferedImage image)
    {
        this.image = image;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
