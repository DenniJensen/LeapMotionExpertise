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

	public void setWidth(int width) {
		this.width = width;
	}

	public void draw() {
		stroke(255);
		line(0, height/2, width, height/2);
		line(width / 2, 0, width / 2, height);
		drawHotplates();
		if (mousePressed) {
			line(mouseX,mouseY,pmouseX,pmouseY);
		}

		if (keyPressed) {
			background(0);
		}
	}

	private void drawHotplates() {
		final int X_LEFT = width / 4;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 4;
		final int Y_BOTTON = Y_TOP + height / 2;
		final int RADIUS = height / 3;
		ellipse(X_LEFT, Y_TOP, RADIUS, RADIUS);
		ellipse(X_RIGHT, Y_TOP, RADIUS, RADIUS);
		ellipse(X_LEFT, Y_BOTTON, RADIUS, RADIUS);
		ellipse(X_RIGHT, Y_BOTTON, RADIUS, RADIUS);
	}
	
}