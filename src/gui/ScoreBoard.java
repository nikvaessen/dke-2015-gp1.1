package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by baxie on 30-11-15.
 */
public class ScoreBoard extends JPanel{

    private int score;
    private JLabel scoreLabel;
    private JLabel scoreValueLabel;

    /**
     * construct panel which displays a score
     */
    public ScoreBoard()
    {
        //variable creation
        this.score = 0;
        this.scoreLabel = new JLabel("Score: ");
        this.scoreValueLabel = new JLabel(Integer.toString(score));

        // adding to panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(scoreLabel);
        this.add(scoreValueLabel);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * construct panel which displays a score
     * @param initialValue the initial score the panel displays
     */
    public ScoreBoard(int initialValue)
    {
        this.score = initialValue;
    }

    /**
     * the preferred size of the panel
     * @return the preferred size of the panel
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(150,50);
    }


    /**
     * set the score of the panel
     * @param newValue the new value of the score
     */
    public void setScore(int newValue)
    {
        this.score = newValue;
        this.scoreValueLabel.setText(Integer.toString(score));
        this.repaint();
    }

}
