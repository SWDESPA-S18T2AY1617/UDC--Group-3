package values;

public enum DayHeader {

	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

	public String toString() {
		switch (this) {
		case SUNDAY:
			return "S";
		case MONDAY:
			return "M";
		case TUESDAY:
			return "T";
		case WEDNESDAY:
			return "W";
		case THURSDAY:
			return "T";
		case FRIDAY:
			return "F";
		case SATURDAY:
			return "S";

		default:
			return "";
		}
	}
}
