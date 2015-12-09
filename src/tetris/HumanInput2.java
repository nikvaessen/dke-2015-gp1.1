package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class HumanInput2 implements KeyListener, InputController {

    private char currentInput;

    /**
     * Constructor for the current input
     */
    public HumanInput2() {
        currentInput = ' ';
    }

    /**
     * Identifies which button was pressed and associates it with the correct input
     * @param e the button that is pressed
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 69)
            currentInput = 'r';
        if(e.getKeyCode() == 87)
            currentInput = 'd';
        if(e.getKeyCode() == 81)
            currentInput = 'l';
        if(e.getKeyCode() == 88)
            currentInput = 'z';
        if(e.getKeyCode() == 67)
            currentInput = 'x';
        if (e.getKeyCode() == 86) {
            currentInput = 's';
        }
        //System.out.println(currentInput);
        //displayInfo(e, "Pressed: ");
    }


    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }

    public void keyTyped(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }

    /**
     * Used to display the code for the button pressed
     * @param e the button that is pressed
     * @param s the string which will contain the code of the button
     */
    public void displayInfo(KeyEvent e, String s) {
        String keyString;
        int keyCode = e.getKeyCode();
        keyString = s + "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
        System.out.println(keyString);
    }

    /**
     * Used to test whether there is a new input
     * @return whether there is a new input or not
     */
    public boolean hasNewInput() {
        if (currentInput == ' ') {
            return false;
        }
        return true;
    }

    /**
     * Used to return the current input
     * @return the current input
     */
    public char getCurrentInput() {
        char toReturn = currentInput;
        currentInput = ' ';
        return toReturn;
    }
}
