package view.doctor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PanelDoctorWho extends JPanel {

	private JPanel panelWelcome;
	private JLabel lblWelcomeMessage;
	private JPanel panelBtnContainer;
	private JButton btnNew;
	private JButton btnOld;

	private JPanel panelNew;
	private JLabel lblNewInstruction;
	private JTextField tfNewName;

	private JPanel panelOld;
	private JPanel panelInput1;
	private JPanel panelInput2;
	private JLabel lblInstruction;
	private JLabel lblID;
	private JLabel lblName;
	private JTextField tfID;
	private JTextField tfName;

	private JButton btnConfirm;
	private JButton btnConfirm2;
	
	private int newDoctor;
	
	public PanelDoctorWho() {

		this.setPreferredSize(new Dimension(400, 100));
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 150));
		//this.setLocationRelativeTo(null);
		
		this.initComp();
		this.addListeners();
		
		//this.setVisible(true);
	}

	private void initComp() {
		newDoctor = -1;
		
		//mainPane = (JPanel) this.getContentPane();

		panelWelcome = new JPanel();
		panelWelcome.setLayout(new BoxLayout(panelWelcome, BoxLayout.Y_AXIS));

		lblWelcomeMessage = new JLabel("Welcome to the application, doctor. Are you new here?");
		lblWelcomeMessage.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		lblWelcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelBtnContainer = new JPanel();
		panelBtnContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBtnContainer.setLayout(new BoxLayout(panelBtnContainer, BoxLayout.X_AXIS));
		btnNew = new JButton("Yes, I'm new here.");
		btnNew.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		btnOld = new JButton("No, I've used this before");
		btnNew.setFont(new Font("Sans Serif", Font.PLAIN, 14));

		panelBtnContainer.add(btnNew);
		panelBtnContainer.add(btnOld);
		panelWelcome.add(Box.createRigidArea(new Dimension(20, 20)));
		panelWelcome.add(lblWelcomeMessage);
		panelWelcome.add(Box.createRigidArea(new Dimension(20, 10)));
		panelWelcome.add(panelBtnContainer);

		panelNew = new JPanel();
		panelNew.setLayout(new BoxLayout(panelNew, BoxLayout.Y_AXIS));
		lblNewInstruction = new JLabel("Please enter your name.");
		lblNewInstruction.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		lblNewInstruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		tfNewName = new JTextField();
		tfNewName.setPreferredSize(new Dimension(10, 50));
		tfNewName.setMaximumSize(new Dimension(450, 30));
		tfNewName.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		tfNewName.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelNew.add(Box.createRigidArea(new Dimension(20, 20)));
		panelNew.add(lblNewInstruction);
		panelNew.add(Box.createRigidArea(new Dimension(20, 10)));
		panelNew.add(tfNewName);
		panelNew.add(Box.createRigidArea(new Dimension(20, 10)));
		panelNew.add(btnConfirm);

		panelOld = new JPanel();
		panelOld.setLayout(new BoxLayout(panelOld, BoxLayout.Y_AXIS));
		lblInstruction = new JLabel("Please enter your information.");
		lblInstruction.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		lblInstruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblName = new JLabel("Name:  ");
		lblName.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		lblName.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblID = new JLabel("     ID:  ");
		lblID.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		lblName.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tfID = new JTextField();
		tfID.setPreferredSize(new Dimension(250, 30));
		tfID.setMaximumSize(new Dimension(250, 30));
		tfID.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		tfName = new JTextField();
		tfName.setPreferredSize(new Dimension(250, 30));
		tfName.setMaximumSize(new Dimension(250, 30));
		tfName.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		btnConfirm2 = new JButton("Confirm");
		btnConfirm2.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		btnConfirm2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelInput1 = new JPanel();
		panelInput1.setLayout(new BoxLayout(panelInput1, BoxLayout.X_AXIS));
		panelInput1.add(lblID);
		panelInput1.add(tfID);
		panelInput2 = new JPanel();
		panelInput2.setLayout(new BoxLayout(panelInput2, BoxLayout.X_AXIS));
		panelInput2.add(lblName);
		panelInput2.add(tfName);
		
		panelOld.add(Box.createRigidArea(new Dimension(20, 20)));
		panelOld.add(lblInstruction);
		panelOld.add(Box.createRigidArea(new Dimension(20, 10)));
		panelOld.add(panelInput1);
		panelOld.add(Box.createRigidArea(new Dimension(20, 10)));
		panelOld.add(panelInput2);
		panelOld.add(Box.createRigidArea(new Dimension(20, 10)));
		panelOld.add(btnConfirm2);
		
		//mainPane.add(panelWelcome);
		//mainPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(panelWelcome);
		setLayout(new FlowLayout(FlowLayout.CENTER));
	}
	
	public JPanel getWelcomePane() {
		return panelWelcome;
	}
	
	public int getNewDoctor() {
		return newDoctor;
	}

	public String getNewDocName() {
		return tfNewName.getText();
	}
	
	public String getOldDocName() {
		return tfName.getText();
	}
	
	public String getOldDocID() {
		return tfID.getText();
	}
	
	public void addListeners() {
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//mainPane.removeAll();
				//mainPane.add(panelNew);
				removeAll();
				add(panelNew);
				setSize(new Dimension(400, 190));
				repaint();
				revalidate();
			}
		});

		btnOld.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//mainPane.removeAll();
				//mainPane.add(panelOld);
				removeAll();
				add(panelOld);
				setSize(new Dimension(400, 220));
				repaint();
				revalidate();
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newDoctor = 1;
				JDialog dialog = (JDialog) SwingUtilities.getRoot(panelNew);
		        dialog.dispose();
			}
		});
		
		btnConfirm2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newDoctor = 0;
				JDialog dialog = (JDialog) SwingUtilities.getRoot(panelOld);
		        dialog.dispose();
			}
		});
	}
}
