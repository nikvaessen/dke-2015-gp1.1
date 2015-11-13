package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Aleksandra on 2015-11-10.
 */
public class MenuWindow extends JPanel {

    //size of the window
    private final int HEIGHT = 400;
    private final int WIDTH = 400;

    //options
    private JButton singePlayerButton;
    private JButton multiPlayerButton;
    private JButton optionsMenuButton;
    private JButton highScoresButton;
    private JButton botButton;
    private JButton quitButton;
    private JViewport imageheader;

    public MenuWindow() {
        //set up the panel behaviour
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new GridBagLayout());
        //set up the buttons and the image header
        setUpButtons();
        setUpImage();

        //set visable
        this.setVisible(true);
    }

    public void setUpButtons()
    {
        //set up the the buttons
        singePlayerButton = new JButton("Single Player");
        multiPlayerButton = new JButton("Multiplayer");
        optionsMenuButton = new JButton("Options");
        highScoresButton  = new JButton("View Highscore");
        botButton         = new JButton("Watch bot");
        quitButton        = new JButton("Quit");

        //add the buttons to a panel and add the panel to the frame.
        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new GridLayout(6, 0, 0, 20));
        buttonHolder.add(singePlayerButton);
        buttonHolder.add(multiPlayerButton);
        buttonHolder.add(highScoresButton);
        buttonHolder.add(botButton);
        buttonHolder.add(optionsMenuButton);
        buttonHolder.add(quitButton);

        //set up constraints for the buttonpanel and add it to the panel
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weighty = 0.5;
        this.add(buttonHolder);
    }

    public void setUpImage()
    {
        class ImagePanel extends JPanel
        {
            private BufferedImage image;

            public ImagePanel()
            {
                try{
                    image = ImageIO.read(new File(Config.MAIN_MENU_HEADER_IMAGE));
                }
                catch(Exception e)
                {
                    System.out.println("Did not find header image for main window");
                }
            }
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(image, 0 , 0 , null);
            }
        }
        JPanel imagePanel = new ImagePanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        this.add(imagePanel, c);
    }

}
