package bot;
import tetris.Board;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by baxie on 12/7/15.
 */
public class GeneticAlgorithm {

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final boolean tetris = true;
    public static final int POPULATION = 100;
    public static final int MAX_GENERATIONS = 15;
    public static final int MUTATION_RATE = 15;

    public static Random rng;

    public static void main(String[] argv)
    {
        runAlgorithm();
    }

    /**
     * Runs the Genetic Algorithm
     */
    public static void runAlgorithm()
    {
        System.out.println("with forced starting pop with 15 mut");
        Individual[] population = new Individual[POPULATION];
        rng = new Random(System.currentTimeMillis());
        //fill population with bots
        for(int i = 0; i < population.length; i++)
        {
            Board boardForBot = new Board(BOARD_WIDTH, BOARD_HEIGHT);
            double[] chromosome = new double[] {-0.49957273958047954, 0.29594893328418037, -0.7942169712292743, -0.17540932859336716 };
//            double[] chromosome = new double[] { 2 * rng.nextDouble() - 1,2 * rng.nextDouble() -
//                    1,2 * rng.nextDouble() - 1, 2 * rng.nextDouble() - 1};
            population[i] = new Individual(chromosome);
        }
        //population[POPULATION] = new Individual(new double[] {-0.510066, 0.76066, -0.35663, -0.184483});
        printPopulation(population);

        //genetic algorithm
        for(int generations = 0; generations < MAX_GENERATIONS; generations++) {
            System.out.println("Starting generation: " + (generations + 1) + " out of " + MAX_GENERATIONS);

            //let the population play tetris
            letPopulationPlay(population);

            //select the first hundred
            Individual[] elites = new Individual[POPULATION/10 * 3];
            for(int i = 0; i < elites.length; i++)
            {
                elites[i] = population[i].clone();
            }
            killThreads(elites);
            System.out.print("Selection done\n");

            //create 300 new indivuals by the selected first hundred with crossover :)
            Individual[] children = new Individual[POPULATION/10 * 3];
            for(int i = 0; i < children.length; i+= 2)
            {
                Individual mother = elites[i];
                Individual father = elites[i+1];

                children[i] = createChild(mother, father);
                children[i+1]=createChild(mother, father);
            }
            System.out.print("cross-over done\n");

            //replace the weakest 300 by the new 300 indivuadals
            int k = 0;
            for(int i = (POPULATION/10) * 9; i < population.length; i++, k++){
                population[i] = children[k];
            }
            System.out.print("children replaced\n");
        }
        System.out.println("Result after evolution");
        //every indivual starts playing it's game
        for (int i = 0; i < population.length; i++) {
            population[i].run();
        }
        //wait until every game is done
        int count = 0;
        do {
            count = 0;
            for (int i = 0; i < population.length; i++) {
                Individual bot = population[i];
                if (bot.botIsDone()) {
                    bot.updateFitness();
                    count++;
                }
            }
        } while (count < population.length);
        HeapSort.sort(population);
        printPopulation(population);
        killThreads(population);
    }

    /**
     * It prints out the given population
     * @param population the population you want to get printed out
     */
    public static void printPopulation(Individual[] population)
    {
        for(int i = 0; i < population.length; i++)
        {
            System.out.printf("Bot %d: fitness: %f chromosome: %s\n", i, population[i].getFitness(),
                    Arrays.toString(population[i].getChromosome()));
        }
    }

    /**
     * Uses crossover to create another individual
     * @param mother one of the individuals used in the crossover
     * @param father the other individual used in the crossover
     * @return the resulting individual of the crossover
     */
    public static Individual createChild(Individual mother, Individual father)
    {
        double[] sperm = father.getChromosome();
        double[] egg = mother.getChromosome();
        double[] zygote = new double[sperm.length];
        for(int i = 0; i < zygote.length; i++)
        {
            if(rng.nextBoolean())
            {
                zygote[i] = sperm[i];
            }
            else{
                zygote[i] = egg[i];
            }
        }
        if(rng.nextInt(100) < MUTATION_RATE)
        {
            int indexToMutate = rng.nextInt(zygote.length);
            zygote[indexToMutate] = zygote[indexToMutate] + ((rng.nextDouble() - 1.0)/4.0);
            normalize(zygote);
        }
        return new Individual(zygote);
    }

    /**
     * Starts every thread in every individual
     * @param population the population on which you want to play
     */
    public static void letPopulationPlay(Individual[] population)
    {
        //every indivual starts playing it's game
        for (int i = 0; i < population.length; i++) {
            population[i].run();
        }
        //wait until every game is done
        int count = 0;
        do {
            count = 0;
            for (int i = 0; i < population.length; i++) {
                Individual bot = population[i];
                if (bot.botIsDone()) {
                    bot.updateFitness();
                    count++;
                }
            }
        } while (count < population.length);
        //sort them
        HeapSort.sort(population);
        //loop through the population and reset them so it's possible to let them play the game again
        for(int i = 0; i < population.length; i++)
        {
            population[i] = population[i].clone();
        }
    }

    /**
     * Normalizes a given input vector
     * @param vector the vector you want to normalize
     */
    public static void normalize(double[] vector)
    {
        double length = 0;
        for(int i = 0; i < vector.length; i++)
        {
            length += (vector[i] * vector[i]);
        }
        length = Math.sqrt(length);
        for(int i=0; i < vector.length; i++)
        {
            vector[i] /= length;
        }
    }

    /**
     * Kills every thread of every population
     * @param pop the population for which we want to kill the threads
     */
    public static void killThreads(Individual[] pop)
    {
        for(int i = 0; i < pop.length; i++)
        {
            pop[i].killThread();
        }
    }
}
