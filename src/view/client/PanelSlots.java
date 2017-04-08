package view.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PanelSlots extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelActivity;
	private JPanel panelTime;
	private JPanel container;
	private JPanel[] timeSlot;
	private JPanel[] activitySlot;
	private JLabel[] seeTime;
	private JScrollPane scrollAct;
	private String[] milTime;
	private GridBagConstraints[] gbc;
	private GridBagConstraints[] gb;
	
	public PanelSlots() {
		
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
			
			panelTime = new JPanel();
			panelTime.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelTime.setBackground(Color.WHITE);
			
			panelActivity = new JPanel();
			panelActivity.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelActivity.setBackground(Color.WHITE);
			
			container.add(panelTime);
			container.add(panelActivity);
			
			scrollAct = new JScrollPane(container);
			scrollAct.getViewport().setBackground(Color.WHITE);
			scrollAct.getVerticalScrollBar().setUnitIncrement(20);

			
			GridBagLayout panelTimeGB = new GridBagLayout();
			panelTimeGB.columnWidths = new int[] {115};
			panelTimeGB.rowHeights = new int[] {trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize, trSize};
			panelTimeGB.columnWeights = new double[]{1.0};
			panelTimeGB.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
			panelTime.setLayout(panelTimeGB);
			
			GridBagLayout panelActivityGB = new GridBagLayout();
			panelActivityGB.columnWidths = new int[]{510};
			panelActivityGB.rowHeights = new int[]{activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize, activitySize};
			panelActivityGB.columnWeights = new double[]{1.0};
			panelActivityGB.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
			panelActivity.setLayout(panelActivityGB);
			
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
				panelTime.add(timeSlot[i], gb[i]);
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
				
				panelActivity.add(activitySlot[i],gbc[i]);
			}
			
		}
		
		private void addPlaceComp() {

			this.add(scrollAct);
			
			scrollAct.setBounds(0, 0, 665, 485);
		}
		
		public void update(int month, int day, int year) {
			
			this.setPanelValuesNull();
			//this.setPanelValues(month, day, year, activity);
			
		}
	/*	
		public void setPanelValues(int month, int day, int year, Iterator<Activity> activity) {
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
				activitySlot[i].setBackground(Color.WHITE);
				activitySlot[i].removeAll();
				activitySlot[i].revalidate();
				activitySlot[i].repaint();
			}
			
		}

}
