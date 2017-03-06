package preliminaries;

public class Main {

	public static void main(String[] args) {
		
		Model model = new Model(5, 0);
		Controller controller = new Controller(model);
		View ui = new View(controller);
		controller.setView(ui);
		ui.setVisible(true);
	
		model.addObserver(ui);
		
		model.run();
	}
}
