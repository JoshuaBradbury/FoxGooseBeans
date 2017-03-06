package preliminaries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class View extends JFrame implements Observer {

	private JTextArea textArea;
	private JSlider speedSlider, xCoordSlider;
	private JButton reset;
	
	public View(Controller controller) {
		super("Flight Simulator");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initWidget(controller);
	}
	
	public JSlider getSpeedSlider() {
		return speedSlider;
	}
	
	public JSlider getXCoordSlider() {
		return xCoordSlider;
	}	
	
	public void reset() {
		textArea.setText("");
		speedSlider.setValue(0);
		xCoordSlider.setValue(5);
	}
	
	public void initWidget(Controller controller) {
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 1));
		textArea = new JTextArea();
		textArea.setEditable(false);

		Color c = new Color(102, 198, 233);
		textArea.setBackground(c);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		reset = new JButton("reset");
		reset.addActionListener(controller);

		speedSlider = new JSlider(JSlider.VERTICAL, 0, 10, 0);
		speedSlider.addChangeListener(controller);
		speedSlider.setMajorTickSpacing(2);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		
		xCoordSlider = new JSlider(0, Model.RUNWAY_WIDTH);
		xCoordSlider.addChangeListener(controller);
		xCoordSlider.setMajorTickSpacing(2);
		xCoordSlider.setMinorTickSpacing(1);
		xCoordSlider.setPaintTicks(true);
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel1.add(scroll);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(xCoordSlider, BorderLayout.NORTH);
		panel2.add(speedSlider, BorderLayout.CENTER);
		panel2.add(reset, BorderLayout.SOUTH);

		this.setLayout(new GridLayout(2, 1));
		this.add(panel1);
		this.add(panel2);

	}

	private void printOutput(String output) {
		textArea.append(output);
	}

	@Override
	public void update(Observable observable, Object o) {
		Model model = (Model) observable;

		String output = "";
		
		if (model.getTakeOff())
			if (model.getElevation() >= 5)
				output = "\n Plane in air";
			else
				output = "\n Take off Failed";
		else
			output = "Second : " + model.getSeconds() + "\n" + model.toString() + "\n";
		
		printOutput(output);
	}
}
