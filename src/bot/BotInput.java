package bot;

import tetris.InputController;

/**
 * Created by chrx on 12/1/15.
 */
public class BotInput implements InputController {

    private char currentInput;

    public void moveRight()
    {
        currentInput='r';
    }

    public void moveLeft()
    {
        currentInput = 'l';
    }

    public void moveDown()
    {
        currentInput='d';
    }

    public void rotateAnticlockwise()
    {
        currentInput='z';
    }

    public void rotateClockwise()
    {
        currentInput='x';
    }

    public void moveCompletelyDown()
    {
        currentInput='s';
    }

    public char getCurrentInput()
    {
        char returnInput=currentInput;
        currentInput=0;
        return returnInput;
    }
}
