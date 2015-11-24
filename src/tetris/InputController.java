//to add this InputController to a panel, make sure the panel is focusable by
//using the below methods:
//    panel.setFocusable(true);
//    panel.requestFocusInWindow();
//panel has to be focused in order for the KeyListener to be able to listen
package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener {
    private char currentInput;

    public InputController() {
        currentInput = ' ';
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39)
            currentInput = 'r';
        if (e.getKeyCode() == 40)
            currentInput = 'd';
        if (e.getKeyCode() == 37)
            currentInput = 'l';

        //displayInfo(e, "Pressed: ");
    }

    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }

    public void keyTyped(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
    }

    public void displayInfo(KeyEvent e, String s) {
        String keyString;
        int keyCode = e.getKeyCode();
        keyString = s + "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
        System.out.println(keyString);
    }

    public boolean hasNewInput() {
        if (currentInput == ' ') {
            return false;
        }
        return true;
    }

    public char getCurrentInput() {
        char toReturn = currentInput;
        currentInput = ' ';
        return toReturn;
    }
}