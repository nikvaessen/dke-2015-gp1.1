package bot;

import gui.GamePanel;
import tetris.Board;
import tetris.BoardHandler;
import tetris.GameLoop;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    //variables for board handling
    char[] actionCommands;
    private int count;
    private volatile int score;

    public Bot(Board board, BoardHandler boardHandler, GamePanel gamePanel)
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
                boardHandler.spawnPiece();
                makeMovementCommands();
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
        char input = getMovementCommand();
        //System.out.println("Input: " + input);
        //System.out.println("User input: " + input);
        if (input != ' ') {
            //System.out.println("user input was not empty, repainting");
            boardHandler.giveInput(input);
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
            this.sleep(ms);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
        //System.out.println("done Waiting: " + System.currentTimeMillis());
    }

    private void makeMovementCommands()
    {
        char[][] botBoard = board.getBoard();
        Board cloneBoard= board.clone();

        char[][] pieceFalling = boardHandler.getFallingPentMatrix();
        char letterPieceFalling=boardHandler.getFallingPentName();
        int currentRow = boardHandler.getCurrentRow();
        int currentColumn = boardHandler.getCurrentColumn();
        int[] recommendedPositionAndRotation = getRecommendedPositionAndRotation(botBoard, pieceFalling);
        int newRow = recommendedPositionAndRotation[0];
        int newColumn = recommendedPositionAndRotation[1];
        int rotation = recommendedPositionAndRotation[2];
        ArrayList<Character> newActionCommands = new ArrayList<>();
        newActionCommands.add('d');
        newActionCommands.add('d');
        for(int i = rotation; i > 0; i-- )
        {
            newActionCommands.add(ACTION_ROTATE_CLOCKWISE);
        }
        for(int i = 0; currentColumn != newColumn; )
        {
            if(currentColumn < newColumn)
            {
                newActionCommands.add(ACTION_MOVE_RIGHT);
                currentColumn++;
            }
            else{
                newActionCommands.add(ACTION_MOVE_LEFT);
                currentColumn--;
            }
        }
        newActionCommands.add(ACTION_DROP_DOWN);
        actionCommands = new char[newActionCommands.size()];
        for( int i = 0; i < newActionCommands.size(); i++)
        {
            actionCommands[i] = newActionCommands.get(i);
        }
    }

    private boolean testPath(int newRow, int newColumn)
    {

        for(int i=5; i<newRow; i++)
        {
            if(board.getCell(i, newColumn) != 'o') return false;
        }

        return true;


    }

    private char getMovementCommand()
    {
        char action = actionCommands[0];
        char[] newActionCommands = new char[actionCommands.length - 1];
       // System.out.println("old array: " + Arrays.toString(actionCommands));
       System.arraycopy(actionCommands, 1, newActionCommands, 0, newActionCommands.length);
//        System.out.println("new array: " + Arrays.toString(newActionCommands));
        actionCommands = newActionCommands;
        return action;
    }

    private int[] getRecommendedPositionAndRotation(char[][] board, char[][] piece)
    {
        Board testBoard = new Board(0,0);
        testBoard.setBoard(board);
        ArrayList<char[][]> possiblePieces = new ArrayList<>();
        possiblePieces.add(piece);
        possiblePieces.add(BoardHandler.rotateMatrix(piece, true));
        possiblePieces.add(BoardHandler.rotateMatrix(possiblePieces.get(1), true));
        possiblePieces.add(BoardHandler.rotateMatrix(possiblePieces.get(2), true));

        ArrayList <Integer> xCoords= new ArrayList<>();
        ArrayList <Integer> yCoords= new ArrayList<>();
        int highestRow = board.length - 1;
        for(int i=board.length-1; i>=5; i--)
        {
            for (int j = board[0].length-1; j >= 0; j--)
            {
                if(board[i][j] != 'o')
                {
                    highestRow = i;
                  //  System.out.println("Highest row: " + highestRow);
                    break;
                }
            }
        }
        //fill the array with all the coords to check
        for(int i = highestRow - 2; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                xCoords.add(i);
                //System.out.println("Row: " + i + " Column: " + j);
                yCoords.add(j);
            }
        }

        //at every coord, try to place all rotations of the matrix and calculate the "snug" level
        int bestX = 0;
        int bestY = 0;
        int bestR = 0;
        double bestScore = -100000000;
        Board bestBoard = new Board(15,20);
        for(int i=0; i<xCoords.size(); i++)
        {
            int x = xCoords.get(i);
            int y = yCoords.get(i);
            //System.out.println("Row: " + x + " Column: " + y );
            for(int rot = 0; rot < possiblePieces.size(); rot++)
            {
                if(testBoard.canPlace(possiblePieces.get(rot), x, y))
                {
                    testBoard.placePiece(possiblePieces.get(rot), x, y);
                    //System.out.println(Arrays.deepToString(possiblePieces.get(rot)));

                    double tempScore = -0.510066*testBoard.aggregateHeight() + 0.760666* testBoard.checkFullLines()
                            + -0.35663 * testBoard.amountOfHoles() + -0.184483 * testBoard.bumpiness();
//                    testBoard.printBoard();
//                    System.out.printf(" Aggregate height: %d %n Amount of holes: %d %n Bumpiness: %d %n " +
//                            "Full lines: %d %n ", testBoard.aggregateHeight(), testBoard.amountOfHoles(),
//                            testBoard.bumpiness(), testBoard.checkFullLines());
//                    System.out.println("Score: " + tempScore);

                    if(tempScore > bestScore && testPath(x,y)){

                        bestX = x;
                        bestY = y;
                        bestR = rot;
                        bestScore=tempScore;
                        bestBoard= testBoard.clone();
                        //System.out.println("Max score: " + bestScore);

                    }
                    testBoard.removePiece(possiblePieces.get(rot), x , y);
                }
            }
        }

        //System.out.println("Printing best board:");
        //bestBoard.printBoard();
        return new int[] {bestX, bestY, bestR};
    }

    public int getScore()
    {
        return score;
    }

}