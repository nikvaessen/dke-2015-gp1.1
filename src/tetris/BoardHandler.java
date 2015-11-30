package tetris;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * Created by Nik on 11/12/15.
 */
public class BoardHandler {
    //classes needed to run this class
    private Board board;
    private Polyomino polyomino;
    private InputController input;
    private Random rng;

    //variables related to moving a piece
    private char[][] fallingPentMatrix; //the matrix of the currently falling
    private int rowOfPiece;
    private int columnOfPiece;
    private char kindOfPent; // letter corresponding to the shape of the pentomino
    private boolean needNewPiece;

    //score
    private int score;

    public BoardHandler(Board board, InputController input, boolean tetris)
    {
        this.board = board;
        this.input = input;
        if(tetris)
        {
           polyomino = new TetrisBlocks();
        }
        else
        {
            polyomino = new PentominoBlocks();
        }

        rng = new Random();
    }

    public void switchToTetris()
    {
        polyomino = new TetrisBlocks();
    }

    public void switchToPentris()
    {
        polyomino = new PentominoBlocks();
    }

    /**
     * Spawns a random pentomino in the top row(s) of the board.
     */
    public void spawnPiece()
    {
        rowOfPiece = 0;
        columnOfPiece = board.getWidth()/2 - 1;
        fallingPentMatrix = getRandomPentomino();
        board.placePiece(fallingPentMatrix, rowOfPiece, columnOfPiece);
        needNewPiece = false;
    }

    /**
     * returns a random matrix of a pentomino
     * @return
     */
    private char[][] getRandomPentomino()
    {
        ArrayList<Character> everyPentomiChar = polyomino.getKeys();
        kindOfPent = everyPentomiChar.get(rng.nextInt(everyPentomiChar.size()));
        char[][] toReturn = polyomino.getMatrix(kindOfPent, 0);
        if(polyomino.hasFlip(kindOfPent)) {
            if(rng.nextBoolean())
            {
                toReturn = polyomino.getFlippdMatrix(kindOfPent);
            }
        }
        return toReturn;
    }

    public void giveInput(char input)
    {
        //System.out.println("row of piece: "+ rowOfPiece + " column of piece: " + columnOfPiece);
        //System.out.println("Matrix of piece: \n" + Arrays.deepToString(fallingPentMatrix));
        if(input == 'l' || input == 'r'  || input == 'd')
        {
            movePentominoTo(input);
        }
        else if(input == 'z' || input == 'x')
        {
            rotatePentomino(input);
        }
    }

