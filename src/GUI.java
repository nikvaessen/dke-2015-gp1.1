import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class GUI extends JFrame{
	//declares contants
	//size of gui(pixel*pixel)
	private final int WIDTH  = 400;
	private final int HEIGHT = 500;
	//size of in-game pentris board
	private final int GAME_HEIGHT = 15;
	private final int GAME_WIDTH  = 10;

	//declares the panels
	private JPanel mainPanel;
	private JPanel gamePanel;
	private JPanel infoPanel;

	//declares the components for the game panel
	private InputController input;
	private Board board;
	
	//declares the components for the info panel
	private JLabel infoLabel;
	private JButton button;

	/**
	*	constructs a gui
	*/
	public GUI(InputController input, Board board){
		//sets up inputcontroller and board
		this.input = input;
		this.board = board;
		//method which sets up all components
		this.setUpPanels();
		//this.setUpInfoPanel();
		//sets size, title bar and close operation
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("test gui");
		this.setResizable(false);
		//makes the gui visisble
		//this.pack();
		this.setVisible(true);
	}

	private void setUpPanels(){
		mainPanel = new JPanel();
		this.add(mainPanel);

		GamePanel gamePanel = new GamePanel(board);
		mainPanel.add(gamePanel);

		gamePanel.setFocusable(true);
		gamePanel.requestFocus();
		gamePanel.addKeyListener(input);

		JLabel test = new JLabel("test");
		mainPanel.add(test);
		mainPanel.setVisible(true);
	}

	/**
	*	sets up the infoPanel
	*/
	private void setUpInfoPanel(){
		//instantiates the panels for the button and labe and the block
		this.button = new JButton("Click me to print hello");
		this.infoLabel = new JLabel("This is the info panel");
		//adds components to the panel
		infoPanel.add(button);
		infoPanel.add(infoLabel);
	}
}