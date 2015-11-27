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


	public static void testCoords()
	{
		Pentominoes p = new Pentominoes();
		char[][] fMatrix = p.getMatrix('f',0);
		int[][] coords = { 	{2,2,3,3,4},
							{8,9,7,8,8} };
		int[][] newcoords = getCoordsForAntiClockwiseRotation(fMatrix, coords);
		char[][] newFMatrix = rotateMatrixAntiClockwise(fMatrix);
		System.out.print("original coords: \n");
		printMatrix(coords);
		System.out.print("new coords for : \n");
		printMatrix(newcoords);
		int[][] newcoords1 = getCoordsForAntiClockwiseRotation(newFMatrix, newcoords);
		char[][] newFMatrix1 = rotateMatrixAntiClockwise(newFMatrix);
		System.out.print("original coords: \n");
		printMatrix(newcoords);
		System.out.print("new coords for : \n");
		printMatrix(newcoords1);
		int[][] newcoords2 = getCoordsForAntiClockwiseRotation(newFMatrix1, newcoords1);
		char[][] newFMatrix2 = rotateMatrixAntiClockwise(newFMatrix1);
		System.out.print("original coords: \n");
		printMatrix(newcoords1);
		System.out.print("new coords for : \n");
		printMatrix(newcoords2);
		int[][] newcoords3 = getCoordsForAntiClockwiseRotation(newFMatrix2, newcoords2);
		char[][] newFMatrix3 = rotateMatrixAntiClockwise(newFMatrix2);
		System.out.print("original coords: \n");
		printMatrix(newcoords2);
		System.out.print("new coords for : \n");
		printMatrix(newcoords3);

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
			char[][] rotation1 = rotateMatrixAntiClockwise(matrix);
			char[][] rotation2 = rotateMatrixAntiClockwise(rotation1);
			char[][] rotation3 = rotateMatrixAntiClockwise(rotation2);
			char[][] rotation4 = rotateMatrixAntiClockwise(rotation3);
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


	public static char[][] rotateMatrixAntiClockwise(char[][] matrixToRotate){
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

	public static int[][] getCoordsForAntiClockwiseRotation(char[][] fallingPentMatrix, int[][] coordsFallingPent)
	{
		char[][] matrix=fallingPentMatrix;
		int[][] matrix2=coordsFallingPent;
		int i,j;

		//calculate and store the rotated version of the current falling pentomino piece
		char[][] rotation= rotateMatrixAntiClockwise(fallingPentMatrix);

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
		//System.out.println(Arrays.deepToString(pentMatrixCoords));

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

}