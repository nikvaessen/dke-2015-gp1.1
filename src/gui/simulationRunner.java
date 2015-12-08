package gui;

import tetris.Board;
import tetris.BoardHandler;
import tetris.Pentomino;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
        order.add(pentomino.getMatrix('w', 0));
        order.add(pentomino.getMatrix('p', 0));
        order.add(pentomino.getMatrix('t', 0));
        order.add(pentomino.getFlippdMatrix('y'));
        order.add(pentomino.getMatrix('x', 0));
        order.add(pentomino.getMatrix('u', 0));
        order.add(pentomino.getMatrix('i', 0));
        order.add(pentomino.getMatrix('l', 0));

        actionCommands = new char[]{
                'l', 'l', 's', 'x', 'l', 's', 'l', 'x', 's', 'x', 'r', 's', 'x', 'l', 's', 'z', 'z', 's', 'l', 'z', 's',
                'x', 'x', 's', 'l', 's', 'l', 'l', 'x', 's', 'r', 'z', 'z', 's', 'l', 'l', 'z', 's', 'r', 'x', 'x', 's', 'l',
                'l', 's', 'x', 'x', 'l', 'l', 's', 'r', 'r', 's', 'z', 'r', 'r', 's'
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
//        if (boardHandler.()) {
//            boardHandler.hardSpawnPiece(order.get(spawnCount));
//            gamePanel.repaint();
//            spawnCount++;
//        }
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        boardHandler.giveInput(getMovementCommand());
//        gamePanel.repaint();
//        try {
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    playGame();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        BoardHandler c = boardHandler;
        Pentomino a = pentomino;
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.repaint();
            }
        }).start();
        c.hardSpawnPiece(a.getMatrix('v', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('l');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getFlippdMatrix('z'));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('l');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getMatrix('f', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('l');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getFlippdMatrix('n'));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('r');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.hardSpawnPiece(a.getMatrix('w', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('l');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getMatrix('p', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('z');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('z');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }


        c.hardSpawnPiece(a.getMatrix('t', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('l');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('z');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getFlippdMatrix('y'));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getMatrix('x', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('l');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getMatrix('u', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.giveInput('l');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('x');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }

        c.hardSpawnPiece(a.getMatrix('i', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('r');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('r');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }


        c.hardSpawnPiece(a.getMatrix('l', 0));
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('z');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('r');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('r');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
        }
        c.giveInput('s');
        try {
            Thread.sleep(TIME_BETWEEN_ACTIONS);
        } catch (Exception e) {
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
        return action;
    }


}


