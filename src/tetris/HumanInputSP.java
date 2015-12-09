package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Stefan K on 09.12.2015.
 */
public class HumanInputSP implements KeyListener, InputController {

    private char currentInput;

    /**
     * Constructor for the current input
     */
    public HumanInputSP() {
        currentInput = ' ';
    }

    /**
     * Identifies which button was pressed and associates it with the correct input
     * @param e the button that is pressed
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 39)
            currentInput = 'r';
        if(e.getKeyCode() == 40)
            currentInput = 'd';
        if(e.getKeyCode() == 37)
            currentInput = 'l';
        if(e.getKeyCode() == 90)
            currentInput = 'z';
        if(e.getKeyCode() == 88)
            currentInput = 'x';
        if (Character.isSpaceChar(e.getKeyChar())) {
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
