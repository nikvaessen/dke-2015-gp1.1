package tetris;


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
    public static String name ;
    public static int score ;
    public static String name1 ;
    public static String name2 ;
    public static String name3 ;
    public static String name4 ;
    public static String name5 ;
    public static int score1 ;
    public static int score2 ;
    public static int score3 ;
    public static int score4 ;
    public static int score5 ;




    public static void readScores()
    {
        String filepath = "C:/Users/Stefan K/workspace/Tetris/src/tetris/highscore.txt" ;
        Scanner in;
        try {
            in = new Scanner(new FileReader(filepath));
            if(in.hasNextLine() == true)
            {
                name1 = in.next() ;
                score1 = in.nextInt();
                if(in.hasNextLine() == true)
                {
                    name2 = in.next();
                    score2 = in.nextInt();
                    if(in.hasNextLine() == true)
                    {
                        name3 = in.next();
                        score3 = in.nextInt();
                        if(in.hasNextLine() == true)
                        {
                            name4 = in.next();
                            score4 = in.nextInt() ;
                            if(in.hasNextLine() == true)
                            {
                                name5 = in.next() ;
                                score = in.nextInt();
                            }
                        }
                    }
                }

            }

        } catch (FileNotFoundException e) {
            //create txt file
            e.printStackTrace();
        }
    }

    public static void printScores()
    {
        System.out.println();
        System.out.println(name1 + " = " + score1);
        System.out.println();
        System.out.println(name2 + " = " + score2);
        System.out.println();
        System.out.println(name3 + " = " + score3);
        System.out.println();
        System.out.println(name4 + " = " + score4);
        System.out.println();
        System.out.println(name5 + " = " + score5);
        System.out.println();
    }


    public static void main(String[] args)
    {
        readScores() ;
        System.out.println() ;
        printScores() ;
        score = 1;
        name = "bianca";
        compareScores() ;
        System.out.println() ;
        printScores() ;
        score = 2 ;
        name = "jonty" ;
        compareScores() ;
        System.out.println() ;
        saveScores() ;
        printScores() ;


    }

    public static void compareScores()
    {
        if(score > score1)
        {
            score5 = score4 ;
            name5 = name4 ;
            score4 = score3 ;
            name4 = name3 ;
            score3 = score2 ;
            name3 = name2 ;
            score2 = score1 ;
            name2 = name1 ;
            score1 = score ;
            name1 = name ;
        }
        else if(score > score2)
        {
            score5 = score4 ;
            name5 = name4 ;
            score4 = score3 ;
            name4 = name3 ;
            score3 = score2 ;
            name3 = name2 ;
            score2 = score ;
            name2 = name ;
        }
        else if(score > score3)
        {
            score5 = score4 ;
            name5 = name4 ;
            score4 = score3 ;
            name4 = name3 ;
            score3 = score ;
            name3 = name ;
        }
        else if(score > score4)
        {
            score5 = score4 ;
            name5 = name4 ;
            score4 = score ;
            name4 = name ;
        }
        else if(score > score5)
        {
            score5 = score ;
            name5 = name ;
        }
    }

    public static void saveScores()
    {
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:/Users/Stefan K/workspace/Tetris/src/tetris/highscore.txt");
            writer.println(name1);
            writer.println(score1);
            writer.println(name2);
            writer.println(score2);
            writer.println(name3);
            writer.println(score3);
            writer.println(name4);
            writer.println(score4);
            writer.println(name5);
            writer.println(score5);
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
