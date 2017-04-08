package values;

public enum AppStrings {
	NOEVENTS;

	public String toString() {
		switch (this) {
		case NOEVENTS:
			return "No Appointments yet.";
		default:
			return "";
		}
	}
}
