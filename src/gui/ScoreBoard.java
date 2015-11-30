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

    public ScoreBoard(int initialValue)
    {
        this.score = initialValue;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(150,50);
    }


    public void setScore(int newValue)
    {
        this.score = newValue;
        this.scoreValueLabel.setText(Integer.toString(score));
        this.repaint();
    }

}
