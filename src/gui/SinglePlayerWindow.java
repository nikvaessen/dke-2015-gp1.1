package gui;

import tetris.Board;
import tetris.BoardHandler;
import tetris.InputController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by baxie on 15-11-15.
 */
public class SinglePlayerWindow extends JPanel {

    Thread movingPieceDown;
    Thread movingPieceLeftAndRight;

    public SinglePlayerWindow(MainMenu mainMenu, InputController in) {
        Board board = new Board(15, 10);
        final InputController input = in;
        final BoardHandler bh = new BoardHandler(board, in);

        final GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        this.add(gamePanel);
        this.add(new BackButton(mainMenu));
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bh.spawnPiece();
                movingPieceDown.start();
                movingPieceLeftAndRight.start();
            }
        });
        this.add(startButton);

        movingPieceLeftAndRight = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    char direction = input.getCurrentInput();
                    //System.out.println("Direction pressed: " + direction);
                    if (direction != ' ') {
                        bh.movePentominoTo(direction);
                        gamePanel.repaint();
                    }

                }
            }
        });

        movingPieceDown = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        bh.movePentominoTo('d');
                        gamePanel.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public Dimension getPreferedDimension()
    {
        return Config.SINGLE_PLAYER_SIZE;
    }
}
