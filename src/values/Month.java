package values;

public enum Month {

	JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

	public String toString() {
		switch (this) {
		case JANUARY:
			return "January";
		case FEBRUARY:
			return "February";
		case MARCH:
			return "March";
		case APRIL:
			return "April";
		case MAY:
			return "May";
		case JUNE:
			return "June";
		case JULY:
			return "July";
		case AUGUST:
			return "August";
		case SEPTEMBER:
			return "September";
		case OCTOBER:
			return "October";
		case NOVEMBER:
			return "November";
		case DECEMBER:
			return "December";

		default:
			return "";
		}
	}

	public String toShortString() {
		switch (this) {
		case JANUARY:
			return "Jan";
		case FEBRUARY:
			return "Feb";
		case MARCH:
			return "Mar";
		case APRIL:
			return "Apr";
		case MAY:
			return "May";
		case JUNE:
			return "Jun";
		case JULY:
			return "Jul";
		case AUGUST:
			return "Aug";
		case SEPTEMBER:
			return "Sep";
		case OCTOBER:
			return "Oct";
		case NOVEMBER:
			return "Nov";
		case DECEMBER:
			return "Dec";

		default:
			return "";
		}
	}
	
	public static String[] getArrShortString() {
		String[] str = new String[12];
		
		for(int i = 0; i < 12; i++) {
			str[i] = Month.values()[i].toShortString();
		}
		
		return str;
	}
	
	public static int getIndexShortString(String strMonthShort) {
		if(strMonthShort.equalsIgnoreCase("Jan")) 
			return 0;
		else if(strMonthShort.equalsIgnoreCase("Feb"))
			return 1;
		else if(strMonthShort.equalsIgnoreCase("Mar"))
			return 2;
		else if(strMonthShort.equalsIgnoreCase("Apr"))
			return 3;
		else if(strMonthShort.equalsIgnoreCase("May"))
			return 4;
		else if(strMonthShort.equalsIgnoreCase("Jun"))
			return 5;
		else if(strMonthShort.equalsIgnoreCase("Jul"))
			return 6;
		else if(strMonthShort.equalsIgnoreCase("Aug"))
			return 7;
		else if(strMonthShort.equalsIgnoreCase("Sep"))
			return 8;
		else if(strMonthShort.equalsIgnoreCase("Oct"))
			return 9;
		else if(strMonthShort.equalsIgnoreCase("Nov"))
			return 10;
		else if(strMonthShort.equalsIgnoreCase("Dec"))
			return 11;
		else return -1;
	}
}
