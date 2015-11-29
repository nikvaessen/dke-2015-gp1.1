package tetris;

import java.util.ArrayList;

/**
 * Created by baxie on 29-11-15.
 */
public interface Polyomino {

    /**
     * Returns an ArrayList of pentominoes names which can be used in the other functions of this class.
     * @return names of the pentominoes
     */
    public ArrayList<Character> getKeys();

    /**
     * Returns the amount of forms a given pentomino has.
     * @param name name of pentomino
     * @return amount of the forms a given pentomino has
     */
    public int getAmountOfMatrixes(char name);

    /**
     * Returns the matrix of the asked form of the asked pentomino.
     * @param name name of the pentomino
     * @param number the form you want to be given, ranging from 0 for the first stored to the amount of forms stored.
     * @return the matrix of the asked pentomino
     */
    public char[][] getMatrix(char name, int number);

    /**
     * Returns a Blocks, which stores every form of the pentomino.
     * @param name name of the pentomino
     * @return the pentomino
     */
    public Blocks getPentomino(char name);

    /**
     * Sets the pentomino and all its rotations to true in the boolean array of used pentominoes if it has been used.
     * @param name name of the pentomino
     */
    public void usedPentomino(char name);

    /**
     * Sets the pentomino and all its rotations to false in the boolean array of used pentominoes if it has been removed from the board
     * @param name name of the pentomino
     */
    public void removedPentomino(char name);


    public boolean hasFlip(char name);

    public char[][] getFlippdMatrix(char name) throws IllegalArgumentException;

}
