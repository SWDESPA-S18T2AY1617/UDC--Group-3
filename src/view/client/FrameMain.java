package view.client;

import javax.swing.*;

public class FrameMain extends JFrame {

	private JPanel topPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;

	public FrameMain() {


		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setSize(900, 600);

		topPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();

		this.add(topPanel);
		this.add(leftPanel);
		this.add(rightPanel);

		topPanel.setBounds(0, 0, 895, 60);
		leftPanel.setBounds(0, 60, 230, 830);
		rightPanel.setBounds(230, 60, 670, 830);

		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//testPanels();
	}
	
	public void setFTitle(String n) {
		
		this.setTitle(n);
	}

	public void setTopPanel(JPanel panel) {
		topPanel.removeAll();
		topPanel.add(panel);
		topPanel.repaint();
		revalidate();
	}

	public void setLeftPanel(JPanel panel) {
		leftPanel.removeAll();
		leftPanel.add(panel);
		leftPanel.repaint();
		revalidate();
	}

	public void setRightPanel(JPanel panel) {
		rightPanel.removeAll();
		rightPanel.add(panel);
		rightPanel.repaint();
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