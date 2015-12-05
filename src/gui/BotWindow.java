package gui;

import bot.Bot;
import bot.BotInput;
import tetris.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by baxie on 15-11-15.
 */
public class BotWindow extends JPanel
{
    private GameLoop gameLoop;
    private boolean gameLoopHasStarted;
    private BoardHandler bh;


    public BotWindow(MainMenu mainMenu) {
        //create the variables
        Board board = new Board(10, 20);
        this.bh = new BoardHandler(board, true);
        //behaviour
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        //create the scoreboard
        final ScoreBoard scoreBoard = new ScoreBoard();
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(scoreBoard, c);
        //add a timer that updates the scoreboard every 100 ms.
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Trying to update score");
                scoreBoard.setScore(gameLoop.getScore());
            }
        }).start();

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
        optionList.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                optionList.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        this.add(optionList, c);

        //create the gamepanel and add it
        final GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = 0.5;
        c.gridy = 0;
        c.weighty = 1;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.CENTER;
        this.add(gamePanel, c);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gamePanel.repaint();
                    }
                }).start();
            }
        }).start();

        //add the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(30);
        buttonPanel.setLayout(new GridLayout(3,1,10,10));
        c = new GridBagConstraints();
        c.gridx = 2;
        c.weightx = 0.2;
        c.insets = new Insets(250,0,0,0);
        this.add(buttonPanel, c);

        //backbutton
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(0, 0, 20, 0);
        this.add(new BackButton(mainMenu), c);

        //create bot Thread

        final Bot bot = new Bot(board, bh, gamePanel);

        //startbutton
        final JButton startButton = new JButton("Start");
        startButton.requestFocus(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gameLoopHasStarted)
                {
                    try{
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run(){
                                optionList.setEnabled(false);
                                requestFocusInWindow();
                                startButton.setEnabled(false);
                                bot.run();

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
        buttonPanel.add(startButton);

    }

    public Dimension getPreferredSize()
    {
        return Config.SINGLE_PLAYER_SIZE;
    }



}
