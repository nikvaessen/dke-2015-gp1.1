//package bot;
//
//import tetris.Board;
//import tetris.BoardHandler;
//
//import java.util.Random;
//
///**
// * Created by baxie on 12/7/15.
// */
//public class GeneticAlgorithm {
//
//    public static final int BOARD_WIDTH = 10;
//    public static final int BOARD_HEIGHT = 20;
//    public static final boolean tetris = false;
//
//    public static Random rng;
//
//    public static void main(String[] argv)
//    {
//        GeneticBot[] population = new GeneticBot[1000];
//         rng = new Random(System.currentTimeMillis());
//        //fill population with bots
//        for(int i = 0; i < population.length; i++)
//        {
//            Board boardForBot = new Board(BOARD_WIDTH, BOARD_HEIGHT);
//            double[] chromosome = new double[] {rng.nextDouble()}
//            population[i] = new GeneticBot(boardForBot, new BoardHandler(boardForBot,));
//        }
//
//    }
//}
