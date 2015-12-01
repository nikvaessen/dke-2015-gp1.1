package bot;

import tetris.Board;
import tetris.GameLoop;

import javax.swing.*;
import java.util.Random;

/**
 * Created by chrx on 12/1/15.
 */
public class Bot extends Thread{

    private BotInput input;
    private GameLoop gameLoop;
    private Random rng;



    public Bot(BotInput input, GameLoop gameLoop)
    {
        this.input=input;
        this.gameLoop = gameLoop;
        this.rng = new Random(System.currentTimeMillis());
    }

    public void run()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                waitForGameStart();
            }
        });
    }

    private void startPlayingGame()
    {
        if(gameLoop.hasGameEnded()){
            System.out.println("beep beep bot shutting down beep bop");
            return;
        }
        boolean left = rng.nextBoolean();
        System.out.println("going left: " + left);
        if(left)
        {
            input.moveLeft();
            this.wait(500);
            input.moveLeft();
            this.wait(500);
            input.moveLeft();
            this.wait(500);
            input.moveLeft();
            this.wait(500);
            input.moveLeft();
            this.wait(500);
            input.moveLeft();
            this.wait(500);
            input.moveCompletelyDown();
            this.wait(200);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    startPlayingGame();
                }
            });
        }
        else if(!left)
        {           
            input.moveRight();
            this.wait(500);
            input.moveRight();
            this.wait(500);
            input.moveRight();
            this.wait(500);
            input.moveRight();
            this.wait(500);
            input.moveRight();
            this.wait(500);
            input.moveRight();
            this.wait(500);
            input.moveCompletelyDown();
            this.wait(200);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    startPlayingGame();
                }
            });


        }
    }

    private void wait(int ms)
    {
        try{
            this.sleep(ms);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void waitForGameStart()
    {
        if(!gameLoop.isRunning())
        {
             wait(100);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    waitForGameStart();
                }
            });
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    startPlayingGame();
                }
            });
        }
    }


}

