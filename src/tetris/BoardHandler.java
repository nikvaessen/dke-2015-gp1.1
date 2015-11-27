package tetris;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Arrays;

/**
 * Created by Nik on 11/12/15.
 */
public class BoardHandler {

    private Board board;
    private Pentominoes pentominoes;
    private InputController input;
    private Random rng;

    private char[][] fallingPentMatrix; //the matrix of the currently falling
    private int[][] coordsFallingPent;  //coordinates of the pentomino current falling in the form of
                                        // { {x1,..x5},{y1,..,y5} }
    private char kindOfPent; // letter corresponding to the shape of the pentomino

    private boolean needNewPiece;

    public BoardHandler(Board board, InputController input)
    {
        this.board = board;
        this.input = input;
        pentominoes = new Pentominoes();
        rng = new Random();
    }

    /**
     * Spawns a random pentomino in the top row(s) of the board.
     */
    public void spawnPiece()
    {
        fallingPentMatrix = getRandomPentomino();
        board.placePiece(fallingPentMatrix, 0, (board.getWidth()/2) -1);
        coordsFallingPent = getInitialCoordinates();
        needNewPiece = false;
    }

    /**
     * returns a random matrix of a pentomino
     * @return
     */
    private char[][] getRandomPentomino()
    {
        char[] everyPentomiChar = pentominoes.getCharForTheList();
        int randomNumber = rng.nextInt(everyPentomiChar.length);
        kindOfPent = everyPentomiChar[randomNumber];
        char[][] toReturn = pentominoes.getMatrix(everyPentomiChar[randomNumber], 0);
        if(rng.nextBoolean())
        {
           // toReturn = flipMatrix(toReturn);
        }
        return toReturn;
    }

    //TODO FINISH THE FLIPPING :D
    /**
     *
     */
//    private char[][] flipMatrix(char[][] matrixToFlip)
//    {
//        char[][] flippedMatrix =
//
//        return matrixToFlip;
//    }

    private int[][] getInitialCoordinates()
    {
        int [][] toReturn = new int[2][5];
        int count = 0;
        for(int column = 0; column < board.getWidth(); column++)
        {
            for(int row = 0; row < 5; row++ )
            {
                if(board.getCell(row, column) != 'o')
                {
                    toReturn[0][count] = row;
                    toReturn[1][count] = column;
                    count++;
                }
            }
        }
        if(count != 5)
        {
            System.out.print("Something has gone horrible wrong with the initial coordinates");
        }
        return toReturn;
    }


    public void giveInput(char input)
    {
        System.out.println("The current coords of the falling piece:");
        System.out.println(Arrays.deepToString(coordsFallingPent));
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
        char[][] rotatedMatrix = fallingPentMatrix;
        if(input == 'z'){
            rotatedMatrix = rotateMatrixAntiClockwise(fallingPentMatrix);
        }
        else if(input == 'x')
        {
            rotatedMatrix = rotateMatrixClockwise(fallingPentMatrix);
        }
        for (int column = 0; column < 5; column++) {
                board.setCell(coordsFallingPent[0][column], coordsFallingPent[1][column], 'o');
         }

        coordsFallingPent = board.placePiece(rotatedMatrix,coordsFallingPent[0][0],coordsFallingPent[1][0]);
        fallingPentMatrix = rotatedMatrix;


//        System.out.print("Trying to rotate piece\n");
//        int[][] newCoords = getCoordsForAntiClockwiseRotation();
//        char[][] newPentMatrix = rotateMatrixAntiClockwise(fallingPentMatrix);
//
////        System.out.print("new pentomini matrix:\n");
////        System.out.println(Arrays.deepToString(newPentMatrix));
////        System.out.print("new coordinates matrix:\n");
////        System.out.println(Arrays.deepToString(newCoords));
//
//        for (int column = 0; column < 5; column++) {
//            board.setCell(coordsFallingPent[0][column], coordsFallingPent[1][column], 'o');
//        }
//        coordsFallingPent = newCoords;
//        fallingPentMatrix = newPentMatrix;
//        if(board.canPlace(newCoords)){
//            try {
//
//                for (int column = 0; column < 5; column++) {
//                    board.setCell(newCoords[0][column], newCoords[1][column], kindOfPent);
//
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("the new coordinates are still out of bounds");
//            }
//            coordsFallingPent = newCoords;
//        }
//        else{
//            for (int column = 0; column < 5; column++) {
//                board.setCell(coordsFallingPent[0][column], coordsFallingPent[1][column], kindOfPent);
//            }
//        }
    }

