package tetris;


import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter ;
import java.io.IOException ;
import java.io.PrintWriter;

public class ScoreBoard
{

    private HashMap<String, Integer> highScores;
    private final String FILEPATH = "data/HighScores.txt" ;


    public ScoreBoard(){

        highScores = new HashMap<String, Integer>();
    }

    public void add(String name, int score){
        highScores.put(name, score);
    }

    public void readScores()
    {
        Scanner in;
        try {
            in = new Scanner(new FileReader(FILEPATH));
            while(in.hasNextLine() == true)
            {   String line = in.nextLine();
                int i = line.indexOf(',');

                String name = line.substring(0, i);
                int score = Integer.parseInt(line.substring(i+1));
                add(name, score);
            }

        } catch (FileNotFoundException e) {
            //create txt file
            e.printStackTrace();
        }
    }

    public void printScores()
    {
        HashMap<String, Integer> copyHighScores = (HashMap<String, Integer>) highScores.clone();
        int maximum = 0;
        String maxName = "";
        String file = "";
        for (int i = 0; i<highScores.size(); i++) {
            for (String name : copyHighScores.keySet()) {
                if (copyHighScores.get(name) > maximum) {
                    maximum = copyHighScores.get(name);
                    maxName = name;
                }
            }
            System.out.println(maxName + " = " + maximum);
            file = file + maxName + "," + maximum + "\n";
            copyHighScores.remove(maxName);
            maxName = "";
            maximum = 0;
        }
        saveScores(file);
    }


    public void saveScores(String file)
    {
        PrintWriter writer;
        try {
            writer = new PrintWriter(FILEPATH);

            writer.println(file);
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
