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


    public void giveInput(char input)
    {
        if(input == 'l' || input == 'r'  || input == 'd')
        {
            movePentominoTo(input);
        }
        else if(input == 'z' || input == 'x')
        {
            rotatePentomino(input);
        }

    }


    private  void rotatePentomino(char input)
    {
        System.out.print("Trying to rotate piece\n");
        int[][] newCoords = coordsForRightRotation();

        for (int column = 0; column < 5; column++) {
            board.setCell(coordsFallingPent[0][column], coordsFallingPent[1][column], 'o');
        }
        coordsFallingPent = newCoords;
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

    public  int[][] coordsForRightRotation()
    {
        char[][] matrix=fallingPentMatrix;
        int[][] matrix2=coordsFallingPent;
        char[][] rotation= new char[matrix[0].length][matrix.length];
        int i,j;
        char[] first_column=new char[rotation[0].length];

        //The coordinates of the non-rotated matrix
        int[][] oldCoords= new int[2][5];
        int k=0;

        for(i=0; i<matrix.length; i++)
            for(j=0; j<matrix[0].length; j++)
                if(matrix[i][j]!='o')
                {
                    oldCoords[0][k]=i;
                    oldCoords[1][k]=j;
                    k++;

                }



        for(i=0;i<matrix.length;i++)
            for(j=0;j<matrix[0].length;j++)
                rotation[j][i]=matrix[i][j];

        for(i=0; i< rotation.length; i++)
            first_column[i]=rotation[i][0];

        for(i=0;i<rotation.length; i++)
            rotation[i][0]= rotation[i][rotation[0].length-1];

        for(i=0;i<rotation.length; i++)
            rotation[i][rotation[0].length-1]=first_column[i];

        //The coordinates of the rotated matrix
        int[][] newCoords= new int[2][5];
        k=0;

        for(i=0; i<rotation.length; i++)
            for(j=0; j<rotation[0].length; j++)
                if(rotation[i][j]!='o')
                {
                    newCoords[0][k]=i;
                    newCoords[1][k]=j;
                    k++;

                }



        int[][] coords= new int[2][5];

        for(i=0;i<coords.length; i++)
            for(j=0;j<coords[0].length; j++)
                coords[i][j]= oldCoords[i][j] - newCoords[i][j];



        int[][] newCoords2=new int[2][5];

        for( i=0; i< matrix2.length; i++)
            for( j=0; j<matrix2[0].length; j++)
                newCoords2[i][j]= matrix2[i][j] - coords[i][j];



        return newCoords2;
    }




}
