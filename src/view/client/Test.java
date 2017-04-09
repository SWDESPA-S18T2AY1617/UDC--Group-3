package view.client;

import controller.ClientController;
import controller.MainController;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MainController mc = new MainController();
		ClientController cc = new ClientController(mc);
	}

}
