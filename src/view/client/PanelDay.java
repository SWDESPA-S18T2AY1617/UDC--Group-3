package view.client;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;


public class PanelDay extends JPanel {
	
	private JPanel activity;
	private JPanel time;
	private JPanel container;
	private JPanel[] timeSlot;
	private JPanel[] activitySlot;
	private JLabel[] seeTime;
	private JScrollPane scrollAct;
	private String[] milTime;
	private GridBagConstraints[] gbc;
	private GridBagConstraints[] gb;
	
	public PanelDay() {
		
		this.setLayout(null);
		this.setSize(new Dimension (670, 830));
		this.initComp();
		this.initTimeSlot();
		this.initActivitySlot();
		this.addPlaceComp();
	}
	
	private void initComp() {
		
		int trSize = 60;
		int activitySize = 30;
		
		container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		time = new JPanel();
		time.setBorder(new LineBorder(new Color(0, 0, 0)));
		time.setBackground(Color.WHITE);
		
		activity = new JPanel();
		activity.setBorder(new LineBorder(new Color(0, 0, 0)));
		activity.setBackground(Color.WHITE);
		
		container.add(time);
		container.add(activity);
		
		scrollAct = new JScrollPane(container);
		scrollAct.getViewport().setBackground(Color.WHITE);
		scrollAct.getVerticalScrollBar().setUnitIncrement(20);
		
		GridBagLayout activity_time = new GridBagLayout();
		activity_time.columnWidths = new int[] {115};
		activity_time.rowHeights = new int[] {trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize};
		activity_time.columnWeights = new double[]{1.0};
		activity_time.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		time.setLayout(activity_time);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{510};
		gridBagLayout.rowHeights = new int[]{activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		activity.setLayout(gridBagLayout);
		
	}
	
	private void initTimeSlot() {
		
		timeSlot = new JPanel[24];
		seeTime = new JLabel[24];
		gb = new GridBagConstraints[24];
		milTime = new String[] {	
				"00:00", "1:00","2:00","3:00", "4:00", "5:00" ,"6:00",  
				"7:00",  "8:00",  "9:00",  "10:00",  "11:00",  "12:00",  
				"13:00", "14:00",  "15:00",  "16:00",  "17:00",  "18:00", 
				"19:00",  "20:00","21:00",  "22:00", "23:00"
				};	
		
		for(int i = 0; i < 24; i++) {
			
			Border border;
			
			timeSlot[i] = new JPanel();
			
			border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
			
			timeSlot[i].setBorder(border);
			timeSlot[i].setBackground(Color.WHITE);
			
			seeTime[i] = new JLabel(milTime[i]);
			seeTime[i].setFont(new Font("Sans Serif", Font.PLAIN, 14));
			timeSlot[i].add(seeTime[i]);
			
			gb[i] = new GridBagConstraints();
			gb[i].insets = new Insets(0, 0, 0, 0);
			gb[i].fill = GridBagConstraints.BOTH;
			gb[i].gridx = 0;
			gb[i].gridy = i;
			time.add(timeSlot[i], gb[i]);
		}
		
	}
	
	private void initActivitySlot() {
		
		activitySlot = new JPanel[48];
		gbc = new GridBagConstraints[48];
		
		for(int i = 0; i < 48; i++) {
			
			Border border; 
			
			activitySlot[i] = new JPanel();
			
			border = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.DARK_GRAY);
		
			activitySlot[i].setBorder(border);
			activitySlot[i].setBackground(Color.LIGHT_GRAY);
			
			gbc[i] = new GridBagConstraints();
			gbc[i].insets = new Insets(0, 0, 0, 0);
			gbc[i].fill = GridBagConstraints.BOTH;
			gbc[i].gridx = 0;
			gbc[i].gridy = i;
			
			activity.add(activitySlot[i],gbc[i]);
		}
		
	}
	private void addPlaceComp() {
		
		this.add(scrollAct);
		scrollAct.setBounds(0, 0, 665, 510);
	}

	public void update(int month, int day, int year) {
		
		this.setPanelValuesNull();
		//this.setPanelValues(month, day, year, activity);
		
	}
	/*
	public void setPanelValues(int month, int day, int year) {
		System.out.println("+++++++++");

		ArrayList<Activity> activityList = new ArrayList<>();
		
		if(activity != null) {
			while (activity != null && activity.hasNext()) {
				Activity a = activity.next();
				activityList.add(a);
				System.out.println(a.getName());
				}
			
			for(int i = 0; i < activityList.size(); i++) {
				if(activityList.get(i).isMonth(month) && activityList.get(i).isDay(day) && activityList.get(i).isYear(year)) {
					
					setEvent(activityList.get(i));
				}
			}
		}	
	}
	
	public void setEvent(Activity act) {
		
		if(act instanceof Event) {
			
			JLabel evnt = new JLabel(act.getName());
			evnt.setFont(new Font("Sans Serif", Font.BOLD, 14));
			evnt.setForeground(Color.white);
		
			int start = act.getStartHour() * 2 + act.getStartMinute() / 30;
			int end = act.getEndHour() * 2 + act.getEndMinute() / 30;
			
			activitySlot[start].add(evnt);
			
			while (start < end) {
				
				activitySlot[start].setBackground(act.getColor());

				start++;
				}
			}
			else
			{
				int index = act.getStartHour() * 2 + act.getStartMinute() / 30;
				JLabel evnt = new JLabel(act.getName());
				evnt.setFont(new Font("Sans Serif", Font.BOLD, 14));
				evnt.setForeground(Color.white);
				
				activitySlot[index].add(evnt);
				activitySlot[index].setBackground(act.getColor());
				
			}
		}
	*/
	public void setPanelValuesNull() {
		
		for (int i = 0; i < 48; i++) {
			activitySlot[i].setBackground(Color.LIGHT_GRAY);
			activitySlot[i].removeAll();
			activitySlot[i].revalidate();
			activitySlot[i].repaint();
		}
		
	}
}
