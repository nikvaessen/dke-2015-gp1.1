package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by baxie on 16-11-15.
 */
public class BackButton extends JButton {

    /**
     * constructor for a backbutton which goes back to the main menu
     * @param mainMenu the main menu to return to
     */
    public BackButton(final MainMenu mainMenu)
    {
        super("< back");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainMenu.goBackToMainMenu();
            }
        });
    }
}
