package gui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;

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


    public MenuWindow() {
        //set up the panel behaviour
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new GridBagLayout());
        //set up the components
        setUpButtons();
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
        this.add(buttonHolder);
    }

}
