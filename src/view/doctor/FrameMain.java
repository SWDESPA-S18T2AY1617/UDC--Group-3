package view.doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

public class FrameMain extends JFrame {

	private JPanel topPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;

	private BorderLayout bl;
	private Container mainPane;
	public FrameMain() {
		super("My Productivity Tool");

		bl = new BorderLayout();
		mainPane = this.getContentPane();
		
		//this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(bl);
		
		this.setMinimumSize(new Dimension(950, 600));

		topPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();

		//this.add(topPanel);
		//this.add(leftPanel);
		//this.add(rightPanel);

		//mainPane.add(topPanel, BorderLayout.PAGE_START);
		//topPanel.setBounds(0, 0, 895, 60);
		//leftPanel.setBounds(0, 60, 230, 830);
		//rightPanel.setBounds(230, 60, 670, 830);

		//topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		//topPanel.setMinimumSize(new Dimension(900, 50));
		//leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		//rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void setTopPanel(JPanel panel) {
		//topPanel.removeAll();
		//topPanel.add(panel);
		//topPanel.repaint();
		mainPane.add(panel, BorderLayout.PAGE_START);
		mainPane.repaint();
		revalidate();
	}

	public void setLeftPanel(JPanel panel) {
		//leftPanel.removeAll();
		//leftPanel.add(panel);
		//leftPanel.repaint();
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

	/** 	PANG TEST LANG 		**/
	private void testPanels() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		p1.setLayout(null);
		p2.setLayout(null);
		p3.setLayout(null);

		p1.setBorder(BorderFactory.createTitledBorder("TOP"));
		p2.setBorder(BorderFactory.createTitledBorder("LEFT"));
		p3.setBorder(BorderFactory.createTitledBorder("RIGHT"));

		this.setTopPanel(p1);
		this.setLeftPanel(p2);
		this.setRightPanel(p3);
	}
}