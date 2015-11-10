import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class GUI extends JFrame{
	//declares contants
	//size of gui(pixel*pixel)
	private final int WIDTH  = 400;
	private final int HEIGHT = 500;
	//size of in-game pentris board
	private final int GAME_HEIGHT = 15;
	private final int GAME_WIDTH  = 10;

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

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		GamePanel gp = new GamePanel(board);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gp.setFocusable(true);
		gp.requestFocus();
		gp.addKeyListener(input);
		this.add(gp, gbc);

		/*JLabel test = new JLabel("test");
		mainPanel.add(test);
		mainPanel.setVisible(true);
	}

	/**
	*	sets up the infoPanel
	*/
	/*private void setUpInfoPanel(){
		//instantiates the panels for the button and labe and the block
		this.button = new JButton("Click me to print hello");
		this.infoLabel = new JLabel("This is the info panel");
		//adds components to the panel
		infoPanel.add(button);
		infoPanel.add(infoLabel);*/
	}
}