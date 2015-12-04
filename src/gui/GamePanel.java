package gui;

import javax.swing.*;
import java.awt.*;
import tetris.*;

/**
 * Created by Nik on 7/11/15.
 */
public class    GamePanel extends JPanel {
    //px*px size of blocks
    private int blockHeight;
    private int blockWidth;
        
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
        //sets the blocksize to the right size
        blockHeight = Config.GAMEPANEL_SIZE.height / board.getHeight();
        blockWidth  = Config.GAMEPANEL_SIZE.width  / board.getWidth();
        System.out.print(" height= " + Config.GAMEPANEL_SIZE.height / board.getHeight() + "width= " + Config.GAMEPANEL_SIZE.width  / board.getWidth() );
    }

    /**
     * The size the gamepanel wants in the frame
     * @return A dimension (x,y) where x is the width of the given board class and y the height of the given board
     * class in the constructor
     */
    public Dimension getPreferredSize() {
        return new Dimension(Config.GAMEPANEL_SIZE);
    }

    /**
     * Draws a grid given by the board class
     * @param g the Graphics class which is going to represent the board
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int columns = board.getWidth();
        int rows = board.getHeight();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //System.out.printf("i: %d j: %d \t placing at x,y: %d,%3d\t actual width: %d actual height: %d\n",
                //        i,j, i*blocksize, j*blocksize, board.getWidth(), board.getHeight());
                if (board.getCell(i, j) == 'o') {
                    g2d.setPaint(new Color(192, 206, 212));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'p') {
                    g2d.setPaint(new Color(255, 20, 147));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'x') {
                    g2d.setColor(new Color(184, 134, 11));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'f') {
                    g2d.setColor(new Color(255, 255, 0));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'v') {
                    g2d.setColor(new Color(221, 160, 221));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'w') {
                    g2d.setColor(new Color(47, 79, 79));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'y') {
                    g2d.setColor(new Color(0, 0, 205));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'i') {
                    g2d.setColor(new Color(255, 206, 13));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 't') {
                    g2d.setColor(new Color(0, 206, 209));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'z') {
                    g2d.setColor(new Color(154, 205, 50));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'u') {
                    g2d.setColor(new Color(0, 255, 0));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'n') {
                    g2d.setColor(new Color(128, 0, 128));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                } else if (board.getCell(i, j) == 'l') {
                    g2d.setColor(new Color(186, 85, 211));
                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
                }
//                else if (board.getCell(i, j) == 'a') {
//                    g2d.setPaint(new Color(79, 79, 79));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
//                 else if (board.getCell(i, j) == 'b') {
//                    g2d.setPaint(new Color(128, 0, 128));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
//                 else if (board.getCell(i, j) == 'c') {
//                    g2d.setPaint(new Color(0, 191, 255));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
//                 else if (board.getCell(i, j) == 'd') {
//                    g2d.setPaint(new Color(0, 255, 127));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
//                 else if (board.getCell(i, j) == 'e') {
//                    g2d.setPaint(new Color(255, 140, 0));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
//                 else if (board.getCell(i, j) == 'g') {
//                    g2d.setPaint(new Color(255, 255, 0));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
//                 else if (board.getCell(i, j) == 'k') {
//                    g2d.setPaint(new Color(0, 0, 0));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
//                 else if (board.getCell(i, j) == 'm') {
//                    g2d.setPaint(new Color(72, 209, 204));
//                    g2d.fillRect(j * blockWidth, i * blockHeight, blockWidth - 1, blockHeight- 1);
//                }
            }
        }
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0, blockHeight * 5, Config.GAMEPANEL_SIZE.width, blockHeight * 5);

      /*       g2d.setColor(new Color(0, 206, 209));
        g2d.fillRect(0, (int) (Config.GAMEPANEL_SIZE.height*0.75), Config.GAMEPANEL_SIZE.width,Config.GAMEPANEL_SIZE.height );
    */
    }

}