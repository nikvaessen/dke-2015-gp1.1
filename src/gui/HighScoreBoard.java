package gui;

import tetris.HighScoreList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by baxie on 1-12-15.
 */
public class HighScoreBoard extends JPanel {

    private HighScoreList highScoreList;
    private JTextArea highScoreDisplay;

    public HighScoreBoard(final HighScoreList highScoreList)
    {
        this.highScoreList = highScoreList;

        this.highScoreDisplay = new JTextArea();
        highScoreDisplay.setEditable(false);
        highScoreDisplay.setOpaque(false);
        highScoreDisplay.setColumns(1+ 2 + 10 + 6 + 7 + 1); // String format: "A: B with C points "
                                                        // A = 1 column B = 10 columns C = 10 colums
        highScoreDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.add(highScoreDisplay);
        updateHighScoreDisplay(highScoreList.getHighScoreList());

        setBorder(BorderFactory.createLineBorder(Color.black));

        //timer to update display
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(highScoreList.hasNewScore())
                {
                    updateHighScoreDisplay(highScoreList.getHighScoreList());
                }
            }
        }).start();
    }

    public Dimension getPreferredSize()
    {
        return highScoreDisplay.getPreferredSize();
    }

    private void updateHighScoreDisplay(ArrayList<String> list)
    {
        String newDisplayText = "";
        for(int i = 0; i < list.size() && i < 5; i++){
            String line = list.get(i);
            newDisplayText += String.format(" %d: %12s with %7s points \n", i + 1, line.substring(0,line.indexOf(",")),
                    line.substring(line.indexOf(",")+1));
        }
        if(list.size() == 0)
            newDisplayText = "No highscores yet!";
        System.out.println("New text for the highscore display: \n" + newDisplayText);
        highScoreDisplay.setText(newDisplayText);
    }

}
