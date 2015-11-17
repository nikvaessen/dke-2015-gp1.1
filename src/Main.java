import gui.*;
import tetris.Board;

import javax.swing.*;

public class Main {
	public static void main(String[] argv){
		new MainMenu();
		//testFlip();
	}

	public static void testFlip()
	{
		char[][] matrix =
		{
			{'l', 'o'},
			{'l', 'o'},
			{'l', 'o'},
			{'l', 'o'},
			{'l', 'l'}
		};
		printMatrix(matrix);
		char[][] flippedMatrix = flip(matrix);
		printMatrix(flippedMatrix);

	}

	private static char[][] flip(char[][] originalMatrix) {
		char[][] flipped = new char[originalMatrix[0].length][originalMatrix.length];
		for (int row=0; row<originalMatrix[0].length; row++) {
			for (int col=0; col<originalMatrix.length; col++) {
				flipped[row][col] = originalMatrix[col][row];
			}
		}

		return flipped;
	}

	private static void printMatrix(char[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j]);
			System.out.println();
		}
	}
}