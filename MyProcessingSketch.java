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
		ellipse(56, 46, height / 4, height / 4);
		if (mousePressed) {
			line(mouseX,mouseY,pmouseX,pmouseY);
		}

		if (keyPressed) {
			background(0);
		}
	}	
	
}