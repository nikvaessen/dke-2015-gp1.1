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

    public GameLoop(BoardHandler boardHandler, InputController inputController, GamePanel gamePanel)
    {
        this.boardHandler = boardHandler;
        this.inputController = inputController;
        this.gamePanel = gamePanel;

        shouldMovePieceDown = false;
        count = 0;
    }

    public void start()
    {
        //System.out.println("try to spawn piece");
        boardHandler.spawnPiece();
        gamePanel.repaint();

        gameLoop();
    }

    public void gameLoop()
    {
        count++;
        //System.out.println(count);
        if(boardHandler.isGameOver())
        {
            //System.out.println("Game over");
            //todo handle game over
            return;
        }
        if(boardHandler.isPieceDoneFalling())
        {
            //System.out.println("Spawning new piece");
            boardHandler.spawnPiece();
        }
        try{
            //System.out.println("sleeping for a second");
            this.sleep(100);
            //System.out.println("Done sleeping for a second");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //System.out.println("Looking for user input");
        char direction = inputController.getCurrentInput();
        //System.out.println("User input: " + direction);
        if (direction != ' ') {
            //System.out.println("user input was not empty, repainting");
            boardHandler.movePentominoTo(direction);
            gamePanel.repaint();
        }
        if(count > 10) //1000 ms have passed
        {
            ////System.out.println("10 loops have happened, moving the piece down");
            count = 0;
            boardHandler.movePentominoTo('d');
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
