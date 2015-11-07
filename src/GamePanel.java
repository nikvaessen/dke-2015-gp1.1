import javax.swing.*;
import java.awt.*;

/**
 * Created by Nik on 7/11/15.
 */
public class GamePanel extends JPanel {
    //px*px size of blocks
    public static final int BLOCKSIZE = 50;

    //board to draw
    private Board board;

    /**
     * Constructs a GamePanel JPanel object which displays a grid representing up to 12 different pentomino pieces
     * @param board the board the GamePanel has to display
     */
    public GamePanel(Board board) {
        //adds the board
        this.board = board;
        //set a border around the gameboard
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * The size the gamepanel wants in the frame
     * @return A dimension (x,y) where x is the width of the given board class and y the height of the given board
     * class in the constructor
     */
    public Dimension getPreferredSize() {
        return new Dimension(board.getWidth() * BLOCKSIZE,
                board.getHeight() * BLOCKSIZE);
    }

    /**
     * Draws a grid given by the board class
     * @param g the Graphics class which is going to represent the board
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int columns = board.getWidth();
        int rows = board.getHeight();
        System.out.printf("rows: %d colums: %d %n", columns, rows);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (board.getCell(i, j) == 'p') {
                    g2d.setPaint(new Color(255, 20, 147));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'o') {
                    g2d.setPaint(new Color(192, 206, 212));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'x') {
                    g2d.setColor(new Color(184, 134, 11));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'f') {
                    g2d.setColor(new Color(255, 255, 0));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'v') {
                    g2d.setColor(new Color(221, 160, 221));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'w') {
                    g2d.setColor(new Color(47, 79, 79));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'y') {
                    g2d.setColor(new Color(0, 0, 205));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'i') {
                    g2d.setColor(new Color(0, 191, 255));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 't') {
                    g2d.setColor(new Color(0, 206, 209));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'z') {
                    g2d.setColor(new Color(154, 205, 50));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'u') {
                    g2d.setColor(new Color(0, 255, 0));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'n') {
                    g2d.setColor(new Color(128, 0, 128));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                } else if (board.getCell(i, j) == 'l') {
                    g2d.setColor(new Color(186, 85, 211));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE -1, BLOCKSIZE -1);
                }
            }
        }
    }

}
