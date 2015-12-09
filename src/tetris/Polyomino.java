package tetris;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by baxie on 29-11-15.
 */
public class Polyomino {
    //instance variables
    private ArrayList<Character> keys;			//ArrayList which will store the keys used in the HashMap
    private HashMap<Character, Blocks> dict;  //HashMap mapping Blocks classes representing a pentomino of a specific letter
    //to a key of the respective letter
    private HashMap<Character, Blocks> flip; //stores flipped versions of a pentomino
    private ArrayList<Character> flippedKeys;

    /**
     * Constructs the object of the Blocks class for a given kind of pentomino, e.g. x or p and a matrix of chars representing a single form
     * of the given kind or pentomino, where 'o' is an empty cell and 'char' a non-empty cell.
     */
    public Polyomino() {
        //Puts all pentominoes into the hashmap with their corresponding 'letter' as key
        this.dict = new HashMap<Character, Blocks>();

        // i dont want to put it in .dict but i dont know where else to put them?
        this.flip= new HashMap<Character, Blocks>();

        //Adds all letters of dictionary to the arraylist 'keys'.
        this.keys = new ArrayList<Character>();

        //add all letters of the flipped matrixeses to the arraylist 'flippedKeys
        this.flippedKeys = new ArrayList<Character>();
    }

    /**
     * Returns an ArrayList of pentominoes names which can be used in the other functions of this class.
     * @return names of the pentominoes
     */
    public ArrayList<Character> getKeys() {
        return this.keys;
    }

    public ArrayList<Character> getFlippedKeys() { return this.flippedKeys;}

    public ArrayList<Character> getAllKeys()
    {
        ArrayList<Character> combinedKeys = new ArrayList<Character>();
        combinedKeys.addAll(this.keys);
        combinedKeys.addAll(this.flippedKeys);
        return combinedKeys;
    }

    /**
     * Returns the amount of forms a given pentomino has.
     * @param name name of pentomino
     * @return amount of the forms a given pentomino has
     */
    public int getAmountOfMatrixes(char name) {
        return this.dict.get(name).getAmountOfMatrixes();
    }

    /**
     * Adds the matrix into the HashMap
     * @param key the key of the pentomino that will be added into the HashMap
     * @param block it represents a single form of a pentomino
     */
    public void addMatrix(char key, Blocks block)
    {
        this.dict.put(key, block);
        this.keys.add(key);
    }

    /**
     * It adds the flipped pentomino into the HashMap
     * @param key the key of the pentomino that will be added into the HashMap
     * @param block it represents a single form of a pentomino
     */
    public void addFlippedMatrix(char key, Blocks block)
    {
        this.flip.put(key, block);
        this.flippedKeys.add(key);
    }

    /**
     * Returns whether or not the HashMap contains the key of the pentomino corresponding to the name parameter
     * @param name the name of the pentomino
     * @return whether or not the HashMap contains the key of the pentomino corresponding to the name parameter
     */
    public boolean hasKey(char name)
    {
        if(dict.containsKey(name))
        {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns the matrix of the asked form of the asked pentomino.
     * @param name name of the pentomino
     * @param number the form you want to be given, ranging from 0 for the first stored to the amount of forms stored.
     * @return the matrix of the asked pentomino
     */
    public char[][] getMatrix(char name, int number) {
        return this.dict.get(name).getMatrix(number);
    }


    /**
     * Returns a Blocks, which stores every form of the pentomino.
     * @param name name of the pentomino
     * @return the pentomino
     */
    public Blocks getPentomino(char name){
        return this.dict.get(name);
    }

    /**
     * Sets the pentomino and all its rotations to true in the boolean array of used pentominoes if it has been used.
     * @param name name of the pentomino
     */
    public void usedPentomino(char name){
        this.dict.get(name).setUsedForAll(true);
    }

    /**
     * Sets the pentomino and all its rotations to false in the boolean array of used pentominoes if it has been removed from the board
     * @param name name of the pentomino
     */
    public void removedPentomino(char name){
        this.dict.get(name).setUsedForAll(false);
    }

    /**
     * Returns whether or not the HashMap contains the key of the flipped pentomino corresponding to the name parameter
     * @param name the name of the pentomino
     * @return whether or not the HashMap contains the key of the pentomino corresponding to the name parameter
     */
    public boolean hasflipKey(char name)
    {
        if(this.flip.containsKey(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the flipped matrix of the matrix corresponding to the name parameter if it exists
     * @param name the matrix of which we want to return the flipped matrix
     * @return the flipped version of the matrix corresponding to the name parameter if it exists
     * @throws IllegalArgumentException if the flipped version of the matrix corresponding to the parameter name doesn't exist it will throw an exception
     */
    public char[][] getFlippdMatrix(char name) throws IllegalArgumentException
    {
        if(hasflipKey(name))
        {
            return flip.get(name).getMatrix(0);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * @param name name of the pentomino
     * @param i index retaining the order of the used pentominoes
     * @return
     */
    public boolean alreadyUsed(char name, int i){
        return this.dict.get(name).isUsed(i);
    }

    /**
     * Prints all pentominoes (used for testing).
     */
    public void printAllPentominoes(){
        for(char key : dict.keySet()){
            System.out.println(this.dict.get(key));
        }
    }


}
