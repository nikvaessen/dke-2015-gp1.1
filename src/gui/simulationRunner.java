package gui;
import tetris.Board;
import tetris.BoardHandler;
import tetris.Pentomino;

import javax.swing.*;
import java.util.Random;

/**
 * Created by Jonty on 08/12/2015.
 */
public class simulationRunner {


    /**
     * Created by chrx on 12/1/15.
     */
    public class Bot extends Thread{
        //final variables

        private final int TIME_BETWEEN_ACTIONS = 1000;
        int x=0;
        int i=0;
        private Random rng;
        private Board board;
        private BoardHandler boardHandler;
        private GamePanel gamePanel;
        private Pentomino pentomino;
        char[][][] order= {pentomino.getMatrix('v', 0), pentomino.getFlippdMatrix('z'), pentomino.getMatrix('f', 0), pentomino.getFlippdMatrix('n'), pentomino.getMatrix('w', 0), pentomino.getMatrix('p', 0), pentomino.getMatrix('t', 0), pentomino.getFlippdMatrix('y'), pentomino.getMatrix('x', 0), pentomino.getMatrix('u', 0), pentomino.getMatrix('i', 0), pentomino.getMatrix('l', 0)};


        //variables for board handling
        char[] actionCommands= {'l','l','s','x', 'l', 's', 'l','x','s', 'x','r','s','x','l','s','z','z','s','l','z','s','x','x','s','l','s','l','l','x','s','r','z','z','s','l','l','z','s','r','x','x','s','l','l','s','x','x','l','l','s','r','r','s','z','r','r','s'};
        private int count;
        private volatile int score;

        public Bot(Board board, BoardHandler boardHandler, GamePanel gamePanel, char order)
        {
            this.board = board;
            this.boardHandler = boardHandler;
            this.gamePanel = gamePanel;
            this.rng = new Random(System.currentTimeMillis());
            count = 0;
        }

        public void run()
        {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    playGame();
                }
            });
        }

        private void playGame()
        {
            count++;
            //System.out.println(count);
            if (boardHandler.isPieceDoneFalling()) {
                //System.out.println("Spawning new piece");
                if (boardHandler.isGameOver()) {
                    System.out.println("Game over");
                    return;
                }
                else {
                    int rowsCleared = boardHandler.checkFullLines();
                    if(rowsCleared != 0) {
                        //scoreBoard.setScore(score);
                        score += rowsCleared*rowsCleared * 100;
                    }
                    i++;
                    boardHandler.hardSpawnPiece(order[i]);
                    gamePanel.repaint();
                }
            }
            try {
                //System.out.println(sleeping for a second");
                sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println("Looking for user input");
            char input = getMovementCommand();
            //System.out.println("Input: " + input);
            //System.out.println("User input: " + input);
            if (input != ' ') {
                //System.out.println("user input was not empty, repainting");
                x++;
                boardHandler.giveInput(actionCommands[x]);
                gamePanel.repaint();
            }
            if (count > 10) //1000 ms have passed
            {
                count = 0;
                //System.out.println("10 loops have happened, moving the piece down");
                boardHandler.giveInput('d');
                gamePanel.repaint();

            }
            ////System.out.println("restarting the game loop!");
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        playGame();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private boolean wait(int ms)
        {
            //System.out.println("Waiting: " + System.currentTimeMillis());
            try{
                sleep(ms);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return true;
            //System.out.println("done Waiting: " + System.currentTimeMillis());
        }


        private char getMovementCommand()
        {
            char action = actionCommands[45];
            char[] newActionCommands = new char[actionCommands.length - 1];
            // System.out.println("old array: " + Arrays.toString(actionCommands));
            System.arraycopy(actionCommands, 1, newActionCommands, 0, newActionCommands.length);
//        System.out.println("new array: " + Arrays.toString(newActionCommands));
            actionCommands = newActionCommands;
            return action;
        }


    }

}
