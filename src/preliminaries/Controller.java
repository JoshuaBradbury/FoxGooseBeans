package preliminaries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller implements ActionListener, ChangeListener {

	private Model model;
	private View view;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	@Override
	public void stateChanged(ChangeEvent ce) {
		if (view.getSpeedSlider() == ce.getSource()) {
			model.setSpeed(view.getSpeedSlider().getValue());
		} else {
			model.setXCoord(view.getXCoordSlider().getValue());
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		view.reset();
		model.reset();
	}
}
