import java.util.ArrayList;

/**
 * This class can be used to represent a single form of a pentomino. It can calculate every kind of rotation for the
 * single given piece. 
 * @author 
 *
 */
public class Pentomino {
	//instance variables
	private char name;						//holds the kind of letter the pentomino stored within represents
	private ArrayList<char[][]> matrixes; 	//stores matrixes of all possible rotations of the pentomino
	private boolean[] usedRotations;
	
	/**
	 * Constructs the pentomino for a given kind of pentomino, e.g x or p, and a matrix of chars representing a single form
	 * of the given kind or pentomino, where 'o' is an empty cell and 'char' for a non-empty cell. 
	 * @param name the character this class has to represent
	 * @param matrix a single form of the matrix this class has to represent
	 */
	public Pentomino(char name, char[][] matrix) {
		this.name = name;
		this.matrixes = new ArrayList<char[][]>();
		this.matrixes.add(matrix);
		calculateRotations();
		removeEqual();
		this.usedRotations = new boolean[matrixes.size()];
	}
	
	
	/**
	 * Gives the amount of non duplicate matrixes stored 
	 * @return the amount of non duplicate matrixes stored as an integer
	 */
	public int getAmountOfMatrixes(){
		return this.matrixes.size();
	}
	
	/**
	 * gives a matrix of a single form of the represented pentomino
	 * @param number the form you want to be given, ranging from 0 for the first stored to the amount of forms stored. 
	 * @return a matrix
	 */
	public char[][] getMatrix(int number) {
		return this.matrixes.get(number);
	}
	
	/**
	 * returns the kind of pentomino the object is representing
	 * @return the kind of pentomino represented
	 */
	public char getName() {
		return this.name;
	}
	
	/**
	 * Sets a value for one particular rotation in a boolean array, which represents all the used pentominoes and their rotations.
	 * @param i index of the rotation in the boolean array
	 * @param used value set for the rotation
	 */
	public void setUsed(int i, boolean used){
		this.usedRotations[i] = used;
	}
	
	/**
	 * Sets all of the values in the boolean array, which represents all the used pentominoes and their rotations.
	 * @param used value that is set for all pentominoes in the boolean array.
	 */
	public void setUsedForAll(boolean used){
		for(int i= 0; i < usedRotations.length;i++){
			usedRotations[i] = used;
		}
	}
	
	/**
	 * Returns a boolean array, which represents all the used pentominoes and their rotations.
	 * @param rotation number of the rotation
	 * @return boolean array, which represents all the used pentominoes and their rotations.
	 */
	public boolean isUsed(int rotation){
		return this.usedRotations[rotation];
	}
	
	/**
	 * Overwrites toString to return all matrixes in string form in format matrix1 \n matrix2 \n etc.
	 */
	public String toString() {
		String result = "";
		
		for (char[][] matrix : matrixes) {
			for (int i=0; i<matrix.length; i++) {
				for (int j=0; j<matrix[i].length; j++) {
					result += matrix[i][j];
				}
				result += "\n";
			}
			result += "\n";
		}
		return result;
	}
	
	/**
	 * This function will take the single matrix given in the constructor to calculate every possible
	 * non-duplicate rotation.
	 */
	private void calculateRotations() {
		ArrayList<char[][]> rotations = new ArrayList<char[][]>();
		boolean flipped = false;
		for (int k=0; k<7; k++) {
			char[][] originalMatrix;
			if (k==0) {
				originalMatrix = this.matrixes.get(0);
			} else {
				originalMatrix = rotations.get(rotations.size()-1);
			}
			if (flipped) {				
				rotations.add(rotate(originalMatrix));
				flipped = false;
			} else {
				rotations.add(flip(originalMatrix));
				flipped = true;
			}
		} 
		for(char[][] matrix : rotations){
			this.matrixes.add(matrix);
		}
	}
	
	/**
	 * Flips a given matrix of size x*y to size y*x
	 * @param originalMatrix the matrix which has to be flipped
	 * @return the flipped version of the given matrix
	 */
	private char[][] flip(char[][] originalMatrix) {
		char[][] flipped = new char[originalMatrix[0].length][originalMatrix.length];
		for (int row=0; row<originalMatrix[0].length; row++) {
			for (int col=0; col<originalMatrix.length; col++) {
				flipped[row][col] = originalMatrix[col][row];
			}
		}
		
		return flipped;
	}
	
	/**
	 * Flips the columns of a given matrix of width x, where column 1 will become column x, column 2 will become x-1, ect
	 * @param originalMatrix the matrix whose columns will be flipped
	 * @return the matrix with flipped columns
	 */
	private char[][] rotate(char[][] originalMatrix) {
		char[][] rotated = new char[originalMatrix.length][originalMatrix[0].length];
		for (int i=0; i<originalMatrix.length; i++) {
			for(int j=0; j<originalMatrix[i].length; j++) {
				rotated[i][j] = originalMatrix[(originalMatrix.length-1)-i][j];
			}
		}
		return rotated;
	}
	

	/**
	 * Goes through the created rotations in the calcutateRotations() method to remove duplicates.
	 */
	private void removeEqual() {
		ArrayList<char[][]> rotations = this.matrixes;
		for (int i=0; i<rotations.size(); i++) {
			char[][] matrix1 = rotations.get(i);
			boolean removedSth = false;
			for (int j=0; j<rotations.size(); j++) {
				if (i==j) {
					continue;
				} else {
					char[][] matrix2 = rotations.get(j);
					if (areEven(matrix1, matrix2)) {
						rotations.remove(j);
						removedSth = true;
					}
				}
			}
			if (removedSth) {
				i=(-1);
			}
			
		}
	}
	
	/**
	 * Compares 2 matrixes for equality
	 * @param matrix1 matrix you want to compare
	 * @param matrix2 matrix you want to compare
	 * @return true if matrixes are equal, false if matrixes are not equal.
	 */
	private boolean areEven(char[][] matrix1, char[][] matrix2) {
		if (matrix1.length!=matrix2.length || matrix1[0].length!=matrix2[0].length) {
			return false;
		} else {
			int cntr = 0;
			for (int i=0; i<matrix1.length; i++) {
				for (int j=0; j<matrix1[0].length; j++) {
					if (matrix1[i][j]==matrix2[i][j]) {
						cntr++;
					}
				}
			}
			int cells = matrix1.length*matrix1[0].length;
			if (cntr==cells) {
				return true;
			} else {
				return false;
			}
		}
	}
}