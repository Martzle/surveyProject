package finalproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class universalGUIConstants {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static Color getForegroundColour() {
		// set color for the foreground
		Color foregroundColour = new Color(3,83,103);
		return foregroundColour;		
	}
	
	public static Color getBackgroundColour() {
		// set color for the backgorund
		Color backgroundColor = new Color(40,40,40);
		return backgroundColor;
		
	}
	
	public static int getWidth() {
		// set a given width
		double width = screenSize.getWidth()-500;
		return (int) width;
	}
	
	public static int getLength() {
		// set a given length
		double height = screenSize.getHeight()-500;
		return (int) height;
	}
	
	public static Font getTitleFont() {
		// make a consistent font for titles
		Font titleFont = new Font("Serif",Font.BOLD,48);
		
		return titleFont;
	}
	
	public static Font getMainFont() {
		// make a consistent font for everything else 
		Font mainFont = new Font("Serif",Font.BOLD,20);
		
		return mainFont;
	}
	
}
