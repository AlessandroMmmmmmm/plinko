

import processing.core.PApplet;

public class Plinko {

	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		drawing.windowResizable(false);
	}
	
}
