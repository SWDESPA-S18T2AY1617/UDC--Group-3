package view.doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

public class FrameMain extends JFrame {

	private BorderLayout bl;
	private Container mainPane;
	public FrameMain() {
		super("Doctor View");

		bl = new BorderLayout();
		mainPane = this.getContentPane();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(bl);
		
		this.setMinimumSize(new Dimension(950, 600));

		this.setLocationRelativeTo(null);
	}

	public void setTopPanel(JPanel panel) {
		mainPane.add(panel, BorderLayout.PAGE_START);
		mainPane.repaint();
		revalidate();
	}

	public void setLeftPanel(JPanel panel) {
		mainPane.add(panel, BorderLayout.WEST);
		mainPane.repaint();
		revalidate();
	}

	public void setRightPanel(JComponent panel) {
		BorderLayout layout = (BorderLayout) mainPane.getLayout();
		try {
			mainPane.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		} catch(Exception e) {
			System.out.println("oops");
		}
		JScrollPane jsp = new JScrollPane(panel);
		jsp.getViewport().setBackground(Color.RED);
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		mainPane.add(jsp, BorderLayout.CENTER);

		mainPane.repaint();
		revalidate();
	}
}