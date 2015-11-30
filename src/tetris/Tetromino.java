package tetris;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by baxie on 29-11-15.
 */
public class Tetromino implements Polyomino {

    //instance variables
    private ArrayList<Character> keys;			//ArrayList which will store the keys used in the HashMap
    private HashMap<Character, Blocks> dict;  //HashMap mapping Blocks classes representing a pentomino of a specific letter
    //to a key of the respective letter
    private HashMap<Character, Blocks> flip; //stores flipped versions of a pentomino

    /**
     * Constructs the object of the Blocks class for a given kind of pentomino, e.g. x or p and a matrix of chars representing a single form
     * of the given kind or pentomino, where 'o' is an empty cell and 'char' a non-empty cell.
     */
    public Tetromino() {

        //i tetromino
        char[][] iMatrix = {
                {'i'},
                {'i'},
                {'i'},
                {'i'}};
        Blocks i = new Blocks('i', iMatrix);

        //O tetromino
        char[][] oMatrix = {
                {'x', 'x'},
                {'x', 'x'}};
        Blocks x = new Blocks('x', oMatrix);

        //Z tetromino
        char[][] zMatrix = {
                {'z', 'o'},
                {'z', 'z'},
                {'o', 'z'}};
        Blocks z = new Blocks('z', zMatrix);

        //T tetromino
        char[][] tMatrix = {
                {'t', 'o'},
                {'t', 't'},
                {'t', 'o'}};
        Blocks t = new Blocks('t', tMatrix);

        //L tetrominoe
        char[][] lMatrix = {
                {'l', 'o'},
                {'l', 'o'},
                {'l', 'l'}};
        Blocks l = new Blocks('l', lMatrix);

        //flipped versions of the l and z tetrominoes
        char[][] lMatrixflip = {
                {'o', 'l'},
                {'o', 'l'},
                {'l', 'l'}};
        Blocks l1 = new Blocks('y', lMatrixflip);

        char[][] zMatrixflip = {
                {'o', 'z'},
                {'z', 'z'},
                {'z', 'o'}};
        Blocks z1 = new Blocks('z', zMatrixflip);

        //Puts all pentominoes into the hashmap with their corresponding 'letter' as key
        this.dict = new HashMap<Character, Blocks>();
        this.dict.put('l', l);
        this.dict.put('i', i);
        this.dict.put('t', t);
        this.dict.put('z', z);
        this.dict.put('x', x);

        // i dont want to put it in .dict but i dont know where else to put them?

        this.flip= new HashMap<Character, Blocks>();
        this.flip.put('l', l1);
        this.flip.put('z', z1);

        //Adds all letters of dictionary to the arraylist 'keys'.
        this.keys = new ArrayList<Character>();
        for(char letter : new char[] {'i','x','z','t','l'}){
            this.keys.add(letter);
        }
    }

    /**
     * Returns an ArrayList of pentominoes names which can be used in the other functions of this class.
     * @return names of the pentominoes
     */
    public ArrayList<Character> getKeys() {
        return this.keys;
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


    public boolean hasFlip(char name)
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

    public char[][] getFlippdMatrix(char name) throws IllegalArgumentException
    {
        if(hasFlip(name))
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
