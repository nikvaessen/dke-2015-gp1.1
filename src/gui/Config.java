package gui;

import java.awt.*;
import java.nio.file.Path;

/**
 * Created by chrx on 11/12/15.
 */
public abstract class Config
{
    //dimensions of main menu
    public static final int MAIN_MENU_WIDTH =  500;
    public static final int MAIN_MENU_HEIGHT = 500;
    public static final Dimension MAIN_MENU_DIMENSION = new Dimension(MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);
    public static final String MAIN_MENU_HEADER_IMAGE = "data/img/pentris_header_smaller.png";

    //dimensions of single player window

    public static final Dimension SINGLE_PLAYER_DIMENSION = new Dimension(1000,1000);

}
