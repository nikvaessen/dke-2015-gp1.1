package tetris;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileReader;

public class HighScoreList
{
    private HashMap<String, Integer> highScores;
    private final String FILEPATH = "data/HighScores.txt" ;

    private boolean hasNewScore;

    /**
     * Constructor for the high score list
     */
    public HighScoreList(){

        highScores = new HashMap<String, Integer>();
        readScores();
        hasNewScore = false;
    }

    /**
     * It adds a new score and name o the high score list
     * @param name the name that will be added in the high score list
     * @param score the score that will be added in the high score list
     */
    public void add(String name, int score){
        highScores.put(name, score);
        saveScore(formatString(name, score));
    }

    /**
     * Check whether there is a new high score or not
     * @return whether there is a new highscore or not
     */
    public boolean hasNewScore()
    {
        return hasNewScore;
    }

    /**
     * Returns the high score list
     * @return the high score list
     */
    public ArrayList<String> getHighScoreList()
    {
        hasNewScore = false;
        ArrayList<String> highScoreList = new ArrayList<>();
        HashMap<String, Integer> copyHighScores = (HashMap<String, Integer>) highScores.clone();
        int maximum = 0;
        String maxName = "";
        for (int i = 0; i<highScores.size(); i++) {
            for (String name : copyHighScores.keySet()) {
                if (copyHighScores.get(name) > maximum) {
                    maximum = copyHighScores.get(name);
                    maxName = name;
                }
            }
            highScoreList.add(formatString(maxName , maximum));
            copyHighScores.remove(maxName);
            maxName = "";
            maximum = 0;
        }
        return highScoreList;
    }

    /**
     * It reads the scores
     */
    private void readScores()
    {
        Scanner in;
        try {
            in = new Scanner(new FileReader(FILEPATH));
            while(in.hasNextLine() == true)
            {   String line = in.nextLine();
                int i = line.indexOf(',');

                String name = line.substring(0, i);
                int score = Integer.parseInt(line.substring(i+1));
                highScores.put(name, score);
            }
            in.close();
        } catch (FileNotFoundException e) {
            //create txt file
            e.printStackTrace();
        }
    }

    /**
     * It prints the scores
     */
    public void printScores()
    {
        ArrayList<String> toPrint = getHighScoreList();
        for(int i = 0; i < toPrint.size(); i++)
        {
            String line = toPrint.get(i);
            System.out.printf("%d: %10s with %7s points%n", i + 1, line.substring(0,line.indexOf(",")),
                    line.substring(line.indexOf(",")+1));
        }
    }

    /**
     * It saves the score into the high score list
     * @param newScore the score that needs to be saved
     */
    private void saveScore(String newScore)
    {
        PrintWriter writer;
        try {
            writer = new PrintWriter(FILEPATH);
            writer.println(newScore);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * It constructs a string containing the name of the person and tge corresponding score
     * @param name the name of the player that will be put in the score list
     * @param score the score corresponding to that player
     * @return a string containing the name an score of the player
     */
    private String formatString(String name, int score)
    {
        return String.format("%s,%s",name, Integer.toString(score));
    }

}
