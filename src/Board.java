/**
 * Class is used to create an empty board, ready to be filled with pentominoes. Char 'o' represents an empty cell.
 * @author 
 *
 */
public class Board {
	//instance variables
	private char[][] board; 	//Stores the board state. 
	
	private int width;
	private int height;
	
	/**
	 * Creates a board of given height and width
	 * @param height height of the board
	 * @param width width of the board
	 */
	public Board(int height, int width)
	{
		this.width = width;
		this.height = height;
		
		this.board = new char[height][width];
		// Fill all the board with o's (empty fields).
		for( int i = 0 ; i < board.length ; i++)
		{
			for( int j = 0 ; j < board[0].length ; j++) 
			{
				board[i][j] = 'o';				
			} 
		}
	}
	
	/**
	 * Prints the board.
	 */
	public void printBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[0].length;j++)
			System.out.print(board[i][j] + " ");
		System.out.println();
		}	
		System.out.println();
	}
	
	/**
	 * Sets the board to a given matrix of chars.
	 * @param newboard the given matrix of chars
	 */
	public void setBoard(char[][] newboard){
		this.board = newboard;
	}
	
	/**
	 * Gets the board of a given matrix of chars.
	 * @return board of a given matrix of chars.
	 */
	public char[][] getBoard(){
		return this.board;
	}
	
	
	/**
	 * Returns the coordinates (-1, -1) if board is completely full.
	 * @return the coordinates (-1, -1)
	 */
	public int[] firstEmptyCell(){
		for(int i = 0; i < board.length;i++){
			for(int j = 0; j < board[i].length;j++){
				if(isCellEmpty(i, j)){
					int[] coord = {i,j};
					return coord;
				}
			}
		}
		int[] coords = {-1,-1};
		return coords;
	}
	
	/**
	 * Counts the amount of empty cells.
	 * @return the amount of empty cells
	 */
	public int emptyCells(){
		int counter = 0;
		for(int i = 0; i < board.length;i++){
			for(int j = 0; j < board[i].length;j++){
				if(isCellEmpty(i, j)){
					counter++;
				}
			}
		}
		return counter;
		
	}
	
	/**
	 * 
	 * @param pentomino
	 */
	public void remove(char pentomino){
		int counter = 0;
		for(int i = 0; i < this.board.length; i++){
			if(counter == 5){
				break;
			}
			for(int j = 0; j < this.board[i].length; j++){
				if(board[i][j] == pentomino){
					board[i][j] = 'o';
					counter++;
				}
			}
		}
	}
	
	/**
	 * Checks if the pentomino can be placed in the cell of the board in its current state. Returns true if it can.
	 * @param pentMatrix matrix which stores the pentomino
	 * @param x coordinate x of the checked cell
	 * @param y coordinate y of the checked cell
	 * @return true if the pentomino can be placed in the cell, false when it cannot
	 */
	public boolean canPlace(char[][] pentMatrix, int x, int y){
		y -= checkPad(pentMatrix);
		//Checks whether the given matrix goes out of bounds of the board. Return false if it does.
		if(x+pentMatrix.length > board.length || y+pentMatrix[0].length > board[0].length || y < 0){
			return false;
		}
		//Checks whether the given matrix will overlap already placed pentominoes. Returns false when it does.
		for(int i = 0; i < pentMatrix.length;i++){
			for(int j = 0; j < pentMatrix[i].length;j++){
				if(!isCellEmpty(x+i,y+j)){
					if(pentMatrix[i][j] != 'o'){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Places the pentomino, starting with the first char of the pentomino being placed in the chosen cell.
	 * @param pentMatrix matrix which stores the pentomino
	 * @param x coordinate x of the cell
	 * @param y coordinate y of the cell
	 */
	public void placePiece(char[][] pentMatrix, int x, int y){
		y -= checkPad(pentMatrix);
		for(int i = 0; i < pentMatrix.length;i++){
			for(int j = 0; j < pentMatrix[i].length;j++){
				if(pentMatrix[i][j] != 'o'){
					setCell(x+i, y+j, pentMatrix[i][j]);						
				}
			}
		}
	}
	
	/**
	 * Checks if the first chars of the pentomino matrix is an empty cell. Returns the amount of empty cells in the top left corner of the matrix.
	 * @param matrix matrix which stores the pentomino
	 * @return the amount of empty cells in the top left corner
	 */
	private int checkPad(char[][] matrix){
		int pad = 0;
		for(int i = 0; i < matrix.length; i++){
			char value = matrix[0][i];
			if(value == 'o'){
				pad++;
			}
			else{
				break;
			}
		}
		return pad;
	}
	
	/**
	 * Sets the char of the pentomino in the cell.
	 * @param i coordinate x of the cell
	 * @param j coordinate y of the cell
	 * @param value char of the pentomino
	 */
	private void setCell(int i, int j, char value){
		board[i][j] = value;
	}


	/**
	 *
	 */
	public char getCell(int x, int y)
	{
		if( x >= this.width || y >= this.height ){
			return ' ';
		}
		return board[y][x];
	}

	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	/**
	 * Checks if the cell is empty. Returns true if it is.
	 * @param x coordinate x of the cell
	 * @param y coordinate y of the cell
	 * @return true if the cell is empty
	 */
	private boolean isCellEmpty(int x,int y){
		if(board[x][y] != 'o'){
			return false;
		}
		return true;
	}
}
