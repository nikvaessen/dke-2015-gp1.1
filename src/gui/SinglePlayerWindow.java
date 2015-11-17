package gui;

import tetris.Board;
import tetris.BoardHandler;
import tetris.InputController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by baxie on 15-11-15.
 */
public class SinglePlayerWindow extends JPanel {

    public SinglePlayerWindow(MainMenu mainMenu)
    {
        Board board = new Board(20,10);
        InputController in = new InputController();
        BoardHandler bh = new BoardHandler(board, in);
        bh.spawnPiece();

        GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        this.add(gamePanel);
        this.add(new BackButton(mainMenu));

        gamePanel.repaint();
    }

    public Dimension getPreferedDimension()
    {
        return Config.SINGLE_PLAYER_SIZE;
    }
}
