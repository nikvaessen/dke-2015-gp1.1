package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aleksandra and Nik on 2015-11-10.
 */
public class MenuWindow extends JPanel {
    //All buttons
    private JButton singePlayerButton;
    private JButton multiPlayerButton;
    private JButton optionsMenuButton;
    private JButton highScoresButton;
    private JButton botButton;
    private JButton quitButton;

    public MenuWindow() {
        //set up the layout
        this.setLayout(new GridBagLayout());
        //set up the buttons and the image header
        setUpButtons();
        setUpImage();
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
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(20, 0 , 0 , 0);
        this.add(buttonHolder, c);
    }

    public void setUpImage()
    {
        try{
            BufferedImage image = ImageIO.read(new File(Config.MAIN_MENU_HEADER_IMAGE));
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0;
            this.add(imageLabel, c);
        }
        catch(IOException e)
        {
            System.out.println("Could not find image for main menu in memory");
        }
    }

}