    private void rotatePentomino(char input)
    {
        //determine the new matrix of the piece
        char[][] rotatedMatrix = fallingPentMatrix;
        if(input == 'z'){
            rotatedMatrix = rotateMatrix(fallingPentMatrix, false);
        }
        else if(input == 'x')
        {
            rotatedMatrix = rotateMatrix(fallingPentMatrix, true);
        }
        //first remove the piece currently on the board
        removeCurrentPiece();
        //then try to place it in the new location
        if(board.canPlace(rotatedMatrix, rowOfPiece, columnOfPiece) ){
            try
            {
                //actually place it
                board.placePiece(rotatedMatrix, rowOfPiece, columnOfPiece);
                //and update the matrix
                fallingPentMatrix = rotatedMatrix;
            } catch (ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
        else{
            //replace in old position
            redrawCurrentPiece();
        }
    }

    //direction can be 'l' for left, 'r' for roght and 'd' for down
    private void movePentominoTo(char direction) {
        //determine the new values to move the piece left or right
        int rowTranslation = 0;
        int columnTranslation = 0;
        if (direction == 'l') {
            columnTranslation = -1;
        } else if (direction == 'r') {
            columnTranslation = 1;
        } else if (direction == 'd') {
            rowTranslation = 1;
        }
        int newRow = rowOfPiece + rowTranslation;
        int newColumn = columnOfPiece + columnTranslation;

        //first remove the piece currently on the board
        removeCurrentPiece();

        //then try to place it in the new location
        if(board.canPlace(fallingPentMatrix, newRow, newColumn) ){
            try
            {
                board.placePiece(fallingPentMatrix, newRow, newColumn);
                rowOfPiece = newRow;
                columnOfPiece = newColumn;
            } catch (ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
        else{
            //replace in old position
            redrawCurrentPiece();
            //if movement is down and it can't, it is done falling and a new piece should be spawned
            if(direction == 'd')
            {
                needNewPiece = true;
            }
        }
    }

    private void removeCurrentPiece()
    {
        for(int i = 0; i < fallingPentMatrix.length; i++)
        {
            for(int j = 0; j < fallingPentMatrix[i].length; j++)
            {
                if(fallingPentMatrix[i][j] != 'o')
                    board.setCell(i+rowOfPiece, j+columnOfPiece, 'o');
            }
        }
    }

    private void redrawCurrentPiece()
    {
        for(int i = 0; i < fallingPentMatrix.length; i++)
        {
            for(int j = 0; j < fallingPentMatrix[i].length; j++)
            {
                if(fallingPentMatrix[i][j] != 'o')
                    board.setCell(i+rowOfPiece, j+columnOfPiece, kindOfPent);
            }
        }
    }

    public boolean isGameOver()
    {
        for(int row=0; row<5; row++)
            for(int column=0; column<board.getWidth(); column++)
                if(board.getCell(row,column)!='o')
                    return true;

        return false;
    }

    public void checkFullLines()
    {
        for(int row = 0; row < board.getHeight(); row++)
        {
            boolean foundEmptyCell = false;
            for(int column= 0; column < board.getWidth(); column++)
            {
                if (board.getCell(row, column) == 'o' && !foundEmptyCell)
                {
                    foundEmptyCell = true;
                }
            }
            if(!foundEmptyCell){
                score++;
                clearLine(row);
            }
        }
    }

   private void clearLine(int row1)
    {
        for(int row=row1; row>5; row--)
        {
            for(int column=0; column<board.getWidth(); column++)
            {
                board.setCell(row,column, board.getCell(row-1,column));
            }
        }
    }

    public boolean isPieceDoneFalling()
    {
        return needNewPiece;
    }

    public void resetBoard()
    {
        board.emptyBoard();
    }

    private static char[][] rotateMatrix(char[][] matrixToRotate, boolean clockwise){
		/*
		step 1: To rotate a matrix, first transpose the matrix so that the first column becomes the
				first row, the second column becomes the second row, ect.
		*/
        //create a empty matrix with transposed row and column length
        char[][] rotatedMatrix = new char[matrixToRotate[0].length][matrixToRotate.length];
        //and set the new values
        for(int row = 0; row < rotatedMatrix.length; row++)
        {
            for(int column = 0; column < rotatedMatrix[row].length; column++)
            {
                rotatedMatrix[row][column] = matrixToRotate[column][row];
            }
        }

		/*
		step 2: For a clockwise rotation(+90), 	every row has to be reversed.
				for an anti-clockwise rotation, every column has to be reversed.
		 */

        if(clockwise)
        {
            //Loop through every row and reverse them
            for (int row = 0; row < rotatedMatrix.length; row++) {
                //first store all the values in a row
                int rowLength = rotatedMatrix[row].length;
                char[] rowValues = new char[rowLength];
                for(int column = 0; column < rowValues.length; column++)
                {
                    rowValues[column] = rotatedMatrix[row][column];
                }
                //then reversely add them back to the same row
                for(int column = 0; column < rowValues.length; column++)
                {
                    rotatedMatrix[row][column] = rowValues[rowLength - 1 - column];
                }
            }
        }
        else if(!clockwise)
        {
            //Loop through every column and reverse them
            for (int column = 0; column < rotatedMatrix[0].length; column++) {
                //first store all the values in a column
                int columnLength = rotatedMatrix.length;
                char[] columnValues = new char[columnLength];
                for(int row = 0; row < columnLength; row++)
                {
                    columnValues[row] = rotatedMatrix[row][column];
                }
                //then reversely add them back to the same column
                for(int row = 0; row < columnLength; row++)
                {
                    rotatedMatrix[row][column] = columnValues[columnLength - 1 - row];
                }
            }
        }
        return rotatedMatrix;
    }

}
