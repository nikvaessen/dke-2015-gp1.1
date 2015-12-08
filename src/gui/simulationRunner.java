package gui;

import tetris.Board;
import tetris.BoardHandler;
import tetris.Pentomino;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Jonty on 08/12/2015.
 */

/**
 * Created by chrx on 12/1/15.
 */
public class simulationRunner extends Thread {
    //final variables

    private final int TIME_BETWEEN_ACTIONS = 1000;
    int spawnCount = 0;
    int moveCount = 0;
    private Random rng;
    private Board board;
    private BoardHandler boardHandler;
    private GamePanel gamePanel;
    private Pentomino pentomino = new Pentomino();

    //variables for board handling
    char[] actionCommands;
    ArrayList<char[][]> order;

    public simulationRunner(Board board, BoardHandler boardHandler, GamePanel gamePanel) {
        this.board = board;
        this.boardHandler = boardHandler;
        this.gamePanel = gamePanel;
        this.rng = new Random(System.currentTimeMillis());

        order = new ArrayList<>();
        order.add(pentomino.getMatrix('v', 0));
        order.add(pentomino.getFlippdMatrix('z'));
        order.add(pentomino.getMatrix('f', 0));
        order.add(pentomino.getFlippdMatrix('n'));
        order.add(pentomino.getMatrix('p', 0));
        order.add(pentomino.getMatrix('w', 0));
        order.add(pentomino.getMatrix('t', 0));
        order.add(pentomino.getFlippdMatrix('y'));
        order.add(pentomino.getMatrix('x', 0));
        order.add(pentomino.getMatrix('u', 0));
        order.add(pentomino.getMatrix('i', 0));
        order.add(pentomino.getMatrix('l', 0));

        actionCommands = new char[]{
                'l', 'l', 's',    //v
                'd', 'x', 's',      //z
                'l', 'x', 's',      //f
                'z', 'r','r','s',   //n
                'x','x','r', 's',   //p
                'x','l', 's',       //w
                'l', 'z', 's',      //t
                'x', 'x','r', 's',  //y
                'l', 's',           //x
                'l', 'l', 'x','x','s', //u
                'r', 'r','r', 's',//i
                 'z','r','r', 's',//l

        };
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                boardHandler.hardSpawnPiece(order.get(spawnCount));
//                spawnCount++;
//                gamePanel.repaint();
                simulateGame();
            }
        });
    }

    private void simulateGame() {
        System.out.println("simulate loop started. spawncount: " + spawnCount + " move count: " + moveCount);
        if (boardHandler.isPieceDoneFalling()){
            if(spawnCount==12) return;//boardHandler.checkFullLines();

           // boardHandler.checkFullLines();
            System.out.println("Spawning: " + Arrays.deepToString(order.get(spawnCount)));
            boardHandler.hardSpawnPiece(order.get(spawnCount));
            gamePanel.repaint();
            spawnCount++;
        }
        else
        try {
            System.out.println("sleeping 1 second");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Sending movmement command and repainting");
        boardHandler.giveInput(getMovementCommand());
        gamePanel.repaint();
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    simulateGame();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean wait(int ms) {
        //System.out.println("Waiting: " + System.currentTimeMillis());
        try {
            sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
        //System.out.println("done Waiting: " + System.currentTimeMillis());
    }


    private char getMovementCommand() {
        char action = actionCommands[moveCount];
        moveCount++;
        System.out.println("Giving move command: " + action);
        return action;
    }


}


