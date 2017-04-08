package values;

public enum Status {
	NOT_DONE, DONE;
	
	public String toString() {
		// TODO Auto-generated method stub
		switch (this) {
		case NOT_DONE: return "Not Done";
		case DONE: return "Done";
		}
		return "";
	}
}