    //direction can be 'l' for left, 'r' for roght and 'd' for down
    private void movePentominoTo(char direction) {
        int rowTranslation = 0;
        int columnTranslation = 0;
        if (direction == 'l') {
            columnTranslation = -1;
        } else if (direction == 'r') {
            columnTranslation = 1;
        } else if (direction == 'd') {
            rowTranslation = 1;
        }

        int[][] newCoords = new int[2][5];
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 5; column++) {
                //System.out.printf("row: %d column: %d\n", row, column);
                if (row == 0)
                    newCoords[row][column] = coordsFallingPent[row][column] + rowTranslation;
                else
                    newCoords[row][column] = coordsFallingPent[row][column] + columnTranslation;
            }

        }
        for (int column = 0; column < 5; column++) {
            board.setCell(coordsFallingPent[0][column], coordsFallingPent[1][column], 'o');
        }
        if(board.canPlace(newCoords)){
            try {

                for (int column = 0; column < 5; column++) {
                    board.setCell(newCoords[0][column], newCoords[1][column], kindOfPent);

                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("the new coordinates are still out of bounds");
            }
            coordsFallingPent = newCoords;
        }
        else{
            for (int column = 0; column < 5; column++) {
                board.setCell(coordsFallingPent[0][column], coordsFallingPent[1][column], kindOfPent);
            }
            if(direction == 'd')
            {
                needNewPiece = true;
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
        //System.out.println("Checking for full rows");
        for(int row = 0; row < board.getHeight(); row++)
        {
            boolean foundEmptyCell = false;
            for(int column= 0; column < board.getWidth(); column++)
            {
                //System.out.print(board.getCell(row,column) + " ");
                if (board.getCell(row, column) == 'o' && !foundEmptyCell)
                {
                    foundEmptyCell = true;
                    //break;
                }
            }
            //System.out.printf("%nrow %d is full: %b%n", row, !foundEmptyCell);
            if(!foundEmptyCell){
                System.out.println("Clearing row " + row);
                clearLine(row);
            }
        }
    }

   public void clearLine(int row1)
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


    public int[][] getCoordsForAntiClockwiseRotation()
    {
        char[][] matrix=fallingPentMatrix;
        int[][] matrix2=coordsFallingPent;
        int i,j;

        System.out.print("The matrix of the piece currently falling:\n");
        System.out.println(Arrays.deepToString(matrix));

        //calculate and store the rotated version of the current falling pentomino piece
        char[][] rotation= rotateMatrixAntiClockwise(fallingPentMatrix);
        System.out.print("The anti-clockwise rotated version of the piece falling:\n");
        System.out.println(Arrays.deepToString(rotation));

        //calculates and stores the coordinates of the pieces in the pre-rotated matrix
        int[][] pentMatrixCoords= new int[2][5];
        int k=0;

        for(i=0; i<matrix.length; i++)
            for(j=0; j<matrix[0].length; j++)
                if(matrix[i][j]!='o')
                {
                    pentMatrixCoords[0][k]=i;
                    pentMatrixCoords[1][k]=j;
                    k++;
                }
        System.out.print("The matrix coordinates of the piece currently falling\n");
        System.out.println(Arrays.deepToString(pentMatrixCoords));

        //calculates and stores the coordinates of the pieces in the rotated matrix
        int[][] rotatedPentMatrixCoords= new int[2][5];
        k=0;

        for(i=0; i<rotation.length; i++)
            for(j=0; j<rotation[0].length; j++)
                if(rotation[i][j]!='o')
                {
                    rotatedPentMatrixCoords[0][k]=i;
                    rotatedPentMatrixCoords[1][k]=j;
                    k++;
                }
        System.out.print("The matrix coordinates of the anti-clockwise rotation of the piece falling\n");
        System.out.println(Arrays.deepToString(rotatedPentMatrixCoords));

        //calculates and stores the difference between the coordinates of the pre-rotated and rotated matrix
        //to apply to the current coordinates.
        int[][] differentialCoords= new int[2][5];

        for(i=0;i<differentialCoords.length; i++)
            for(j=0;j<differentialCoords[0].length; j++)
                differentialCoords[i][j]= pentMatrixCoords[i][j] - rotatedPentMatrixCoords[i][j];


        System.out.print("The coordinates of the piece currently falling\n");
        System.out.println(Arrays.deepToString(matrix2));
        System.out.print("The differential matrix between the original and anti-clockwise rotated matrix:\n");
        System.out.println(Arrays.deepToString(differentialCoords));

        //calculates and returns the new coordinates to rotate the current pentomino
        int[][] newCoords =new int[2][5];

        for( i=0; i< matrix2.length; i++)
            for( j=0; j<matrix2[0].length; j++)
                newCoords[i][j]= matrix2[i][j] - differentialCoords[i][j];

        System.out.print("The coordinates to rotate the matrix anti clockwise: \n");
        System.out.println(Arrays.deepToString(newCoords));

        return newCoords;
    }

    public int[][] getCoordsForClockwiseRotation()
    {
        char[][] matrix=fallingPentMatrix;
        int[][] matrix2=coordsFallingPent;
        int i,j;

        //calculate and store the rotated version of the current falling pentomino piece
        char[][] rotation= rotateMatrixClockwise(fallingPentMatrix);

        //calculates and stores the coordinates of the pieces in the pre-rotated matrix
        int[][] pentMatrixCoords= new int[2][5];
        int k=0;

        for(i=0; i<matrix.length; i++)
            for(j=0; j<matrix[0].length; j++)
                if(matrix[i][j]!='o')
                {
                    pentMatrixCoords[0][k]=i;
                    pentMatrixCoords[1][k]=j;
                    k++;
                }
        System.out.println("old coords: ");
        System.out.println(Arrays.deepToString(pentMatrixCoords));

        //calculates and stores the coordinates of the pieces in the rotated matrix
        int[][] rotatedPentMatrixCoords= new int[2][5];
        k=0;

        for(i=0; i<rotation.length; i++)
            for(j=0; j<rotation[0].length; j++)
                if(rotation[i][j]!='o')
                {
                    rotatedPentMatrixCoords[0][k]=i;
                    rotatedPentMatrixCoords[1][k]=j;
                    k++;
                }

        //calculates and stores the difference between the coordinates of the pre-rotated and rotated matrix
        //to apply to the current coordinates.
        int[][] differentialCoords= new int[2][5];

        for(i=0;i<differentialCoords.length; i++)
            for(j=0;j<differentialCoords[0].length; j++)
                differentialCoords[i][j]= pentMatrixCoords[i][j] - rotatedPentMatrixCoords[i][j];

        //calculates and returns the new coordinates to rotate the current pentomino
        int[][] newCoords =new int[2][5];

        for( i=0; i< matrix2.length; i++)
            for( j=0; j<matrix2[0].length; j++)
                newCoords[i][j]= matrix2[i][j] - differentialCoords[i][j];

        return newCoords;
    }

    private char[][] rotateMatrixClockwise(char[][] matrixToRotate){
        char[][] rotation= new char[matrixToRotate[0].length][matrixToRotate.length];
        char[] first_column=new char[rotation[0].length];

        int i,j;
        for(i=0;i<matrixToRotate.length;i++)
            for(j=0;j<matrixToRotate[0].length;j++)
                rotation[j][i]=matrixToRotate[i][j];

        for(i=0; i< rotation.length; i++)
            first_column[i]=rotation[i][0];

        for(i=0;i<rotation.length; i++)
            rotation[i][0]= rotation[i][rotation[0].length-1];

        for(i=0;i<rotation.length; i++)
            rotation[i][rotation[0].length-1]=first_column[i];

        return rotation;
    }

    private char[][] rotateMatrixAntiClockwise(char[][] matrixToRotate){
        char[][] rotation= new char[matrixToRotate[0].length][matrixToRotate.length];
        char[] first_row=new char[rotation[0].length];

        int i,j;
        for(i=0;i<matrixToRotate.length;i++)
            for(j=0;j<matrixToRotate[0].length;j++)
                rotation[j][i]=matrixToRotate[i][j];

        for(i=0; i< rotation[0].length; i++)
            first_row[i]=rotation[0][i];

        for(i=0;i<rotation[0].length; i++)
            rotation[0][i]= rotation[rotation.length-1][i];

        for(i=0;i<rotation[0].length; i++)
            rotation[rotation.length-1][i]=first_row[i];

        return rotation;
    }

}
