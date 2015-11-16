package gui;

import tetris.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aleksandra and Nik on 2015-11-10.
 */
public class MainMenu extends JFrame {
    //Every panel the we want to potentially display
    private SinglePlayerWindow  singlePlayerWindow;
    private MultiPlayerWindow   multiPlayerWindow;
    private HighScoreWindow     highScoreWindow;
    private BotWindow           botWindow;
    private OptionsWindow       optionsWindow;

    //strings representing all windows in the CardLayout
    private final String mainMenuName = "main";
    private final String singlePlayerName = "sp";
    private final String multiPlayerName = "mp";
    private final String highScoreName = "hs";
    private final String botWindowName = "bot";
    private final String optionMenuName = "opt";

    private JPanel mainPanel; //holds the image header and all the buttons
    private JPanel cardPanel; //holds all panels to switch between
    private CardLayout cardLayout; //holds the layout manager

    //the buttons to go to each panel
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton optionsMenuButton;
    private JButton highScoresButton;
    private JButton botButton;
    private JButton quitButton;

    //current window
    private JPanel currentlyDisplayedPanel;

    public MainMenu() {
        //set up the panels and their buttons and the image header
        setUpMainPanel();
        setUpOtherPanels();
        setUpCardLayout();

        //and set the behavior of the main menu and start displayed the main menu
        //set behavior of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pentris");
        this.setResizable(false);
        startDisplaying("main");
        this.pack();
        this.setVisible(true);
    }

    private void setUpMainPanel()
    {
        mainPanel = new JPanel(); //to hold image and buttons
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT));
        setUpButtons();
        setUpActionListeners();
        setUpImage();
        setUpMadeByText();
    }

    private void setUpOtherPanels()
    {
        singlePlayerWindow = new SinglePlayerWindow();
        setUpAncestorListeners();
    }

    private void setUpCardLayout()
    {
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(this.cardLayout);
        this.cardPanel.setPreferredSize(Config.MAIN_MENU_DIMENSION);
        //set up the cardbox layout and add all panels
        cardPanel.add(mainMenuName, mainPanel);
        cardPanel.add(singlePlayerName, singlePlayerWindow);
        //todo the rest of the panels

        this.add(cardPanel);

    }

    private void setUpButtons()
    {
        //set up the the buttons
        singlePlayerButton = new JButton("Single Player");
        multiPlayerButton = new JButton("Multiplayer");
        optionsMenuButton = new JButton("Options");
        highScoresButton  = new JButton("View Highscore");
        botButton         = new JButton("Watch bot");
        quitButton        = new JButton("Quit");

        //add the buttons to a panel and add the panel to the frame.
        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new GridLayout(6, 0, 0, 20));
        buttonHolder.add(singlePlayerButton);
        buttonHolder.add(multiPlayerButton);
        buttonHolder.add(highScoresButton);
        buttonHolder.add(botButton);
        buttonHolder.add(optionsMenuButton);
        buttonHolder.add(quitButton);

        //set up constraints for the buttonpanel and add it to the panel
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(30,0,0,0);
        mainPanel.add(buttonHolder, c);
    }

    private void setUpImage()
    {
        try{
            ImageHolderPanel image = new ImageHolderPanel(ImageIO.read(new File(Config.MAIN_MENU_HEADER_IMAGE)));
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0;
            mainPanel.add(image, c);
        }
        catch(IOException e)
        {
            System.out.println("Could not find image for main menu in memory");
        }
    }

    private void setUpMadeByText()
    {
        //create text area to show who made me :)
        JTextPane textArea = new JTextPane();
        textArea.setContentType("text/html");
        textArea.setSize(Config.MAIN_MENU_WIDTH, 100);
        textArea.setText(
                "<html>" +
                        "<pre>" +
                        "<b>Made by:</b><br />" +
                        "Bianca Iancu     Jonty Small<br />" +
                        "Stefan Kischel   Aleksandra Smuda<br />" +
                        "Jan Sainio       Nik Vaessen<br /><" +
                        "</pre>" +
                "</html>");
        textArea.setEditable(false);
        textArea.setOpaque(false);

        //set gridbragconstraints and add it the the main panel
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 2;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(textArea, c);
    }

    private void setUpActionListeners()
    {
        //actionlistener for the singlePlayerWindow
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startDisplaying(singlePlayerName);
            }
        });

        //actionlistener for the mutliplayer window
        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startDisplaying(multiPlayerName);
            }
        });

        //actionlistener for the high score window
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startDisplaying(highScoreName);
            }
        });

        //actionlistener for the bot window
        botButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startDisplaying(botWindowName);
            }
        });

        //actionlistener for the option menu
        optionsMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startDisplaying(optionMenuName);
            }
        });

        //actionlistener for the quit button
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    public void setUpAncestorListeners()
    {
        singlePlayerWindow.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent ancestorEvent) {

            }

            @Override
            public void ancestorRemoved(AncestorEvent ancestorEvent) {
                System.out.println("This should get us back to the main menu");
                startDisplaying(mainMenuName);
            }

            @Override
            public void ancestorMoved(AncestorEvent ancestorEvent) {

            }
        });
    }

    private void startDisplaying(String nameOfPanelToDisplay)
    {
        cardLayout.show(cardPanel, nameOfPanelToDisplay);
    }

}
