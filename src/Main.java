import javax.swing.JFrame;

public class Main{
	public static void main(String[] argv){
		Board board = new Board(15,10);
		InputController in = new InputController();
        BoardHandler bh = new BoardHandler(board, in);
		JFrame gui = new GUI(in,board);
        System.out.println("created board");

        bh.newPieceOnBoard();
        while(true){
            try {
                Thread.sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            bh.lookForMovementCommands();
            board.printBoard();
            gui.repaint();
        }
	}
}