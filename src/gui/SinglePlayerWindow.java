package gui;

import tetris.Board;
import tetris.BoardHandler;

import javax.swing.*;

/**
 * Created by baxie on 15-11-15.
 */
public class SinglePlayerWindow extends JPanel {

    public SinglePlayerWindow()
    {
        Board board = new Board(15,10);
        InputController in = new InputController();
        BoardHandler bh = new BoardHandler();

        GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        this.add(gamePanel);
    }
}
