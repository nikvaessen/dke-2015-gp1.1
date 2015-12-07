package tetris;

import gui.Config;
import gui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class Simulation extends JPanel {
    private final int TIME_BETWEEN_ACTIONS = 1000;
    private Board board;
    private BoardHandler boardHandler;
    private GamePanel gamePanel;

    //variables keeping track of board

    boolean SimulationHasStarted;
    Board b;
    Pentomino a;
    BoardHandler c;

    public Simulation() {
        b = new Board(5, 12);
        a = new Pentomino();
        c = new BoardHandler(b, new Board(5,5,), true);
        SimulationHasStarted = false;

        //create the gamepanel and add it
        final GamePanel gamePanel = new GamePanel(board);
        this.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        gamePanel.setSize(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT);
        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 1;
        d.weightx = 0.5;
        d.gridy = 0;
        d.weighty = 1;
        d.gridheight = 3;
        d.anchor = GridBagConstraints.CENTER;
        this.add(gamePanel, d);

        //add the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(30);
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        d = new GridBagConstraints();
        d.gridx = 2;
        d.weightx = 0.2;
        d.insets = new Insets(250, 0, 0, 0);
        this.add(buttonPanel, d);


        //startbutton
        final JButton startButton = new JButton("Start");
        startButton.requestFocus(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!SimulationHasStarted) {
                    try {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                SimulationHasStarted = true;
                                startButton.setEnabled(false);

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
                        });
                    } catch (Exception expenction) {
                        expenction.printStackTrace();
                    }
                }
            }
        });
        buttonPanel.add(startButton);
    }


}


