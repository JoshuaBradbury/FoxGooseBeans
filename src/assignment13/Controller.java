package assignment13;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
		case "RESET":
			model.reset();
			break;
		case "BOAT LEFT":
			if (!model.isBoatLeft())
				model.moveBoat();
			break;
		case "BOAT RIGHT":
			if (model.isBoatLeft())
				model.moveBoat();
			break;
		default:
			String[] command = ae.getActionCommand().split(" ");
			Characters character = Characters.getByName(command[0]);
			if (character != null) {
				if (command[1].equalsIgnoreCase("RIGHT"))
					model.moveCharacterRight(character);
				else if (command[1].equalsIgnoreCase("LEFT"))
					model.moveCharacterLeft(character);
			}
			break;
		}
	}
}
