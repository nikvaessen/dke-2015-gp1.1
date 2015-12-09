package tetris;

import gui.*;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chrx on 11/19/15.
 */
public class GameLoop extends Thread {

    private BoardHandler boardHandler;
    private InputController inputController;
    private GamePanel gamePanel;
    private NextPiecePanel nextPiecePanel;
    private tetris.HighScoreList highScoreList;

    boolean shouldMovePieceDown;
    int count;

    private volatile boolean isPaused;
    private volatile boolean startNewGame;
    private volatile boolean endGameAprupt;
    private volatile boolean gameIsRunning;


    private volatile int score;
    private String nameOfPlayer ;

    /**
     * Constructor for the game loop
     * @param boardHandler has the board the game is played on
     * @param inputController holds the input given by the player
     * @param gamePanel  the game panel which holds the board of the game
     * @param nextPiecePanel  panel containing the next piece
     * @param highScoreList  the list with the high scores
     */
    public GameLoop(BoardHandler boardHandler, InputController inputController, GamePanel gamePanel,
                    NextPiecePanel nextPiecePanel, tetris.HighScoreList highScoreList)
    {
        this.boardHandler = boardHandler;
        this.inputController = inputController;
        this.gamePanel = gamePanel;
        this.nextPiecePanel = nextPiecePanel;
        this.highScoreList =  highScoreList;

        //booleans
        this.isPaused = false;
        this.startNewGame = false;
        this.endGameAprupt = false;

        //for movement
        shouldMovePieceDown = false;
        count = 0;
        //score
        score = 0;
    }

    /**
     * It starts the thread
     */
    public void run()
    {
        waitingForGameStartLoop();
    }

    /**
     * Puses or unpauses the game loop
     * @param isPaused true if pause, false if unpause
     */
    public void setPaused(boolean isPaused)
    {
        this.isPaused = isPaused;
    }

    /**
     * Returns whether the game is paused
     * @return whether the game is paused; true if pause, false if unpause
     */
    public boolean isPaused()
    {
        return isPaused;
    }

    /**
     * Returns whether the game is running
     * @return whether the game is running; true if running, false if not running
     */
    public boolean isRunning()
    {
        return gameIsRunning;
    }

    /**
     * Imediately ends the game and the thread
     */
    public void apruptGameEnd()
    {
        endGameAprupt = true;
    }

    /**
     * Starts a new game
     */
    public void startNewGame()
    {
        startNewGame = true;
        gameIsRunning = true;
        score = 0;
    }

    /**
     * Returns the current score of the game
     * @return current score of the game
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Starts the game loop
     */
    private void startGame()
    {
        String game = "my programmer did not tell me. Check startGame() method in GameLoop class for bugs.";
        if(boardHandler.isPentris())
            game = "pentris";
        else if(boardHandler.isTetris())
            game = "tetris";
        System.out.printf("Starting new game of %s!\n", game);
        boardHandler.spawnPiece();
        nextPiecePanel.repaint();
        gamePanel.repaint();
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gameLoop();
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Game loop of the game
     */
    private void gameLoop()
    {
        if(endGameAprupt)
        {
            endGameAprupt = false;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    gameOver();
                }
            });
            return;
        }
        else if(isPaused)
        {
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pauseLoop();
                    }
                });
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return;
        }
        else {
            count++;
            //System.out.println(count);
            if (boardHandler.isPieceDoneFalling()) {
                //System.out.println("Spawning new piece");
                if (boardHandler.isGameOver()) {
                    System.out.println("Game over");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            gameOver();
                        }
                    });
                    return;
                }
                else {
                    int rowsCleared = boardHandler.checkFullLines();
                    if(rowsCleared != 0) {
                        score += rowsCleared*rowsCleared * 100;
                        //scoreBoard.setScore(score);
                        String line;
                        if(rowsCleared ==1)
                            line = "line";
                        else{
                            line = "lines";
                        }
                        System.out.printf("Congratulations on clearing %d %s! Your new score is %d.\n", rowsCleared,
                                line, score);
                    }
                    boardHandler.spawnPiece();
                    gamePanel.repaint();
                    nextPiecePanel.repaint();
                }
            }
            try {
                //System.out.println(sleeping for a second");
                this.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println("Looking for user input");
            char input = inputController.getCurrentInput();
            //System.out.println("User input: " + input);
            if (input != ' ') {
                //System.out.println("user input was not empty, repainting");
                boardHandler.giveInput(input);
                gamePanel.repaint();
            }
            if (count > 5) //1000 ms have passed
            {
                //System.out.println("10 loops have happened, moving the piece down");
                count = 0;
                boardHandler.giveInput('d');
                gamePanel.repaint();
            }
            ////System.out.println("restarting the game loop!");
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        gameLoop();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Asks the player for the name when the game is over
     */
    private void gameOver()
    {

        highScoreList.add(showPopUpWindow() , score) ;
        System.out.printf("final score: %d\n", score);
        score = 0;
        //wait for new game
        //startNewGame = false;
        gameIsRunning = false;
        count = 0;

        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    waitingForGameStartLoop();
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Displays a pop up window where the player can write his name after the game is over
     * @return the name of the player
     */
    private String showPopUpWindow()
    {
        JFrame fuuck = new JFrame() ;
        int messageType = JOptionPane.INFORMATION_MESSAGE ;
        nameOfPlayer = JOptionPane.showInputDialog(fuuck , " What's your name ? " , " Input Name " , messageType) ;
        return nameOfPlayer ;
    }

    /**
     * The loop during the game is paused
     */
    private void pauseLoop()
    {
       if(isPaused)
        {
            try{
                this.sleep(100);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        pauseLoop();
                    }
                });
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
           try {
               SwingUtilities.invokeLater(new Runnable() {
                   @Override
                   public void run() {
                       gameLoop();
                   }
               });
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }

    /**
     * It waits for the game loop to start
     */
    private void waitingForGameStartLoop()
    {
        if(!startNewGame)
        {
            try{
                this.sleep(100);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        waitingForGameStartLoop();
                    }
                });
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            startNewGame = false;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        startGame();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
