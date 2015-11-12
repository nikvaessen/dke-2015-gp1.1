import gui.*;
import tetris.Board;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] argv){
		Board board = new Board(15,10);
		InputController in = new InputController();
		GameWindow gameWindow = new GameWindow(in, board);
		JFrame windowHolder = new WindowHolder(new MenuWindow(), new OptionsWindow(), gameWindow);
	}
}