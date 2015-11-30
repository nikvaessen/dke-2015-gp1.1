package tetris;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by baxie on 29-11-15.
 */
public class Tetromino extends Polyomino {
    
    /**
     * Constructs the object of the Blocks class for a given kind of pentomino, e.g. x or p and a matrix of chars representing a single form
     * of the given kind or pentomino, where 'o' is an empty cell and 'char' a non-empty cell.
     */
    public Tetromino() {
        super();
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
        Blocks l1 = new Blocks('l', lMatrixflip);

        char[][] zMatrixflip = {
                {'o', 'z'},
                {'z', 'z'},
                {'z', 'o'}};
        Blocks z1 = new Blocks('z', zMatrixflip);

        //Puts all pentominoes into the hashmap with their corresponding 'letter' as key
        super.addMatrix('l', l);
        super.addMatrix('i', i);
        super.addMatrix('t', t);
        super.addMatrix('z', z);
        super.addMatrix('x', x);

        // i dont want to put it in .dict but i dont know where else to put them?
        super.addFlippedMatrix('l', l1);
        super.addFlippedMatrix('z', z1);
        
    }
}
