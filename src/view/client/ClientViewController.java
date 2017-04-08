package view.client;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.CalendarCalculator;

public class ClientViewController {
	
	private FrameMain f;
	private PanelCalendar pc;
	private PanelMenu pm;
	private PanelCreate pcr;
	private PanelDay pd;
	private PanelWeek pw;
	private PanelReservation pa;
	
	private Controller controller;
	
	public static final int PANEL_DAY = 1;
	public static final int PANEL_RESERVATION = 2;
	public static final int PANEL_WEEK = 3;
	public static final int PANEL_CREATE = 4;
	
	public ClientViewController(Controller controller, int year, int month, int day) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		
		f = new FrameMain();
		pc = new PanelCalendar(controller, year, month, day);
		pm = new PanelMenu(controller, year, month, day);
		pcr = new PanelCreate(controller, year, month, day);
		pa = new PanelReservation(controller);
		pw = new PanelWeek();
		pd = new PanelDay();
		
		f.setLeftPanel(pc);
		f.setTopPanel(pm);
		f.setRightPanel(pd);
	}
	
	public void updateAll(int year, int month, int day) {
		//ArrayList<Activity> act = new ArrayList<>();
		//while(activity != null && activity.hasNext())
		//	act.add(activity.next());
		pc.update(month, year, day);
		pa.update(month, day, year);
		pm.update(month, day, year);
		pd.update(month, day, year);
		pw.update(month, day, year);
	}
	
	public void setMainPanel(int panelConstant) {
		switch(panelConstant) {
			case ClientViewController.PANEL_DAY :
				f.setRightPanel(pd);
				pc.enableBtnCreate();
				break;
		     case ClientViewController.PANEL_RESERVATION :
				f.setRightPanel(pa);
				pc.enableBtnCreate();
				break;
				
			case ClientViewController.PANEL_CREATE :
				f.setRightPanel(pcr);
				pm.unselectToggleBtns();
				break;
			case ClientViewController.PANEL_WEEK:
				f.setRightPanel(pw);
				pc.enableBtnCreate();
				break;
		}
	}
/*
	public boolean isTaskFiltered() {
		return pc.isTaskSelected();
	}

	public boolean isEventFiltered() {
		return pc.isEventSelected();
	} */
}
