package final_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GameGraphic extends JFrame implements ActionListener {
    private GameController gameController;
	private JPanel topPanel, centerPanel, grid;
    private JTextField inputBox;
	private final int WIDTH = 500;
	private final int HEIGHT = 350;
	private JButton[][] buttons = new JButton[3][3];

    public GameGraphic(){
		//gameController = new GameController(); 
    }

	public void setUpGame(){
		// JFrame
		setTitle("Tic Tac Toe");
		setSize(new Dimension(WIDTH,HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
 
		// set panels  
	    setTopPanel();
 		setCenterPanel();
	
		// adding things
		this.add(topPanel, BorderLayout.PAGE_START);
		this.add(centerPanel, BorderLayout.CENTER);
	
		setVisible(true);
        System.out.println("Game Graphic Initialized");
	}

    private void setTopPanel() {
		// inputBox
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		inputBox = new JTextField();
		inputBox.setPreferredSize(new Dimension(200,40));
		inputBox.setHorizontalAlignment(JTextField.RIGHT);
		inputBox.setEnabled(false);
		topPanel.add(inputBox);
	}

    private void setCenterPanel() {
		centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		grid = new JPanel(new GridLayout(0, 3));
        grid.setPreferredSize(new Dimension(350,200));
        
        for (int i = 1; i < 10; i++) {
			JButton button = new JButton(); 
			button.addActionListener(this);
			grid.add(button);
			buttons[(i - 1) / 3][(i - 1) % 3] = button;
		}
		centerPanel.add(grid);
	}

    public void actionPerformed(ActionEvent e) {
		if(gameController.getTurn() == 0){ //is the players turn
			JButton button = (JButton)e.getSource();
			button.setText("X");
			button.setEnabled(false);
			
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if(buttons[i][j] == button){
						GameController.markButton(i, j);
					}
				}
			}
		}
    }

	public void gameOver(String winner){
		String outcome;
		//System.out.println("Game Over");
		if(winner.equals("Player")){
			outcome = "You Win!";
		} else if (winner.equals("Computer")){
			outcome = "You Lose!";
		} else {
			outcome = "It's a Draw!";
		}
		
		String[] options = {"Play again", "Done"};

        // Show the option dialog
        int choice = JOptionPane.showOptionDialog(
                null, // Parent component (null = center on screen)
                outcome, // Message
                "Game Over", // Title
                JOptionPane.YES_NO_OPTION, // Option type
                JOptionPane.QUESTION_MESSAGE, // Message type
                null, // Icon (null = default)
                options, // Custom button labels
                options[0] // Default selected button
        );
		this.dispose();

		if(choice == JOptionPane.YES_OPTION){
			new GameController(true);
		} else {
			GameController.currentPlayer.writeDataFile();
			System.exit(0);
		}
	}

	public void updateMessageBox(String name){
		inputBox.setHorizontalAlignment(JTextField.CENTER);
		inputBox.setFont(new Font("Arial", Font.BOLD, 16));
		inputBox.setBackground(Color.BLACK);
		inputBox.setText(name);
	}

	public void clickButton(int row, int col){
		JButton button = buttons[row][col];
		button.setText("O");
		button.setEnabled(false);
	}

}
