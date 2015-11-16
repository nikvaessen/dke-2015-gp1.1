package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by baxie on 16-11-15.
 */
public class BackButton extends JButton {

    public BackButton(final JPanel toRemove)
    {
        super("< back");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ((JFrame) toRemove.getParent()).getContentPane().remove(toRemove);
            }
        });
    }
}