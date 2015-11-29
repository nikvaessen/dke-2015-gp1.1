package gui;

import tetris.Board;
import tetris.BoardHandler;
import tetris.GameLoop;
import tetris.InputController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by baxie on 15-11-15.
 */
public class SinglePlayerWindow extends JPanel {

    private Thread gameLoop;
    private boolean gameLoopHasStarted;

    private InputController inputController;

    public SinglePlayerWindow(MainMenu mainMenu) {
        //create the variables
        Board board = new Board(10, 20);
        InputController inputController = new InputController();
        this.addKeyListener(inputController);
        this.setFocusable(true);
        this.requestFocusInWindow();
        BoardHandler bh = new BoardHandler(board, inputController, true);

        //create the gamepanel and add it
        GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);

        //set the Thread
        gameLoop = new GameLoop(bh, inputController, gamePanel);
        gameLoopHasStarted = false;

        //add the buttons
        this.add(gamePanel);
        this.add(new BackButton(mainMenu));
        JButton startButton = new JButton("Start");
        startButton.requestFocus(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(!gameLoopHasStarted)
               {
                   System.out.println("started GameLoop");
                   try{
                   SwingUtilities.invokeLater(new Runnable() {
                       @Override
                       public void run(){
                           gameLoopHasStarted = true;
                           gameLoop.start();
                           requestFocusInWindow();
                        }
                        });
                   }
                   catch(Exception expenction)
                   {
                       expenction.printStackTrace();
                   }
               }
            }
        });
        this.add(startButton);

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                requestFocusInWindow();
            }
        });
    }

    public Dimension getPreferredSize()
    {
        return Config.SINGLE_PLAYER_SIZE;
    }
}
