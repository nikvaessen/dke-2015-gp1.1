package gui;

import javax.swing.*;

/**
 * Created by Aleksandra on 2015-11-10.
 */
public class OptionsWindow extends JPanel {

    private JCheckBox holdPiece;
    private JCheckBox ghostPiece;

    public OptionsWindow(MainMenu mainMenu){
        holdPiece = new JCheckBox("Hold piece");
        ghostPiece = new JCheckBox("Ghost piece");
        this.add(new BackButton(mainMenu));

    }


}
