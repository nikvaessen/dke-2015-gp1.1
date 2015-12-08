/**
 * Created by baxie on 12/7/15.
 */
package bot;

import gui.GamePanel;
import tetris.Board;
import tetris.BoardHandler;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class GeneticBot extends Thread{
    //final variables
    final static char ACTION_MOVE_DOWN = 'd';
    final static char ACTION_MOVE_LEFT = 'l';
    final static char ACTION_MOVE_RIGHT = 'r';
    final static char ACTION_DROP_DOWN = 's';
    final static char ACTION_ROTATE_CLOCKWISE = 'x';

    private Random rng;
    private Board board;
    private BoardHandler boardHandler;

    //variables needed for genetic algorithm
    private boolean gameOver;
    private int linesCleared;
    private double[] chromesome;
    private int MAXIMUM_SPAWNS_ALLOWED = 5000;
    private int spawns;

    //variables for board handling
    char[] actionCommands;
    private int count;

    public GeneticBot(Board board, BoardHandler boardHandler, double[] chromesome)
    {
        this.board = board;
        this.boardHandler = boardHandler;
        this.rng = new Random(System.currentTimeMillis());
        this.chromesome = chromesome;
        count = 0;
        spawns = 0;
        linesCleared = 0;
    }

    public double[] getChromosome()
    {
        return chromesome;
    }

    public void setChromosome(double[] chromesome)
    {
        this.chromesome = chromesome;
    }

    public boolean gameOver()
    {
        return gameOver;
    }

    public int getLinesCleared()
    {
        return linesCleared;
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
            if (boardHandler.isGameOver() || spawns >= MAXIMUM_SPAWNS_ALLOWED) {
                gameOver = true;
                return;
            }
            else {
                int rowsCleared = boardHandler.checkFullLines();
                if(rowsCleared != 0) {
                    //scoreBoard.setScore(score);
                }
                boardHandler.spawnPiece();
                spawns++;
                makeMovementCommands();
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
        }
        if (count > 10) //1000 ms have passed
        {
            count = 0;
            //System.out.println("10 loops have happened, moving the piece down");
            boardHandler.giveInput(ACTION_MOVE_DOWN);
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


    private void makeMovementCommands()
    {
        char[][] botBoard = board.getBoard();
        Board cloneBoard= board.clone();

        char[][] pieceFalling = boardHandler.getFallingPentMatrix();
        int currentColumn = boardHandler.getCurrentColumn();
        int[] recommendedPositionAndRotation = getRecommendedPositionAndRotation(botBoard, pieceFalling);
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

    private char getMovementCommand()
    {
        char action = actionCommands[0];
        char[] newActionCommands = new char[actionCommands.length - 1];
        System.arraycopy(actionCommands, 1, newActionCommands, 0, newActionCommands.length);
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
                    double tempScore = chromesome[0]*testBoard.aggregateHeight() + chromesome[1]* testBoard.checkFullLines()
                            + chromesome[2] * testBoard.amountOfHoles() + chromesome[3] * testBoard.bumpiness();

                    if(tempScore > bestScore){
                        bestX = x;
                        bestY = y;
                        bestR = rot;
                        bestScore=tempScore;
                        bestBoard= testBoard.clone();
                    }
                    testBoard.removePiece(possiblePieces.get(rot), x , y);
                }
            }
        }

        //System.out.println("Printing best board:");
        //bestBoard.printBoard();
        return new int[] {bestX, bestY, bestR};
    }
}