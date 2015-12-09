package tetris;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Nik on 11/12/15.
 */
public class BoardHandler {
    //classes needed to run this class
    private Board board;
    private Board nextPieceBoard;
    private Polyomino polyomino;
    private Random rng;

    //variables related to moving a piece
    private char[][] fallingPentMatrix;     //the matrix of the currently falling
    private char[][] nextPiece;             //the matrix of the next piece
    private int rowOfPiece;
    private int columnOfPiece;
    private char kindOfPent;                // letter corresponding to the shape of the pentomino
    private boolean needNewPiece;

    //random pieces
    private char[] keys;
    private boolean[] keysFlipped;

    /**
     * Constructor for the board handler
     * @param board the board on which the game is played
     * @param nextPieceBoard the board with the next piece
     * @param tetris whether the board handler plays tetris or pentris
     */
    public BoardHandler(Board board, Board nextPieceBoard, boolean tetris)
    {
        this.board = board;
        this.nextPieceBoard = nextPieceBoard;
        if(tetris)
        {
           polyomino = new Tetromino();
        }
        else
        {
            polyomino = new Pentomino();
        }
        needNewPiece = true;
        rng = new Random(System.currentTimeMillis());
    }

    /**
     * It sets the game to play Tetris
     */
    public void switchToTetris()
    {
        polyomino = new Tetromino();
    }

    /**
     * It sets the game to play Pentris
     */
    public void switchToPentris()
    {
        polyomino = new Pentomino();
    }

    /**
     * gets the current matrix of piece being 'handled' by the class
     * @return the matrix of the piece being handled
     */
    public char[][] getFallingPentMatrix()
    {
        return fallingPentMatrix;
    }

    /**
     * get the current kind of piece currently being handled
     * @return
     */
    public char getFallingPentName()
    {
        return kindOfPent;
    }

    /**
     * get the current row of the first block of the piece falling
     * @return the current row of the first block of the piece falling
     */
    public int getCurrentRow()
    {
        return rowOfPiece;
    }

    /**
     * get the current column of the first block of the piece falling
     * @return the current column of the first block of the piece falling
     */
    public int getCurrentColumn()
    {
        return columnOfPiece;
    }

