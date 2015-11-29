package tetris;

import gui.GamePanel;

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

    boolean shouldMovePieceDown;
    int count;

    private volatile boolean isPaused;

    public GameLoop(BoardHandler boardHandler, InputController inputController, GamePanel gamePanel)
    {
        this.boardHandler = boardHandler;
        this.inputController = inputController;
        this.gamePanel = gamePanel;
        isPaused = true;

        shouldMovePieceDown = false;
        count = 0;
    }

    public void run()
    {
        if(isPaused)
        {
            isPaused = false;
            gameLoop();
        }
        startLoop();
    }

    public void interrupt()
    {
        isPaused = true;
    }

    private void startLoop()
    {
        //System.out.println("try to spawn piece");
        boardHandler.spawnPiece();
        gamePanel.repaint();

        gameLoop();
    }

    private void gameLoop()
    {
        if(isPaused)
        {
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
        count++;
        //System.out.println(count);

        if(boardHandler.isPieceDoneFalling())
        {
            //System.out.println("Spawning new piece");
            if(boardHandler.isGameOver())
            {
                System.out.println("Game over");
                //todo handle game over
                return;
            }
            boardHandler.checkFullLines();
            boardHandler.spawnPiece();
            gamePanel.repaint();
        }
        try{
            //System.out.println(sleeping for a second");
            this.sleep(100);
        }
        catch(Exception e)
        {
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
        if(count > 8) //1000 ms have passed
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
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
