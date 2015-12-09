//to add this tetris.InputController to a panel, make sure the panel is focusable by
//using the below methods:
//    panel.setFocusable(true);
//    panel.requestFocusInWindow();
//panel has to be focused in order for the KeyListener to be able to listen
package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HumanInput implements KeyListener, InputController {
    private char currentInput;

    /**
     * Constructor for the current input
     */
    public HumanInput() {
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
        if(e.getKeyCode() == 78)
            currentInput = 'z';
        if(e.getKeyCode() == 77)
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