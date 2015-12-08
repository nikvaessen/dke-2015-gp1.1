package bot;

import tetris.Board;
import tetris.BoardHandler;

/**
 * Created by baxie on 12/8/15.
 */
public class Individual {
    private double[] chromosome;
    private double fitness;
    private GeneticBot bot;
    private Board board;

    private boolean fitnessSet;
    private boolean gameStartedRunning;
    private boolean gameDone;

    public Individual(double[] chromosome) {
        this.chromosome = chromosome;
        this.fitness = 0;
        this.board = new Board(GeneticAlgorithm.BOARD_WIDTH, GeneticAlgorithm.BOARD_HEIGHT);
        this.bot = new GeneticBot(board, new BoardHandler(board,new Board(10,10),GeneticAlgorithm.tetris), chromosome);
    }

    public double[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(double[] chromosome) {
        this.chromosome = chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Individual clone() {
        double[] chromClone = new double[chromosome.length];
        for(int i = 0; i < chromClone.length; i++) {
            chromClone[i] = chromosome[i];
        }
        return new Individual(chromClone);
    }

    public void run()
    {
        if(!gameStartedRunning)
        {
            gameStartedRunning = true;
            bot.run();
        }
    }

    public boolean botIsDone()
    {
        return bot.gameOver();
    }

    public void updateFitness()
    {
        if(botIsDone() && !fitnessSet) {
            fitnessSet = true;
            //System.out.println("Fitness is: " + bot.getLinesCleared());
            fitness = bot.getLinesCleared();
        }
    }

    public void killThread()
    {
        bot.interrupt();
    }

}
