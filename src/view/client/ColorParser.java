package view.client;
import java.awt.Color;

public class ColorParser {
	public static String getColor(Color c) {
		if (c.equals(Color.BLUE))
			return "blue";
		if (c.equals(Color.BLACK))
			return "black";
		if (c.equals(Color.RED))
			return "red";
		if (c.equals(Color.GREEN))
			return "green";
		if (c.equals(Color.YELLOW))
			return "yellow";
		if (c.equals(Color.ORANGE))
			return "orange";
		if (c.equals(Color.LIGHT_GRAY))
			return "lightgray";
		if (c.equals(Color.DARK_GRAY))
			return "darkgray";
		return "";
	}

	public static Color getColor(String s) {
		if(s.equalsIgnoreCase("blue"))
			return Color.BLUE;
		if(s.equalsIgnoreCase("black"))
			return Color.BLACK;
		if(s.equalsIgnoreCase("red"))
			return Color.RED;
		if(s.equalsIgnoreCase("green"))
			return Color.GREEN;
		if(s.equalsIgnoreCase("yellow"))
			return Color.YELLOW;
		if(s.equalsIgnoreCase("orange"))
			return Color.ORANGE;
		if(s.equalsIgnoreCase("lightgray"))
			return Color.LIGHT_GRAY;
		if(s.equalsIgnoreCase("darkgray"))
			return Color.DARK_GRAY;
		return Color.BLACK;		
	}
}