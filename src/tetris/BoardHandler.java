package tetris;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.Random;

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

    //direction can be 'l' for left, 'r' for roght and 'd' for down
    public void movePentominoTo(char direction) {
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
                System.out.print(board.getCell(row,column) + " ");
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

    public char[][] rotation_left(char[][] matrix)
    {
        char[][] rotation= new char[matrix[0].length][matrix.length];
        int i,j;
        char[] first_row=new char[rotation[0].length];

        for(i=0;i<matrix.length;i++)
            for(j=0;j<matrix[0].length;j++)
                rotation[j][i]=matrix[i][j];


        for(i=0; i< rotation[0].length; i++)
            first_row[i]=rotation[0][i];

        for(i=0;i<rotation[0].length; i++)
            rotation[0][i]= rotation[rotation.length-1][i];


        for(i=0;i<rotation[0].length; i++)
            rotation[rotation.length-1][i]=first_row[i];

        return rotation;
    }

}
