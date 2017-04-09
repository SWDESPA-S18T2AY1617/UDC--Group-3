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
		
		MainController mc = new MainController();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DoctorController dc = new DoctorController(mc);
				if(dc.showDoctorWho()) {
					dc.createGUI();
					dc.showGUI();
				}
				DoctorController dc2 = new DoctorController(mc);
				if(dc2.showDoctorWho()) {
					dc2.createGUI();
					dc2.showGUI();
				}
				
				System.out.println("hhhhhuuuuuuuuuuhhhhhhuuuuuuu");
			}
			
		});
		
		
		
	}
}