

import javax.swing.*;
import java.awt.*;

/**
 * Created by chrx on 11/5/15.
 */
public class GamePanel extends JPanel {
    public static final int BLOCKSIZE = 50;

    private Board board;

    public GamePanel(Board board){
        this.board = board;
    }

    public void paintComponent(Graphics g)
    {
        int columns = board.getWidth();
        int rows = board.getHeight();
        for(int i = 0; i < rows; i++ )
        {
            for(int j = 0; i < columns; j++)
            {
                g.drawRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
            }
        }

    }

    public void repaint(Graphics g)
    {
        int columns = board.getWidth();
        int rows = board.getHeight();
        for(int i = 0; i < rows; i++ )
        {
            for(int j = 0; i < columns; j++)
            {
                g.fillRect(i * BLOCKSIZE, j * BLOCKSIZE, BLOCKSIZE, BLOCKSIZE);
                char color = board.getCell(i,j);
                if(color == 'x')
                {
                    g.setColor(Color.BLUE);
                }
            }
        }
    }
}
