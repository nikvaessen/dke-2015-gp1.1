import gui.*;
import tetris.Board;
import tetris.ScoreBoard;

import javax.swing.*;

public class Main {
	public static void main(String[] argv) {
		new MainMenu();
		//testHighScores();
	}


	public static void testHighScores(){
		ScoreBoard x = new ScoreBoard();
		x.readScores();
		x.add("Jan", 5);
		x.add("Aleksandra", 5000);
		x.add("Stefan",1100);
		x.add("Adeline", 1500);
		x.add("Jonty", 1050);
		x.printScores();
	}
}