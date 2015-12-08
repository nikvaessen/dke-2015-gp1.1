package gui;

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
public class SinglePlayerWindow extends JPanel {

    private GameLoop gameLoop;
    private boolean gameLoopHasStarted;
    private BoardHandler bh;
    private HighScoreList highScoreList;

    private HumanInput inputController;

    private JPanel leftPanel;
    private JPanel rightPanel;

    public SinglePlayerWindow(MainMenu mainMenu) {
        //create the variables
        Board board = new Board(10, 20);
        Board nextPieceBoard = new Board(5, 5);
        final HumanInput inputController = new HumanInput();
        this.bh = new BoardHandler(board, nextPieceBoard, true);
        this.highScoreList = new HighScoreList();

        //behaviour
        this.addKeyListener(inputController);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(new GridBagLayout());

        //create the panels
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setSize(Config.LEFTPANEL_SIZE);
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setSize(Config.RIGHTPANEL_SIZE);

        //show the next piece
        NextPiecePanel nextPiecePanel = new NextPiecePanel(nextPieceBoard);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        //c.gridheight = 3;
        c.insets = new Insets(10, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        leftPanel.add(nextPiecePanel, c);

        //create the scoreboard
        final ScoreBoard scoreBoard = new ScoreBoard();
        c = new GridBagConstraints();
        c.gridy = 1;
        c.insets = new Insets(30, 0, 0, 0);
        leftPanel.add(scoreBoard, c);
        //add a timer that updates the scoreboard every 100 ms.
        new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                //System.out.println("Trying to update score");
                scoreBoard.setScore(gameLoop.getScore());
              }
            }).start();

        //create the highscore board
        HighScoreBoard highScoreBoard = new HighScoreBoard(highScoreList);
        c = new GridBagConstraints();
        c.gridy = 2;
        c.insets = new Insets(10, 0, 0, 0);
        leftPanel.add(highScoreBoard, c);

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
        c.gridy = 3;
        c.insets = new Insets(10, 0, 0, 0);
        leftPanel.add(optionList, c);

        //show controls
        JTextPane textArea = new JTextPane();
        textArea.setContentType("text/html");
        textArea.setSize(Config.LEFTPANEL_SIZE.width, 200);
        textArea.setText(
                "<html>" +
                        "<pre>" +
                        "<b>Controls</b><br />" +
                        "&rarr;         Move Right<br />" +
                        "&larr;         Move Left<br />" +
                        "&darr;         Move Down<br />" +
                        "<b>Space</b>     Drop<br />" +
                        "<b>X</b>         Rotate Clockwise<br />" +
                        "<b>Z</b>         Rotate Anticlockwise<br /><" +
                        "</pre>" +
                        "</html>");
        textArea.setEditable(false);
        textArea.setOpaque(false);
        c = new GridBagConstraints();
        c.gridy = 4;
        c.insets = new Insets(10, 0, 0, 0);
        leftPanel.add(textArea, c);


        //add the left panel
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(leftPanel, c);

        //create the gamepanel and add it
        final GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.SINGLE_PLAYER_SIZE);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = 0.5;
        c.gridy = 0;
        c.weighty = 1;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.CENTER;
        this.add(gamePanel, c);

        //set the Thread
        gameLoop = new GameLoop(bh, inputController, gamePanel, nextPiecePanel, highScoreList);
        gameLoop.start();
        gameLoopHasStarted = false;

        //add the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(30);
        buttonPanel.setLayout(new GridLayout(3,1,10,10));
        c = new GridBagConstraints();
        c.gridx = 2;
        c.weightx = 0.2;
        c.insets = new Insets(250,0,0,0);
        rightPanel.add(buttonPanel, c);

        //backbutton
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.SOUTH;
        c.insets = new Insets(10, 0, 20, 0);
        rightPanel.add(new BackButton(mainMenu), c);

        //add the right panel
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        this.add(rightPanel, c);

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
                           gameLoopHasStarted = true;
                           gameLoop.startNewGame();
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
                if(!gameLoop.isPaused()) {
                    gameLoop.setPaused(true);
                   // pauseButton.setText("Unpause");
                }
                else if(gameLoop.isPaused())
                {
                    gameLoop.setPaused(false);
                   // pauseButton.setText("Pause  ");
                }
            }
        });
        //reset button
        JButton resetButton = new JButton("Reset");
        buttonPanel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bh.resetBoard();
                optionList.setEnabled(true);
                if(gameLoop.isRunning())
                {
                    gameLoop.apruptGameEnd();
                }
                gameLoopHasStarted = false;
                gamePanel.repaint();
                startButton.setEnabled(true);
                gameLoop.setPaused(false);
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

    /*public Dimension getPreferredSize()
    {
        return Config.SINGLE_PLAYER_SIZE;
    }*/
}
