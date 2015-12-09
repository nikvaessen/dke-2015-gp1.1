package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by baxie on 15-11-15.
 */
public class HighScoreWindow extends JPanel {

    private final String FILEPATH = "data/HighScores.txt";
    private HashMap<String, String> highScores;

    private Font font = new Font("SansSerif", Font.PLAIN, 25);

    /**
     * construct a panel which displays the 5 best scoring players in a HighScoreList object
     * @param mainMenu
     */
    public HighScoreWindow(MainMenu mainMenu) {
        highScores = new HashMap<String, String>();
        this.setPreferredSize(new Dimension(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT));
        setLayout(new GridBagLayout());
        readScores();
        setUpImage();
        printNames(highScores);
        printScores(highScores);
        addBackButton(mainMenu);
        //printScores();
    }

    /**
     * sets up the header image for this panel
     */
    private void setUpImage() {
        try {
            ImageHolderPanel image = new ImageHolderPanel(ImageIO.read(new File(Config.MAIN_MENU_HEADER_IMAGE)));
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0;
            c.gridwidth = 2;
            c.weighty = 0.4;
            c.anchor = GridBagConstraints.NORTH;
            this.add(image, c);
        } catch (IOException e) {
            System.out.println("Could not find image for main menu in memory");
        }
    }

    /**
     * add a high score
     * @param name the name of the new high score
     * @param score the score of the new high score
     */
    public void add(String name, String score) {
        highScores.put(name, score);
    }

    /**
     * read the scores from the file
     */
    public void readScores() {
        Scanner in;
        try {
            in = new Scanner(new FileReader(FILEPATH));
            while (in.hasNextLine() == true) {
                String line = in.nextLine();
                int i = line.indexOf(',');
                String name = line.substring(0, i);
                String score = line.substring(i + 1);
                add(name, score);
            }

        } catch (FileNotFoundException e) {
            //create txt file
            e.printStackTrace();
        }
    }


    /**
     * prints out all the names stored in the high score
     * @param highScores the hashmap storing the names
     */
    public void printNames(HashMap<String, String> highScores) {
        JTextArea names = new JTextArea();
        names.setFont(font);
        HashMap<String, String> copyHighScores = (HashMap<String, String>) highScores.clone();
        int maximum = 0;
        String maxName = "";
        for (int i = 0; i < highScores.size(); i++) {
            for (String name : copyHighScores.keySet()) {
                if (Integer.parseInt(copyHighScores.get(name)) > maximum) {
                    maximum = Integer.parseInt(copyHighScores.get(name));
                    maxName = name;
                }
            }
            names.append(maxName + "\n");
            copyHighScores.remove(maxName);
            maxName = "";
            maximum = 0;
        }

        names.setEditable(false);
        names.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 0;
        c.weighty = 1;
        c.insets = new Insets(0, 30, 0, 50);
        this.add(names, c);
    }

    /**
     * prints the scores of a high score
     * @param highScores the hashmap holding the high score
     */
    public void printScores(HashMap<String, String> highScores) {
        JTextArea scores = new JTextArea();
        scores.setFont(font);
        HashMap<String, String> copyHighScores = (HashMap<String, String>) highScores.clone();
        int maximum = 0;
        String maxName = "";
        String file = "";
        for (int i = 0; i < highScores.size(); i++) {
            for (String name : copyHighScores.keySet()) {
                if (Integer.parseInt(copyHighScores.get(name)) > maximum) {
                    maximum = Integer.parseInt(copyHighScores.get(name));
                    maxName = name;
                }
            }
            scores.append(copyHighScores.get(maxName) + "\n");
            copyHighScores.remove(maxName);
            maxName = "";
            maximum = 0;
        }

        scores.setEditable(false);
        scores.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 50, 0, 30);
        this.add(scores, c);
    }

    /**
     * adds the back button to the panel
     * @param mainMenu the main menu to go back to
     */
    public void addBackButton(MainMenu mainMenu) {
        BackButton back = new BackButton(mainMenu);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 2;
        c.gridwidth = 2;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        this.add(back, c);
    }

    public void printScores() {
        for (String name : highScores.keySet()) {
            System.out.println(name + " " + highScores.get(name) + "\n");
        }
    }

}
