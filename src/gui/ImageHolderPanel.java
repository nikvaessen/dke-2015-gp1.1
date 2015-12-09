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

    /**
     * construct a panel holding an image
     * @param image the image the panel has to hold
     */
    public ImageHolderPanel(BufferedImage image)
    {
        this.image = image;
    }

    /**
     * returns the size the panel wants
     * @return the size the panel wants
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    /**
     * paints the image on the board
     * @param g the graphigcs object to paint on
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
