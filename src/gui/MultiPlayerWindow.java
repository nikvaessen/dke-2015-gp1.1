package gui;

import tetris.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class MultiPlayerWindow extends JPanel
{

    private GameLoop gameLoop1 ;
    private GameLoop gameLoop2 ;
    private boolean gameLoopHasStarted1 ;
    private boolean gameLoopHasStarted2 ;
    private BoardHandler bh1 ;
    private BoardHandler bh2 ;
    private HighScoreList highScoreList;

    private HumanInput inputController1 ;
    private HumanInput inputController2 ;

    private JPanel scorePanel;
    private JPanel rightPanel;

    public MultiPlayerWindow( MainMenu mainMenu )
    {
        //create the variables
        Board board1 = new Board(10 , 20 ) ;
        Board board2 = new Board(10 , 20 ) ;
        final HumanInput inputController1 = new HumanInput() ;
        final HumanInput inputController2 = new HumanInput() ;
        this.bh1 = new BoardHandler(board1 , new Board(0, 0), true) ;
        this.bh2 = new BoardHandler(board2 , new Board(0, 0), true) ;
        this.highScoreList = new HighScoreList() ;


        //behaviour
        this.addKeyListener(inputController1);
        this.addKeyListener(inputController2);
        this.setFocusable(true);
        this.requestFocusInWindow() ;
        GridBagLayout gbl = new GridBagLayout() ;
        this.setLayout(gbl);

        //create panels
        scorePanel = new JPanel() ;
        scorePanel.setLayout(new GridBagLayout());
        scorePanel.setSize(Config.LEFTPANEL_SIZE);
        rightPanel = new JPanel() ;
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setSize(Config.RIGHTPANEL_SIZE);

        //create the ScoreBoard
        final ScoreBoard scoreBoard = new ScoreBoard() ;
        GridBagConstraints d = new GridBagConstraints() ;
        d.gridx = 0 ;
        d.gridy = 0 ;
        scorePanel.add(scoreBoard , d) ;
        d.insets = new Insets(30,10,10,0);
        //add a timer to update ScoreBoard
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Trying to update score");
                scoreBoard.setScore(gameLoop1.getScore());
                scoreBoard.setScore(gameLoop2.getScore());
            }
        }).start();

        //create the Highscore Board
        HighScoreBoard highScoreBoard = new HighScoreBoard(highScoreList);
        d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 1;
        d.insets = new Insets(30,10,10,10);
        scorePanel.add(highScoreBoard, d);

        //create the combobox to choose between tetris and pentris
        String[] optionStrings = {"Tetris", "Pentris"};
        final JComboBox optionList = new JComboBox(optionStrings);
        optionList.setSelectedIndex(0);
        optionList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(optionList.getSelectedIndex() == 0)
                {
                    bh1.switchToTetris();
                    bh2.switchToTetris();
                }
                else if(optionList.getSelectedIndex() == 1)
                {
                    bh1.switchToPentris();
                    bh2.switchToPentris();
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

        d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 5;
        d.weightx = 0.5;
        d.insets = new Insets(30,10,10,0);
        scorePanel.add(optionList, d);

        //add the scorePanel
        d = new GridBagConstraints();
        d.gridx = 1;
        d.gridy = 0;
        this.add(scorePanel, d);

        final GamePanel gamePanel1 = new GamePanel(board1);
        final GamePanel gamePanel2 = new GamePanel(board2);
        d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 0;
        gamePanel1.setSize(Config.GAME_PANEL_SIZE);
        this.add(gamePanel1, d);
        d = new GridBagConstraints();
        d.gridx = 2;
        d.gridy = 0;
        gamePanel2.setSize(Config.GAME_PANEL_SIZE);
        this.add(gamePanel2, d);

        //set the Thread
        gameLoop1 = new GameLoop(bh1, inputController1, gamePanel1, new NextPiecePanel(new Board(1, 1)), highScoreList);
        gameLoop2 = new GameLoop(bh2, inputController2, gamePanel2, new NextPiecePanel(new Board(1, 1)), highScoreList);
        gameLoop1.start();
        gameLoop2.start();
        gameLoopHasStarted1 = false;
        gameLoopHasStarted2 = false;

        //add the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(30);
        buttonPanel.setLayout(new GridLayout(3,1,10,10));
        d = new GridBagConstraints();
        d.gridx = 0;
        d.weightx = 0.2;
        d.gridy = 0;
        d.insets = new Insets(200,20,0,20);
        rightPanel.add(buttonPanel, d);

        //backbutton
        d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 2;
        d.anchor = GridBagConstraints.SOUTH;
        d.insets = new Insets(20, 20, 0, 20);
        rightPanel.add(new BackButton(mainMenu), d);

        //add the right panel
        d = new GridBagConstraints();
        d.gridx = 1;
        d.gridy = 5;
        this.add(rightPanel, d);

        final JButton startButton = new JButton("Start");
        startButton.requestFocus(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gameLoopHasStarted1 && !gameLoopHasStarted2)
                {
                    try{
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run(){
                                gameLoopHasStarted1 = true;
                                gameLoopHasStarted2 = true;
                                gameLoop1.startNewGame();
                                gameLoop2.startNewGame();
                                optionList.setEnabled(false);
                                requestFocusInWindow();
                                startButton.setEnabled(false);
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

        //pause button
        final JButton pauseButton = new JButton("Pause  ");
        buttonPanel.add(pauseButton);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!gameLoop1.isPaused() && !gameLoop2.isPaused()) {
                    gameLoop1.setPaused(true);
                    gameLoop2.setPaused(true);
                    pauseButton.setText("Unpause");
                }
                else if(gameLoop1.isPaused()&& gameLoop2.isPaused())
                {
                    gameLoop1.setPaused(false);
                    gameLoop2.setPaused(false);
                    pauseButton.setText("Pause  ");
                }
            }
        });

        //reset button
        JButton resetButton = new JButton("Reset");
        buttonPanel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bh1.resetBoard();
                bh2.resetBoard();
                optionList.setEnabled(true);
                if(gameLoop1.isRunning() && gameLoop2.isRunning())
                {
                    gameLoop1.apruptGameEnd();
                    gameLoop2.apruptGameEnd();
                }
                gameLoopHasStarted1 = false;
                gameLoopHasStarted2 = false;
                gamePanel1.repaint();
                gamePanel2.repaint();
                startButton.setEnabled(true);
                gameLoop1.setPaused(false);
                gameLoop2.setPaused(false);
                pauseButton.setText("Pause");
                scoreBoard.setScore(0);

            }
        });

        //focuslistener for inputController
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
        return Config.MULTI_PLAYER_SIZE;
    }


}
