/**
 * This class has methods which can operate on a given board which are needed to play a tetris game
 * Created by Nik on 7-11-15.
 */
public class BoardHandler
{
    //classes needed so BoardHandler can function
    private Board board;                //holds the Board class to operate on
    private Pentominoes pentominoes;    //holds all pentominoes which can be put on the board
    private InputController input;      //holds the InputController for player to give movement commands

    //variables needed to keep track of falling pieces
    private char pieceFallingDown;      //holds the kind of piece currently falling down in the game
    private char nextPieceFallingDown;  //holds the next kind of piece set to be falling down
    private char[] possiblePieces;      //holds a list of all 12 chars which can be represented by a pentomino
    private int[] centerXYFallingPiece; //Holds the coordinates of the top center block representing a pentomino piece which
                                        /// is currently 'falling' in the form {x,y}
    private char[][] matrixOfFallingPiece; //holds the matrix of the piece currently falling

    //variables needed to store the size of board so loops don't have to get the size every time
    private int boardWidth;     //holds the amount of columns the board has
    private int boardHeight;    //holds the amount of rows the board has

    //variables needed to restrict movement input
    boolean movedPiece; //used as safeguard which restricts movement so it can only happen once before the piece has to
                        /// fall down


    /**
     * Creates a BoardHandler class which can operate on a board class to play tetris on the Board
     * @param board The board tetris is going to be played on
     */
    public BoardHandler(Board board, InputController input)
    {
        //Create classes needed so BoardHandler can function.
        this.board = board;
        this.input = input;
        pentominoes = new Pentominoes();

        //create the variables needed to keep track of falling pieces.
        //pieceFallingDown will get declared as soon as the first piece is put on the board.
        nextPieceFallingDown = getRandomPieceChar();
        possiblePieces = pentominoes.getKeysAsArray();
        centerXYFallingPiece = new int[2]; //values will be given as soon as the first piece is put on the board.

        //sets the height and width of the board.
        boardWidth = board.getWidth();
        boardHeight = board.getHeight();
    }

    /**
     * Puts the pentomino piece to be placed down in the top center of the board and randomly determines the next one.
     */
    public void newPieceOnBoard()
    {
        //just testing, does not do what the documentation says it does
        //determine pieces
        pieceFallingDown = nextPieceFallingDown;
        nextPieceFallingDown = getRandomPieceChar();
        //place pieces and set coords correctly
        board.placePiece(pentominoes.getMatrix(pieceFallingDown,0), 0, 5);
        setCoordsFallingPiece();
        matrixOfFallingPiece = pentominoes.getMatrix(pieceFallingDown,0);
    }


    /**
     * Drops the piece falling down by 1 row. If the piece comes in contact with the bottom of the board or another
     * stationary piece, a new piece gets put on the top of the board. Sets movedPiece to false so movement can happen
     * again
     */
    public void dropFallingPiece()
    {

    }

    /**
     * makes the class make a call to the InputController to see if the player has given new input.
     */
    public void lookForMovementCommands()
    {
        if(!input.hasNewInput()){
            System.out.println("No new movement");
            return;
        }
        char command = input.getCurrentInput();
        System.out.printf("got this new command: %c%n", command);
        if(command == 'd')
        {
            movePieceCompletelyDown();
        }
        else if(command == 'l')
        {
            movePieceLeft();
        }

        else if(command == 'r')
        {
            movePieceRight();
        }
    }

    /**
     *  Move the piece currently falling down the tetris board to the left
     */
    private void movePieceLeft()
    {  board.remove(pieceFallingDown);
        //first check if it is doable
        int newX = getXFallingPiece() - 1;
        int newY = getYFallingPiece();
        if(!board.canPlace(matrixOfFallingPiece, newX, newY)) {
            board.placePiece(matrixOfFallingPiece, getXFallingPiece(), getYFallingPiece());
            System.out.println("not allowed to move!");
            return;
        }
        //and then replace it in the new position
        board.placePiece(matrixOfFallingPiece,newX,newY);
    }
    /**
     *  Move the piece currently falling down the tetris board to the right
     */
    private void movePieceRight()
    {
        board.remove(pieceFallingDown);
        //first check if it is doable
        int newX = getXFallingPiece() + 1;
        int newY = getYFallingPiece();
        if(!board.canPlace(matrixOfFallingPiece, newX, newY)) {
            board.placePiece(matrixOfFallingPiece, getXFallingPiece(), getYFallingPiece());
            System.out.println("not allowed to move!");
            return;
        }
        //and then replace it in the new position
        board.placePiece(matrixOfFallingPiece,newX,newY);
    }
    /**
     *  Move the piece currently falling down the tetris board up. Not allowed to be called in actual game, just
     *  testing purposes
     */
    private void movePieceCompletelyDown()
    {
        board.remove(pieceFallingDown);
        //first check if it is doable
        int newX = getXFallingPiece();
        int newY = getYFallingPiece() + 1;
        if(!board.canPlace(matrixOfFallingPiece, newX, newY)) {
            board.placePiece(matrixOfFallingPiece, getXFallingPiece(), getYFallingPiece());
            System.out.println("not allowed to move!");
            return;
        }
        //and then replace it in the new position
        board.placePiece(matrixOfFallingPiece,newX,newY);
    }

    /**
     * Randomly gives a char representing a pentomino piece. Makes sure a piece never drawn twice in a row.
     * @return a char which represents one of the 12 pentomino pieces
     */
    private char getRandomPieceChar()
    {
        //just testing, does not do what the documentation says it does
        return 'l';
    }

    /**
     * goes through the 'drop zone' of the pieces and determines the initial coordinates. Gets called immediately after
     * a new piece is put down
     * @return
     */
    private void setCoordsFallingPiece()
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < boardWidth; j++)
            {
                if(board.getCell(i,j) == pieceFallingDown)
                {
                    centerXYFallingPiece[0] = i;
                    centerXYFallingPiece[1] = j;
                    return;
                }
            }
        }
    }

    /**
     * gets the x coordinate of the asked block. goes from 0 to 4
     * @param whichBlock the block the x coords needed to be returned
     * @return the x coordinate of the block
     */
    private int getXFallingPiece()
    {
        return centerXYFallingPiece[0];
    }

    /**
     * gets the y coordinate of the asked block. goes from 0 to 4
     * @param whichBlock the block the y coords needed to be returned
     * @return the y coordinate of the block
     */
    private int getYFallingPiece()
    {
        return centerXYFallingPiece[1];
    }
}
