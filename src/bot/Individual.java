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

    /**
     * Construct a individual being able to play a tetris game with specific weights as chromomos
     * @param chromosome the weights the bot used for playing the game
     */
    public Individual(double[] chromosome) {
        this.chromosome = chromosome;
        this.fitness = 0;
        this.board = new Board(GeneticAlgorithm.BOARD_WIDTH, GeneticAlgorithm.BOARD_HEIGHT);
        this.bot = new GeneticBot(board, new BoardHandler(board,new Board(10,10),GeneticAlgorithm.tetris), chromosome);
    }

    /**
     * returns the weights used by the bot
     * @return the weights as an array of doubles
     */
    public double[] getChromosome() {
        return chromosome;
    }

    /**
     * sets the weights
     * @param chromosome a double array to set the weights to
     */
    public void setChromosome(double[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * returns the fitness of the individual
     * @return
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * returns a cloned version of individual
     * @return cloned individual
     */
    public Individual clone() {
        double[] chromClone = new double[chromosome.length];
        for(int i = 0; i < chromClone.length; i++) {
            chromClone[i] = chromosome[i];
        }
        return new Individual(chromClone);
    }

    /**
     * make the indivudual start playing the game
     */
    public void run()
    {
        if(!gameStartedRunning)
        {
            gameStartedRunning = true;
            bot.run();
        }
    }

    /**
     * returns if the bot is done playing tetris(the game is over)
     * @return boolean if game is done
     */
    public boolean botIsDone()
    {
        return bot.gameOver();
    }

    /**
     * update the fitness of the individual after it is done playing
     */
    public void updateFitness()
    {
        if(botIsDone() && !fitnessSet) {
            fitnessSet = true;
            //System.out.println("Fitness is: " + bot.getLinesCleared());
            fitness = bot.getLinesCleared();
        }
    }

    /**
     * Kill the thread of the bot playing the game
     */
    public void killThread()
    {
        bot.interrupt();
    }

}
