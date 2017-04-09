package view.doctor;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.DoctorController;
import controller.MainController;

public class TestGUI {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainController mc = new MainController();
				DoctorController dc = new DoctorController(mc);
				if(dc.showDoctorWho()) {
					dc.createAndShowGUI();
				}
			}
		});
	}
}