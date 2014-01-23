import processing.core.PApplet;

public class MyProcessingSketch extends PApplet {

	public void setup() {
		System.out.print("Test");
		size(400,400);
		background(0);
	}

	public void draw() {
		stroke(255);
		if (mousePressed) {
			line(mouseX,mouseY,pmouseX,pmouseY);
		}
	}
}