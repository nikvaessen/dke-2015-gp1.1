
//to add this InputController to a panel, make sure the panel is focusable by
//using the below methods:
//    panel.setFocusable(true);
//    panel.requestFocusInWindow();
//panel has to be focused in order for the KeyListener to be able to listen

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener {

  //private handler handler;

  public InputController(/*BoardHandler handler*/){
    //this.handler = handler;
  }

//  public void keyPressed(KeyEvent e) {
//      displayInfo(e, "KEY PRESSED: ");
//        if(keyPressed.getKeyCode==39)
//          handler.moveRight();
//        if(keyPressed.getKeyCode==40)
//          handler.moveDown();
//        if (keyPressed.getKeyCode==37)
//          handler.moveLeft();
//        if(keyPressed.getKeyCode==38)
//          handler.moveUp();  // to be removed after inital test
//
//    }
   
    public void keyReleased(KeyEvent e) {
      displayInfo(e, "KEY RELEASED: ");
    }

    public void keyPressed(KeyEvent e){
    }

    public void keyTyped(KeyEvent e){

    }

    public void displayInfo(KeyEvent e, String s) {
      String keyString; 
      int keyCode = e.getKeyCode();
      keyString = s + "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
      System.out.println(keyString);
    }
}


   


  

