package gui;

import javax.swing.*;

/**
 * Created by Aleksandra on 2015-11-10.
 */
public class MenuWindow extends JFrame{

    //size of the window
    private final int HEIGHT = 400;
    private final int WIDTH = 400;

    //options
    private JButton startSingePlayer;
    private JButton startMultiPlayer;
    private JButton viewOptionsMenu;
    private JButton viewHighScores;
    private JButton startBot;
    private JButton quit;


    public MenuWindow(){


        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pentris");
        this.setResizable(false);
        this.setVisible(true);
    }
}
