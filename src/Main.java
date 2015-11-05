import javax.swing.JFrame;

public class Main{
	public static void main(String[] argv){
		Board board = new Board(15,10);
		InputController in = new InputController();
		JFrame gui = new GUI(in,board);
	}
}