package tetris;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to construct 12 pentominoes in their basic form and get their rotations
 * @author 
 *
 */
public class Pentominoes {
	//instance variables
	private ArrayList<Character> keys;			//ArrayList which will store the keys used in the HashMap
	private HashMap<Character,Pentomino> dict;  //HashMap mapping Pentomino classes representing a pentomino of a specific letter
												//to a key of the respective letter 
	
	/**
	 * Constructs the object of the Pentomino class for a given kind of pentomino, e.g. x or p and a matrix of chars representing a single form
	 * of the given kind or pentomino, where 'o' is an empty cell and 'char' a non-empty cell. 
	 * @param name the character representing the pentomino
	 * @param matrix matrix of chars storing the basic form of the pentomino
	 */
	public Pentominoes() {
		
		char[][] pMatrix = {
				{'p', 'p'},
				{'p', 'p'},
				{'p', 'o'} };
		Pentomino p = new Pentomino('p', pMatrix);
		
		
		char[][] xMatrix = {
				{'o', 'x', 'o'},
				{'x', 'x', 'x'},
				{'o', 'x', 'o'} };
		Pentomino x = new Pentomino('x', xMatrix);
		
		
		char[][] fMatrix = {
				{'o', 'f', 'f'},
				{'f', 'f', 'o'},
				{'o', 'f', 'o'} };
		Pentomino f = new Pentomino('f', fMatrix);
		
		
		char[][] vMatrix = {
				{'v', 'o', 'o'},
				{'v', 'o', 'o'},
				{'v', 'v', 'v'} };
		Pentomino v = new Pentomino('v', vMatrix);
		
		
		char[][] wMatrix = {
				{'w', 'o', 'o'},
				{'w', 'w', 'o'},
				{'o', 'w', 'w'} };
		Pentomino w = new Pentomino('w', wMatrix);
		
		
		char[][] yMatrix = {
				{'o', 'y'},
				{'y', 'y'},
				{'o', 'y'},
				{'o', 'y'} };
		Pentomino y = new Pentomino('y', yMatrix);
		
		
		char[][] iMatrix = {
				{'i'},
				{'i'},
				{'i'},
				{'i'},
				{'i'} };
		Pentomino i = new Pentomino('i', iMatrix);
		
		
		char[][] tMatrix = {
				{'t', 't', 't'},
				{'o', 't', 'o'},
				{'o', 't', 'o'} };
		Pentomino t = new Pentomino('t', tMatrix);
		
		
		char[][] zMatrix = {
				{'z', 'z', 'o'},
				{'o', 'z', 'o'},
				{'o', 'z', 'z'} };
		Pentomino z = new Pentomino('z', zMatrix);
		
		
		char[][] uMatrix = {
				{'u', 'o', 'u'},
				{'u', 'u', 'u'} };
		Pentomino u = new Pentomino('u', uMatrix);
		
		
		char[][] nMatrix = {
				{'n', 'n', 'o', 'o'},
				{'o', 'n', 'n', 'n'} };
		Pentomino n = new Pentomino('n', nMatrix);
		
		
		char[][] lMatrix = {
				{'o', 'o', 'o', 'l'},
				{'l', 'l', 'l', 'l'} };
		Pentomino l = new Pentomino('l', lMatrix);

		char[][] pMatrixflip = {
				{'p', 'p'},
				{'p', 'p'},
				{'o', 'p'}};
		Pentomino p1 = new Pentomino('p', pMatrixflip);


		char[][] fMatrixflip = {
				{'f', 'f', 'o'},
				{'o', 'f', 'f'},
				{'o', 'f', 'o'}};
		Pentomino f1 = new Pentomino('f', fMatrixflip);

		char[][] vMatrixflip = {
				{'o', 'o', 'v'},
				{'o', 'o', 'v'},
				{'v', 'v', 'v'} };
		Pentomino v1 = new Pentomino('v', vMatrixflip);

		char[][] wMatrixflip = {
				{'o', 'o', 'w'},
				{'o', 'w', 'w'},
				{'w', 'w', 'o'} };
		Pentomino w1 = new Pentomino('w', wMatrixflip);

		char[][] yMatrixflip = {
				{'y', 'o'},
				{'y', 'y'},
				{'y', 'o'},
				{'y', 'o'} };
		Pentomino y1 = new Pentomino('y', yMatrixflip);

		char[][] zMatrixflip = {
				{'o', 'z', 'z'},
				{'o', 'z', 'o'},
				{'z', 'z', 'o'} };
		Pentomino z1 = new Pentomino('z', zMatrixflip);

		char[][] nMatrixflip = {
				{'o', 'o', 'n', 'n'},
				{'n', 'n', 'n', 'o'} };
		Pentomino n1 = new Pentomino('n', nMatrixflip);

		char[][] lMatrixflip = {
				{'o', 'o', 'o', 'l'},
				{'l', 'l', 'l', 'l'} };
		Pentomino l1 = new Pentomino('l', lMatrixflip);



		//Puts all pentominoes into the hashmap with their corresponding 'letter' as key
		this.dict = new HashMap<Character,Pentomino>();
		this.dict.put('l', l);
		this.dict.put('p', p);
		this.dict.put('i', i);
		this.dict.put('f', f);
		this.dict.put('v', v);
		this.dict.put('w', w);
		this.dict.put('y', y);
		this.dict.put('t', t);
		this.dict.put('z', z);
		this.dict.put('x', x);
		this.dict.put('u', u);
		this.dict.put('n', n);

		/*
		 // i dont want to put it in .dict but i dont know where else to put them?

		this.flip= new HashMap<Character, Pentomino>();
		this.flip.put('l',l1);
		this.flip.put('n', n1);
		this.flip.put('p', p1);
		this.flip.put('f', f1);;
		this.flip.put('v', v1);
		this.flip.put('y', y1);
		this.flip.put('w', w1);
		*/
		//Adds all letters of dictionary to the arraylist 'keys'.
		this.keys = new ArrayList<Character>();
		for(char letter : new char[] {'l','x','f','v','w','y','i','t','z','u','n','p'}){
			this.keys.add(letter);
		}		
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
	 * Returns a Pentomino, which stores every form of the pentomino.
	 * @param name name of the pentomino
	 * @return the pentomino
	 */
	public Pentomino getPentomino(char name){
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
	 * 
	 * @param name name of the pentomino
	 * @param i index retaining the order of the used pentominoes
	 * @return
	 */
	public boolean alreadyUsed(char name, int i){
		return this.dict.get(name).isUsed(i);
	}
	
	/**
	 * ???????????????????????????????????????????????????????????????
	 * @return
	 */
	public ArrayList<char[][]> createTheListOfLists(){
		ArrayList<char[][]> theList = new ArrayList<char[][]>();
		for(char key : this.keys){
			Pentomino p = dict.get(key);
			int matrixes = p.getAmountOfMatrixes();
			for(int i = 0; i < matrixes; i++){
				theList.add(p.getMatrix(i));
			}
		}
		return theList;
	}
	
	/**
	 * ????????????????????????????????????????????????????????????????
	 * @return
	 */
	public char[] getCharForTheList(){
		char[] keys = new char[63];
		ArrayList<Character> temp = new ArrayList<Character>();
		for(char key : this.keys){
			Pentomino p = dict.get(key);
			int matrixes = p.getAmountOfMatrixes();
			for(int i = 0; i < matrixes; i++){
				temp.add(key);
			}
		}
		
		for(int i = 0; i < temp.size();i++){
			keys[i] = temp.get(i);
		} 
		return keys;
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