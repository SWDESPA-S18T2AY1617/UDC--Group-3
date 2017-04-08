package view.secretary;

import javax.swing.*;

public class FrameMain extends JFrame{
	
	private JPanel panelTop;
	private JPanel panelLeft;
	private JPanel panelRight;

	public FrameMain(){

		super("Hilary Clinton");
                
        this.setSize(910, 610);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelTop = new JPanel();
		panelLeft = new JPanel();
		panelRight = new JPanel();

		this.add(panelTop);
		this.add(panelLeft);
		this.add(panelRight);

		panelTop.setBounds(0, 0, 900, 75);
		panelLeft.setBounds(0, 75, 350, 500);
		panelRight.setBounds(350, 75, 550, 500);

		panelTop.setLayout(null);
		panelLeft.setLayout(null);
		panelRight.setLayout(null);

		this.setVisible(true);
	}
	
	public void setTop(JPanel panel){
		panelTop.removeAll();
		panelTop.add(panel);
		panelTop.repaint();
		revalidate();

	}
	public void setLeft(JPanel panel){
		panelLeft.removeAll();
		panelLeft.add(panel);
		panelLeft.repaint();
		revalidate();

	}
	public void setRight(JPanel panel){
		panelRight.removeAll();
		panelRight.add(panel);
		panelRight.repaint();
		revalidate();
	}
	public void refreshPanels(){
		panelTop.repaint();
		panelLeft.repaint();
		panelRight.repaint();
		revalidate();
	}

}
