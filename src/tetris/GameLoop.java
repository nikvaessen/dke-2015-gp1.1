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
    private gui.ScoreBoard scoreBoard;

    boolean shouldMovePieceDown;
    int count;

    private volatile boolean isPaused;
    private volatile boolean startNewGame;
    private volatile boolean endGameAprupt;
    private volatile boolean gameIsRunning;

    private int score;

    public GameLoop(BoardHandler boardHandler, InputController inputController, GamePanel gamePanel, gui.ScoreBoard
                    scoreBoard)
    {
        this.boardHandler = boardHandler;
        this.inputController = inputController;
        this.gamePanel = gamePanel;
        this.scoreBoard = scoreBoard;

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

    public void run()
    {
        waitingForGameStartLoop();
    }

    public void setPaused(boolean isPaused)
    {
        this.isPaused = isPaused;
    }

    public boolean isPaused()
    {
        return isPaused;
    }

    public boolean isRunning()
    {
        return gameIsRunning;
    }

    public void apruptGameEnd()
    {
        endGameAprupt = true;
    }

    public void startNewGame()
    {
        startNewGame = true;
        gameIsRunning = true;
        score = 0;
    }

    private void startGame()
    {
        String game = "my programmer did not tell me. Check startGame() method in GameLoop class for bugs.";
        if(boardHandler.isPentris())
            game = "pentris";
        else if(boardHandler.isTetris())
            game = "tetris";
        System.out.printf("Starting new game of %s!\n", game);
        boardHandler.spawnPiece();
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
                        scoreBoard.setScore(score);
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
            //System.out.println("User input: " + direction);
            if (input != ' ') {
                //System.out.println("user input was not empty, repainting");
                boardHandler.giveInput(input);
                gamePanel.repaint();
            }
            if (count > 10) //1000 ms have passed
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

    private void gameOver()
    {
        //handle score board
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
