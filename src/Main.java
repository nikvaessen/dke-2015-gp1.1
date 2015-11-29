import gui.*;
import tetris.Board;
import tetris.Pentomino;
import tetris.Pentominoes;
import tetris.ScoreBoard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] argv) {
		//testCoords();
		//testRotations();
		new MainMenu();
	}

	public static void testRotations()
	{
		Pentominoes p = new Pentominoes();
		ArrayList<char[][]> originalMatrixes = new ArrayList<char[][]>();
		for(char key: p.getKeys())
		{
			originalMatrixes.add(p.getMatrix(key, 0));
		}
		for(char[][] matrix: originalMatrixes) {
			System.out.println("Original matrix: ");
			printMatrix(matrix);
			char[][] rotation1 = rotateMatrix(matrix,    true);
			char[][] rotation2 = rotateMatrix(rotation1, true);
			char[][] rotation3 = rotateMatrix(rotation2, true);
			char[][] rotation4 = rotateMatrix(rotation3, true);
			System.out.println("rotation 1:");
			printMatrix(rotation1);
			System.out.println("rotation 2:");
			printMatrix(rotation2);
			System.out.println("rotation 3:");
			printMatrix(rotation3);
			System.out.println("rotation 4:");
			printMatrix(rotation4);
			System.out.print("\n");
		}
	}

	public static void printMatrix(char[][] matrix)
	{
		for(int i=0; i<matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}

	}

	public static void printMatrix(int[][] matrix)
	{
		for(int i=0; i<matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}

	}

	private static char[][] rotateMatrix(char[][] matrixToRotate, boolean clockwise){

		//char[] firstColumn =new char[rotation[0].length];

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