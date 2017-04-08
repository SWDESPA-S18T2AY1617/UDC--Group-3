package view.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.metal.MetalTabbedPaneUI;

public class PanelWeek extends JPanel {

	private JTabbedPane day;
	private PanelSlots monday;
	private PanelSlots tuesday;
	private PanelSlots wednesday;
	private PanelSlots thursday;
	private PanelSlots friday;
	
	public PanelWeek() {
		
		this.setLayout(new GridLayout(1,1));
		this.setPreferredSize(new Dimension(670, 830));
		this.setBackground(Color.decode("#F5E1DF"));
		
		this.initComp();
		this.addPlaceComp();

	}
	
	private void initComp() {
		
		day = new JTabbedPane();
		day.setPreferredSize(new Dimension(670, 830));
		day.setBackground(Color.decode("#dccac8"));
		day.setForeground(Color.WHITE);
		
		monday = new PanelSlots();
		tuesday = new PanelSlots();
		wednesday = new PanelSlots();
		thursday = new PanelSlots();
		friday = new PanelSlots();
		
		day.add("Monday", monday);
		day.add("Tuesday", tuesday);
		day.add("Wednesday",wednesday);
		day.add("Thursday", thursday);
		day.add("Friday", friday);
		
	}
	
	private void addPlaceComp() {
		
		this.add(day);
	}	
	public void update(int month, int day, int year) {
		
		monday.update(month, day, year);
		tuesday.update(month, day + 1, year);
		wednesday.update(month, day + 2, year);
		thursday.update(month, day + 3, year);
		friday.update(month, day + 4, year);
		
	}
}
