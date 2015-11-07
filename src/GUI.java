import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;


public class GUI extends JFrame{
	//declares contants
	//size of gui(pixel*pixel)
	private final int WIDTH  = 1000;
	private final int HEIGHT = 800;
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
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Method which sets up the main, game and info panels
	 */
	private void setUpPanels(){
		//create and add mainpanel to frame
        mainPanel = new JPanel();
		this.add(mainPanel);
        //call the method which creates the GamePanel ands adds it to the main panel
        setUpGamePanel();
        //call the method which creates the InfoPanel ands adds it to the main panel
        //setUpInfoPanel();
	}

    /**
     *	sets up the GamePanel
     */
    private void setUpGamePanel(){
        //creates the GamePanel board
        GamePanel gamePanel = new GamePanel(board);
        mainPanel.add(gamePanel);
        //requests focus so input works
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        //add the input manager
        gamePanel.addKeyListener(input);
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