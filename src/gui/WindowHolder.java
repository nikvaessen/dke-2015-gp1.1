package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nik on 11/12/15.
 */
public class WindowHolder extends JFrame{

    private MenuWindow mainMenu;
    private OptionsWindow optionMenu;
    private GameWindow gameWindow;

    public WindowHolder(MenuWindow mainMenu, OptionsWindow optionMenu, GameWindow gameWindow) {
        //assign the panels
        this.mainMenu = mainMenu;
        this.optionMenu = optionMenu;
        this.gameWindow = gameWindow;

        //Initial frame state
        this.add(mainMenu);

        //set behavior of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pentris");
        this.setResizable(false);
        this.setSize(Dimensions.WINDOW_WIDTH, Dimensions.WINDOW_HEIGHT);

        //initial frame state
        this.setVisible(true);
    }
}
