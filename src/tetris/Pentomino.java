package tetris;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to construct 12 pentominoes in their basic form and get their rotations
 * @author 
 *
 */
public class Pentomino extends Polyomino {

	/**
	 * Constructs the object of the Blocks class for a given kind of pentomino, e.g. x or p and a matrix of chars representing a single form
	 * of the given kind or pentomino, where 'o' is an empty cell and 'char' a non-empty cell.
	 */
	public Pentomino() {
		super();
		char[][] pMatrix = {
				{'p', 'p'},
				{'p', 'p'},
				{'p', 'o'} };
		Blocks p = new Blocks('p', pMatrix);
		
		
		char[][] xMatrix = {
				{'o', 'x', 'o'},
				{'x', 'x', 'x'},
				{'o', 'x', 'o'} };
		Blocks x = new Blocks('x', xMatrix);
		
		
		char[][] fMatrix = {
				{'o', 'f', 'f'},
				{'f', 'f', 'o'},
				{'o', 'f', 'o'} };
		Blocks f = new Blocks('f', fMatrix);
		
		
		char[][] vMatrix = {
				{'v', 'o', 'o'},
				{'v', 'o', 'o'},
				{'v', 'v', 'v'} };
		Blocks v = new Blocks('v', vMatrix);
		
		
		char[][] wMatrix = {
				{'w', 'o', 'o'},
				{'w', 'w', 'o'},
				{'o', 'w', 'w'} };
		Blocks w = new Blocks('w', wMatrix);
		
		
		char[][] yMatrix = {
				{'o', 'y'},
				{'y', 'y'},
				{'o', 'y'},
				{'o', 'y'} };
		Blocks y = new Blocks('y', yMatrix);
		
		
		char[][] iMatrix = {
				{'i'},
				{'i'},
				{'i'},
				{'i'},
				{'i'} };
		Blocks i = new Blocks('i', iMatrix);
		
		
		char[][] tMatrix = {
				{'t', 't', 't'},
				{'o', 't', 'o'},
				{'o', 't', 'o'} };
		Blocks t = new Blocks('t', tMatrix);
		
		
		char[][] zMatrix = {
				{'z', 'z', 'o'},
				{'o', 'z', 'o'},
				{'o', 'z', 'z'} };
		Blocks z = new Blocks('z', zMatrix);
		
		
		char[][] uMatrix = {
				{'u', 'o', 'u'},
				{'u', 'u', 'u'} };
		Blocks u = new Blocks('u', uMatrix);
		
		
		char[][] nMatrix = {
				{'n', 'n', 'o', 'o'},
				{'o', 'n', 'n', 'n'} };
		Blocks n = new Blocks('n', nMatrix);
		
		
		char[][] lMatrix = {
				{'o', 'o', 'o', 'l'},
				{'l', 'l', 'l', 'l'} };
		Blocks l = new Blocks('l', lMatrix);

		char[][] pMatrixflip = {
				{'a', 'a'},
				{'a', 'a'},
				{'o', 'a'}};
		Blocks p1 = new Blocks('p', pMatrixflip);


		char[][] fMatrixflip = {
				{'b', 'b', 'o'},
				{'o', 'b', 'b'},
				{'o', 'b', 'o'}};
		Blocks f1 = new Blocks('f', fMatrixflip);

		char[][] vMatrixflip = {
				{'o', 'o', 'c'},
				{'o', 'o', 'c'},
				{'c', 'c', 'c'} };
		Blocks v1 = new Blocks('v', vMatrixflip);

		char[][] wMatrixflip = {
				{'o', 'o', 'd'},
				{'o', 'd', 'd'},
				{'d', 'd', 'o'} };
		Blocks w1 = new Blocks('w', wMatrixflip);

		char[][] yMatrixflip = {
				{'e', 'o'},
				{'e', 'e'},
				{'e', 'o'},
				{'e', 'o'} };
		Blocks y1 = new Blocks('y', yMatrixflip);

		char[][] zMatrixflip = {
				{'o', 'g', 'g'},
				{'o', 'g', 'o'},
				{'g', 'g', 'o'} };
		Blocks z1 = new Blocks('z', zMatrixflip);

		char[][] nMatrixflip = {
				{'o', 'o', 'k', 'k'},
				{'k', 'k', 'k', 'o'} };
		Blocks n1 = new Blocks('n', nMatrixflip);

		char[][] lMatrixflip = {
				{'o', 'o', 'o', 'm'},
				{'m', 'm', 'm', 'm'} };
		Blocks l1 = new Blocks('l', lMatrixflip);
		
		//Puts all pentominoes into the hashmap with their corresponding 'letter' as key
		super.addMatrix('l', l);
		super.addMatrix('p', p);
		super.addMatrix('i', i);
		super.addMatrix('f', f);
		super.addMatrix('v', v);
		super.addMatrix('w', w);
		super.addMatrix('y', y);
		super.addMatrix('t', t);
		super.addMatrix('z', z);
		super.addMatrix('x', x);
		super.addMatrix('u', u);
		super.addMatrix('n', n);

		// i dont want to put it in .dict but i dont know where else to put them?
		//JONTY: can you please put the new characters here :)?
		super.addFlippedMatrix('b', l1);
		super.addFlippedMatrix('g', n1);
		super.addFlippedMatrix('e', p1);
		super.addFlippedMatrix('d', f1);
		super.addFlippedMatrix('c', v1);
		super.addFlippedMatrix('k', y1);
		super.addFlippedMatrix('m', w1);
    }
}