    /**
     * Returns whether the game is playing pentris or not
     * @return true if the game is playing pentris, false if not
     */
    public boolean isPentris()
    {
        if(polyomino instanceof Pentomino)
        {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns whether the game is playing tetris or not
     * @return true if the game is playing tetris, false if not
     */
    public boolean isTetris()
    {
        if(polyomino instanceof  Tetromino)
        {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Spawns a random pentomino in the top row(s) of the board.
     * @param q the matrix of the matrix falling
     */
    public void hardSpawnPiece(char[][] q)
    {
        rowOfPiece = 0;
        columnOfPiece = board.getWidth()/2 - 1;
        fallingPentMatrix = q;
        kindOfPent = getKindOfPent();
        board.placePiece(fallingPentMatrix, rowOfPiece, columnOfPiece);
        needNewPiece = false;
    }

    /**
     * Spawns a random pentomino in the top row(s) of the board.
     */
    public void spawnPiece()
    {
        if(nextPiece == null)
        {
            nextPiece = getRandomPentomino();
        }
        rowOfPiece = 0;
        columnOfPiece = board.getWidth()/2 - 1;
        fallingPentMatrix = nextPiece;
        nextPiece = getRandomPentomino();
        nextPieceBoard.emptyBoard();
        nextPieceBoard.placePiece(nextPiece, 0, 0);
        kindOfPent = getKindOfPent();
        board.placePiece(fallingPentMatrix, rowOfPiece, columnOfPiece);
        needNewPiece = false;
    }


    /**
     * returns a random matrix of a pentomino
     * @return random matrix of a pentomino
     */
    private char[][] getRandomPentomino()
    {
        if (keys == null || keys.length == 0) {
            ArrayList<Character> nonFlippedKeys = polyomino.getKeys();
            ArrayList<Character> flippedKeys = polyomino.getFlippedKeys();
            //System.out.printf("non flipped keys: %s\n", nonFlippedKeys.toString());
            //System.out.printf("flipped keys: %s\n", flippedKeys.toString());
            keys = new char[nonFlippedKeys.size()+flippedKeys.size()];
            keysFlipped = new boolean[nonFlippedKeys.size()+flippedKeys.size()];
            for (int i=0; i<nonFlippedKeys.size(); i++)
            {
                keys[i] = nonFlippedKeys.get(i);
                keysFlipped[i] = false;
            }
            for (int i = nonFlippedKeys.size(); i<nonFlippedKeys.size()+flippedKeys.size(); i++)
            {
                keys[i] = flippedKeys.get(i-nonFlippedKeys.size());
                keysFlipped[i] = true;
            }
        }
        int randomNumber = rng.nextInt(keys.length);
//        System.out.printf("Random number: %d key value: %c flipped: %b\n", randomNumber, keys[randomNumber],
//                keysFlipped[randomNumber]);
//        System.out.printf("Keys: %s\nBoolean array: %s\n", Arrays.toString(keys), Arrays.toString(keysFlipped));
        char[][] toReturn;
        if(!keysFlipped[randomNumber])
        {
            toReturn = polyomino.getMatrix(keys[randomNumber], 0);
        }
        else {
            kindOfPent = keys[randomNumber];
            toReturn = polyomino.getFlippdMatrix(keys[randomNumber]);
        }
        char[] copy = new char[keys.length-1];
        boolean[] copy1 = new boolean[keys.length-1];
        boolean skipped = false;
        for (int i=0; i<keys.length; i++ )
        {
            if (i == randomNumber) {
                skipped = true;
                continue;
            } else if (!skipped){
                copy[i]=keys[i];
                copy1[i]=keysFlipped[i];
            } else {
                copy[i-1]=keys[i];
                copy1[i-1]=keysFlipped[i];
            }
        }
        keys = copy;
        keysFlipped = copy1;
        return toReturn;
    }

    /**
     * It moves the piece to the given input
     * @param input the input where the piece will be moved
     */
    public void giveInput(char input)
    {
        //System.out.println("row of piece: "+ rowOfPiece + " column of piece: " + columnOfPiece);
        //System.out.println("Matrix of piece: \n" + Arrays.deepToString(fallingPentMatrix));
        if(input == 'l' || input == 'r'  || input == 'd' || input == 's')
        {
            movePentominoTo(input);
        }
        else if(input == 'z' || input == 'x')
        {
            rotatePentomino(input);
        }
    }

    /**
     * It rotates the pentomino to the given input
     * @param input the input to which the pentomino will be placed after rotation
     */
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

    /**
     * It moves the pentomino to the given direction
     * @param direction the direction to which the pentomino will be moved
     */
    private void movePentominoTo(char direction) {
        //determine the new values to move the piece left or right
        int rowTranslation = 0;
        int columnTranslation = 0;
        if (direction == 'l') {
            columnTranslation = -1;
        } else if (direction == 'r') {
            columnTranslation = 1;
        } else if (direction == 'd' || direction == 's') {
            rowTranslation = 1;
        } else if (direction == ' ') {
            removeCurrentPiece();
            while (isPieceDoneFalling()==false ){
                rowTranslation=1;
                int newRow = rowOfPiece + rowTranslation;
                rowTranslation=0;
        }
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
            else if(direction == 's')
            {
                needNewPiece = true;
                return;
            }
        }
        if(direction == 's')
        {
            movePentominoTo('s');
        }
    }

    /**
     * It removes the current piece from the current position
     */
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

    /**
     * It redraws the piece to the stored position
     */
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

    /**
     * Checks whether the game is over
     * @return true if the game is over, false if the game is not over
     */
    public boolean isGameOver()
    {
        for(int row=0; row<5; row++)
            for(int column=0; column<board.getWidth(); column++)
                if(board.getCell(row,column)!='o')
                    return true;

        return false;
    }

    /**
     * It checks the amount of full lines the board has
     * @return the amount of full lines the board has
     */
    public int checkFullLines()
    {
        int rowsCleared = 0;
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
                rowsCleared++;
                clearLine(row);
            }
        }
        return rowsCleared;
    }

    /**
     * Clears the specified row
     * @param row1 the row that will be cleared
     */
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

    /**
     * Returns whether there needs to be a newly spawned piece
     * @return true if there needs to be a new spawned piece, false if not
     */
    public boolean isPieceDoneFalling()
    {
        return needNewPiece;
    }

    /**
     * It resets the board so a new game can be started
     */
    public void resetBoard()
    {
        this.needNewPiece = false;
        board.emptyBoard();
    }

    /**
     * It rotates a given matrix clockwise or anticlockwise
     * @param matrixToRotate the matrix that will be rotated
     * @param clockwise true for clockwise, false for anticlockwise
     * @return the rotated matrix
     */
    public static char[][] rotateMatrix(char[][] matrixToRotate, boolean clockwise){
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

    /**
     * Loops through the matrix of the currently falling piece to return the king of piece it is
     * @return the kind of piece the currently falling piece is
     */
    private char getKindOfPent()
    {
        for(int i = 0; i < fallingPentMatrix.length; i++)
        {
            if(fallingPentMatrix[i][0] != 'o')
            {
                return fallingPentMatrix[i][0];
            }
        }
        return 'o';
    }

}
