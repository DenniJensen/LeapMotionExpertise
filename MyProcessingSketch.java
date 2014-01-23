import processing.core.PApplet;

public class MyProcessingSketch extends PApplet {
	private int width;
	private int height;

	public void setup() {
		System.out.print("Test");
		width = displayWidth;
		height = displayHeight;
		System.out.println("Width: " + width + " " + "heigt: " + height);

		size(width, height);
		background(0);
	}

	public void draw() {
		stroke(255);
		if (mousePressed) {
			line(mouseX,mouseY,pmouseX,pmouseY);
		}

		if (keyPressed) {
			background(0);
		}
	}	
	
}