import processing.core.PApplet;

public class MyProcessingSketch extends PApplet {

	public void setup() {
		System.out.print("Test");
		int width = displayWidth;
		int heigt = displayHeight;
		System.out.println("Width: " + width + " " + "heigt: " + heigt);

		size(width,heigt);
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