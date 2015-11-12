package gui;
import tetris.Board;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Aleksandra on 2015-11-10.
 */
public class GameWindow extends JFrame{
    //instance variables
    private final int WIDTH  = 400;
    private final int HEIGHT = 500;

    private InputController input;
    private Board board;



    public GameWindow(InputController input, Board board){
        //sets up inputcontroller and tetris
        this.input = input;
        this.board = board;
        //method which sets up all components
        this.setPanels();
        //sets size, title bar and close operation
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("test gui");
        this.setResizable(false);
        //makes the gui visisble
        //this.pack();
        this.setVisible(true);

    }

    public void setPanels(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        GamePanel gp = new GamePanel(board);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gp.setFocusable(true);
        gp.requestFocus();
        gp.addKeyListener(input);
        this.add(gp, gbc);
    }
}
