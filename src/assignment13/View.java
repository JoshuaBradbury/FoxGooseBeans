package assignment13;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame implements Observer {

	private Controller controller;

	private JPanel buttons;
	private Display gameView;
	private int score;

	public View(Controller controller) {
		super("Fox, Goose and Bag of Beans");
		this.controller = controller;
		initWidgets();
		pack();
		setSize(750, 500);
		setMinimumSize(new Dimension(750, 75));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void initWidgets() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		gameView = new Display(this);

		buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

		add(gameView);
		add(buttons);

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(controller);
		resetButton.setActionCommand("RESET");
		buttons.add(resetButton);
		
		buttons.add(new JLabel("Boat: "));

		JButton boatLeftButton = new JButton("<");
		JButton boatRightButton = new JButton(">");

		boatLeftButton.addActionListener(controller);
		boatLeftButton.setActionCommand("BOAT LEFT");
		boatRightButton.addActionListener(controller);
		boatRightButton.setActionCommand("BOAT RIGHT");

		buttons.add(boatLeftButton);
		buttons.add(boatRightButton);

		for (Characters character : Characters.values()) {
			buttons.add(new JLabel(character.niceName() + ": "));

			JButton leftButton = new JButton("<");
			JButton rightButton = new JButton(">");

			leftButton.addActionListener(controller);
			leftButton.setActionCommand(character.name() + " LEFT");
			rightButton.addActionListener(controller);
			rightButton.setActionCommand(character.name() + " RIGHT");

			buttons.add(leftButton);
			buttons.add(rightButton);
		}
	}

	@Override
	public void update(Observable observable, Object o) {
		String command = (String) o;
		String[] commandParts = command.split(" ");
		
		if (command.equalsIgnoreCase("WIN")) {
			gameView.win();
			setTitle("You win!");
			return;
		}
		
		if (command.equalsIgnoreCase("RESET")) {
			gameView.reset();
			score = 0;
			setTitle("Fox, Goose and Bag of Beans");
			gameView.revalidate();
			gameView.repaint();
			return;
		}
		
		if (command.equalsIgnoreCase("GAME OVER")) {
			gameView.gameOver();
			setTitle("Game Over. Predator ate prey");
			return;
		}
		
		if (commandParts[0].equalsIgnoreCase("BOAT")) {
			gameView.moveBoat();
			score--;
			setTitle("Score: " + score);
		} else if (commandParts[1].equalsIgnoreCase("RIGHT"))
			gameView.moveCharacterRight(Characters.getByName(commandParts[0]));
		else if (commandParts[1].equalsIgnoreCase("LEFT"))
			gameView.moveCharacterLeft(Characters.getByName(commandParts[0]));
		else if (commandParts[1].equalsIgnoreCase("BOAT"))
			gameView.addToBoat(Characters.getByName(commandParts[0]));
	}
}