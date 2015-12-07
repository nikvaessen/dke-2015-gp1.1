package gui;

import tetris.Board;
import tetris.InputController;

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
import java.util.HashMap;

/**
 * Created by Aleksandra and Nik on 2015-11-10.
 */
public class MainMenu extends JFrame {
    //Every panel the we want to potentially display
    private JPanel              mainPanel;          //holds the image header and all the buttons
    private SinglePlayerWindow  singlePlayerWindow; //holds the window where tetris singleplayer can be played in
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

    //variables needed for panel rotation in the CardLayout
    private JPanel cardPanel; //holds all panels to switch between
    private CardLayout cardLayout; //holds the layout manager
    private HashMap<String, JPanel> panels;

    //the buttons to go to each panel
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton optionsMenuButton;
    private JButton highScoresButton;
    private JButton botButton;
    private JButton quitButton;

    public MainMenu() {
        //set up the panels and their buttons and the image header
        this.panels = new HashMap<String, JPanel>();
        setUpMainPanel();
        setUpOtherPanels();
        setUpCardLayout();

        //and set the behavior of the main menu and start displayed the main menu
        //set behavior of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pentris");
        this.setResizable(false);

        //set icon image in taksbar
        try{
            this.setIconImage(ImageIO.read(new File(Config.ICON_FILE_PATH)));
        }
        catch(IOException e)
        {
            System.out.println("Could not load icon for task bar from memory");
        }
        startDisplaying(mainMenuName);
        this.setVisible(true);
    }

    private void setUpMainPanel()
    {
        mainPanel = new JPanel(); //to hold image and buttons
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(Config.MAIN_MENU_WIDTH, Config.MAIN_MENU_HEIGHT));
        setUpImage();
        setUpButtons();
        setUpActionListeners();
        setUpMadeByText();

        //add mainpanel to hashmap
        this.panels.put(mainMenuName, mainPanel);
        this.add(mainPanel);
    }

    private void setUpOtherPanels()
    {
        singlePlayerWindow = new SinglePlayerWindow(this);
        highScoreWindow = new HighScoreWindow(this);
        botWindow = new BotWindow(this);
        multiPlayerWindow = new MultiPlayerWindow(this);
        //add panels to hashmap
        this.panels.put(singlePlayerName, singlePlayerWindow);
        this.panels.put(highScoreName, highScoreWindow);
        this.panels.put(botWindowName, botWindow);
       // this.panels.put(multiPlayerName, multiPlayerWindow);
        //// TODO: 16-11-15 add the other panels when they are done
    }

    private void setUpCardLayout()
    {
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(this.cardLayout);
        //this.cardPanel.setPreferredSize(Config.MAIN_MENU_DIMENSION);
        //set up the cardbox layout and add all panels
        cardPanel.add(mainMenuName, mainPanel);
        cardPanel.add(singlePlayerName, singlePlayerWindow);
        cardPanel.add(highScoreName, highScoreWindow);
        cardPanel.add(botWindowName, botWindow);
        //cardPanel.add(multiPlayerName, multiPlayerWindow);

        // TODO: 16-11-15 add the others panels when they're done
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
        c.gridx = 0;
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
            c.gridx = 0;
            c.gridy = 0;
            c.weighty = 0.5;
            c.weightx = 1;
            mainPanel.add(image);
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

        //actionlistener for the mutltiplayer window
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


    private void startDisplaying(String nameOfPanelToDisplay)
    {
        cardLayout.show(cardPanel, nameOfPanelToDisplay);
        cardPanel.setPreferredSize(panels.get(nameOfPanelToDisplay).getPreferredSize());
        this.pack();
    }

    public void goBackToMainMenu()
    {
        startDisplaying(mainMenuName);
    }

}
