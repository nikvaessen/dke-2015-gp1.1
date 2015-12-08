package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Stefan K on 08.12.2015.
 */

public class HumanInput2 implements KeyListener, InputController {

    private char currentInput;

    public HumanInput2() {
        currentInput = ' ';
    }

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
