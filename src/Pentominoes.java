import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to construct 12 pentominoes in their basic form and get their rotations.
 * @author 
 *
 */
public class Pentominoes {
	//instance variables
	private ArrayList<Character> keys;			//ArrayList which will store the keys used in the HashMap
	private HashMap<Character,Pentomino> dict;  //HashMap mapping Pentomino classes representing a pentomino of a specific letter
												//to a key of the respective letter 
	
	/**
	 * Constructs 12 pentomino classes for each of the 12 different kind of pentominoes and stores them in an internal
	 * HashMap
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
		
		
		//Adds all letters of dictionary to the arraylist 'keys'.
		this.keys = new ArrayList<Character>();
		for(char letter : new char[] {'l','x','f','v','w','y','i','t','z','u','n','p'}){
			this.keys.add(letter);
		}		
	}

	/**
	 * Returns an ArrayList of pentominoes names which can be used in the other functions of this class.
	 * Each Character represent a kind of pentomino which can be retrieved.
	 * @return names of the pentominoes
	 */
	public ArrayList<Character> getKeys() {
		return this.keys;
	}

	/**
	 * Returns an array of pentominoes names which can be used in the other functions of this class.
	 * Each Character represent a kind of pentomino which can be retrieved.
	 * @return names of the pentominoes
	 */
	public char[] getKeysAsArray()
	{
		ArrayList<Character> list = getKeys();
		char[] array = new char[list.size()];
		for(int i = 0; i < list.size(); i++)
		{
			array[0] = list.get(i);
		}
		return array;
	}

	/**
	 * Returns the amount of forms, rotations a given pentomino has.
	 * @param name name of pentomino
	 * @return amount of the forms a given pentomino has as an integer
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
	 * Returns a Pentomino, which stores every form of one kind of pentomino.
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
	 * Creates an ArrayList of all 63 possible pentominos and their respective rotations
	 * @return an arraylist of char[][] holding all 63 possible pentominoes
	 */
	public ArrayList<char[][]> getAllPentominoes(){
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
	 * Return a list of chars er which represent the kind of pentomino in the same position in the ArrayList got when
	 * the getAllPentominoes method is called
	 * @return A array of chars representing the kind of pentomino in the list gotten from the getAllPentominoes method
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