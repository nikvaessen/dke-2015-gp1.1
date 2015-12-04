package bot;

import gui.GamePanel;
import tetris.Board;
import tetris.BoardHandler;
import tetris.GameLoop;

import javax.swing.*;
import java.util.Random;
import java.util.jar.Pack200;

/**
 * Created by chrx on 12/1/15.
 */
public class Bot extends Thread{

    //final variables
    final static char ACTION_MOVE_DOWN = 'd';
    final static char ACTION_MOVE_LEFT = 'l';
    final static char ACTION_MOVE_RIGHT = 'r';
    final static char ACTION_DROP_DOWN = 's';
    final static char ACTION_ROTATE_CLOCKWISE = 'x';
    final static char ACTION_ROTATE_ANTICLOCKWISE = 'z';

    private final int TIME_BETWEEN_ACTIONS = 1000;

    private Random rng;
    private Board board;
    private BoardHandler boardHandler;
    private GamePanel gamePanel;

    //variables keeping track of board
    private boolean boardHasChanged;
    private char[][] previousBoard;

    public Bot(Board board, BoardHandler boardHandler, GamePanel gamePanel)
    {
        this.board = board;
        this.boardHandler = boardHandler;
        this.gamePanel = gamePanel;
        this.rng = new Random(System.currentTimeMillis());
    }

    public void run()
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startPlayingGame();
            }
        });
    }

    private void startPlayingGame()
    {
        if(boardHandler.isGameOver()){
            System.out.println("beep beep bot shutting down beep bop");
            return;
        }
        if(boardHandler.isPieceDoneFalling())
        {
            boardHandler.spawnPiece();
        }
        boolean left = rng.nextBoolean();
        System.out.println("going left: " + left);
        if(left)
        {
            makeAction(ACTION_MOVE_LEFT, 5);
            makeAction(ACTION_DROP_DOWN, 1);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    startPlayingGame();
                }
            });
        }
        else if(!left)
        {           
            makeAction(ACTION_MOVE_RIGHT, 5);
            makeAction(ACTION_DROP_DOWN, 1);
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
        System.out.println("Waiting: " + System.currentTimeMillis());
        try{
            this.sleep(ms);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("done Waiting: " + System.currentTimeMillis());
    }

    private void makeAction(final char action, final int n)
    {
        if(n <= 0)
        {
            return;
        }
        else {
            wait(TIME_BETWEEN_ACTIONS);
            boardHandler.giveInput(action);
            gamePanel.repaint();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    makeAction(action, n - 1);
                }
            });
        }
    }

    private char[][] waitForBoardChange(char[][] matrix)
    {
        wait(1000);
        if(!areEqual(board.getBoard(), matrix))
        {
            System.out.println("not equal, waiting longer");
            waitForBoardChange(board.getBoard());
        }
        return matrix;
    }

    private boolean areEqual(char[][] board1, char[][] board2)
    {
        for(int i = 0; i < board1.length; i++){
            for(int j = 0; j < board1[i].length; j++){
                if(board1[i][j] != board2[i][j])
                    return false;
            }
        }
        return true;
    }

}

