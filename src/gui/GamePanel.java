package gui;

import tetris.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nik on 11/5/15.
 */
public class GamePanel extends JPanel {
    public static final int BLOCKSIZE = 50;

    private Board board;

    public GamePanel(Board board) {
        this.board = board;
        //testing
        setBackground(Color.GRAY);
        JLabel text = new JLabel("gamepanel shows up");
        this.add(text);
        this.setVisible(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(board.getWidth() * BLOCKSIZE,
                board.getHeight() * BLOCKSIZE);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int columns = board.getWidth();
        int rows = board.getHeight();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; i < columns; j++) {
                if (board.getCell(i, j) == 'p') {
                    g2d.setPaint(new Color(255, 20, 147));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'o') {
                    g2d.setPaint(new Color(255, 255, 255));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'x') {
                    g2d.setColor(new Color(184, 134, 11));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'f') {
                    g2d.setColor(new Color(255, 255, 0));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'v') {
                    g2d.setColor(new Color(221, 160, 221));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'w') {
                    g2d.setColor(new Color(47, 79, 79));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'y') {
                    g2d.setColor(new Color(0, 0, 205));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'i') {
                    g2d.setColor(new Color(0, 191, 255));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 't') {
                    g2d.setColor(new Color(0, 206, 209));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'z') {
                    g2d.setColor(new Color(154, 205, 50));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'u') {
                    g2d.setColor(new Color(0, 255, 0));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'n') {
                    g2d.setColor(new Color(128, 0, 128));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                } else if (board.getCell(i, j) == 'l') {
                    g2d.setColor(new Color(186, 85, 211));
                    g2d.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, 50, 50);
                }
            }
        }

    }

    public void repaint(Graphics g) {
        int columns = board.getWidth();
        int rows = board.getHeight();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; i < columns; j++) {
                g.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
                char color = board.getCell(i, j);
                if (color == 'x') {
                    g.setColor(Color.BLUE);
                }
            }
        }
    }
}
