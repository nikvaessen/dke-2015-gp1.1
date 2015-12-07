import gui.*;
import tetris.Board;
import tetris.BoardHandler;
import tetris.HighScoreList;
import tetris.Pentomino;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] argv) {
		new MainMenu();
	    //testBotMethod();
    }

    public static void testHighScoreList()
    {
        HighScoreList hs = new HighScoreList();
        hs.printScores();
    }

    public static void testBotMethod()
    {
        Board board = new Board(10, 15);
        board.setCell(14, 7, 't');
        board.setCell(14, 8, 't');
        board.setCell(14, 9, 't');
        board.setCell(13, 8, 't');
        board.printBoard();
        char[][] matrix = {{'z', 'z', 'o'},{'o','z','z'}};
        int[] results = getRecommendedPositionAndRotation(board.getBoard(), matrix);
        board.placePiece(BoardHandler.rotateMatrix(matrix, true), results[0], results[1]);
        board.printBoard();
        System.out.println(Arrays.toString(results));
    }

    private static int[] getRecommendedPositionAndRotation(char[][] board, char[][] piece)
    {
        System.out.println("Hello boss");
        Board testBoard = new Board(0,0);
        testBoard.setBoard(board);
        ArrayList<char[][]> possiblePieces = new ArrayList<>();
        possiblePieces.add(piece);
        possiblePieces.add(BoardHandler.rotateMatrix(piece, true));
        possiblePieces.add(BoardHandler.rotateMatrix(possiblePieces.get(1), true));
        possiblePieces.add(BoardHandler.rotateMatrix(possiblePieces.get(2), true));

        ArrayList <Integer> xCoords= new ArrayList<>();
        ArrayList <Integer> yCoords= new ArrayList<>();
        int highestRow = board.length - 1;
        for(int i=board.length-1; i>=0; i--)
        {
            for (int j = board[0].length-1; j >= 0; j--)
            {
                if(board[i][j] != 'o')
                {
                    highestRow = i;
                    System.out.println("Highest row: " + highestRow);
                    break;
                }
            }
        }
        //fill the array with all the coords to check
        for(int i = highestRow - 2; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                xCoords.add(i);
                System.out.println("Row: " + i + " Column: " + j);
                yCoords.add(j);
            }
        }
        //at every coord, try to place all rotations of the matrix and calculate the "snug" level
        int bestX = 0;
        int bestY = 0;
        int bestR = 0;
        int bestScore = 0;
        for(int i=0; i<xCoords.size(); i++)
        {
            int x = xCoords.get(i);
            int y = yCoords.get(i);
            System.out.println("Row: " + x + " Column: " + y );
            for(int rot = 0; rot < possiblePieces.size(); rot++)
            {
                if(testBoard.canPlace(possiblePieces.get(rot), x, y))
                {
                    testBoard.placePiece(possiblePieces.get(rot), x, y);
                    System.out.println(Arrays.deepToString(possiblePieces.get(rot)));
                    int tempScore = testBoard.getScore(possiblePieces.get(rot), x , y);
                    System.out.println("Score: " + tempScore);
                    testBoard.removePiece(possiblePieces.get(rot), x , y);
                    if(tempScore > bestScore){
                        bestX = x;
                        bestY = y;
                        bestR = rot;
                        bestScore=tempScore;
                        System.out.println("Max score: " + bestScore);

                    }
                }
            }
        }

        return new int[] {bestX, bestY, bestR};
    }


}