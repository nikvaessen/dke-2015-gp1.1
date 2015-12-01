import gui.*;
import tetris.HighScoreList;
import tetris.Pentomino;

import java.util.ArrayList;

public class Main {
	public static void main(String[] argv) {
		new MainMenu();
	}

    public static void testHighScoreList()
    {
        HighScoreList hs = new HighScoreList();
        hs.printScores();
    }

}