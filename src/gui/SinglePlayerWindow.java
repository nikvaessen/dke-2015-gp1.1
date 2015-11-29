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
    private BoardHandler bh;

    private InputController inputController;

    public SinglePlayerWindow(MainMenu mainMenu) {
        //create the variables
        Board board = new Board(10, 20);
        InputController inputController = new InputController();
        this.addKeyListener(inputController);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.bh = new BoardHandler(board, inputController, true);

        //create the combobox to choose between tetris and pentris
        String[] optionStrings = {"Tetris", "Pentris"};
        final JComboBox optionList = new JComboBox(optionStrings);
        optionList.setSelectedIndex(0);
        optionList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(optionList.getSelectedIndex() == 0)
                {
                    bh.switchToTetris();
                }
                else if(optionList.getSelectedIndex() == 1)
                {
                    bh.switchToPentris();
                }
            }
        });
        this.add(optionList);

        //create the gamepanel and add it
        final GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        this.add(gamePanel);

        //set the Thread
        gameLoop = new GameLoop(bh, inputController, gamePanel);
        gameLoopHasStarted = false;

        //add the buttons
        //backbutton
        this.add(new BackButton(mainMenu));
        //startbutton
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
                           optionList.setEnabled(false);
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
        //reset button
        JButton resetButton = new JButton("Reset");
        this.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bh.resetBoard();
                optionList.setEnabled(true);
                gameLoopHasStarted = false;
                gamePanel.repaint();
            }
        });
        //pause button
        JButton pauseButton = new JButton("Pause");
        this.add(pauseButton);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop.
            }
        });

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
