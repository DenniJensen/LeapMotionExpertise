import processing.core.PApplet;
import com.leapmotion.leap.Controller;

public class ProcessingSketch extends PApplet {
	private int width;
	private int height;
	private Field hoveredField; //TODO maybe from controller
	private Controller leapController;
	private ControlListener controlListener;
	private Herd model;


	public void setup() {
		hoveredField = Field.NO_FIELD;
		width = displayWidth;
		height = displayHeight;
		size(width, height);
		this.controlListener = new ControlListener(this);
		this.leapController = new Controller(controlListener);
	}

	public void setHoveredField(Field field) {
		this.hoveredField = field;
	}

	public void draw() {
		background(0);
		stroke(255);
		line(0, height / 2, width, height/2);
		line(width / 2, 0, width / 2, height);
		drawHotplates();
		drawHoverDot();
	}

	private void drawHotplates() {
		final int X_LEFT = width / 4;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 4;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 3;
		ellipse(X_LEFT, Y_TOP, RADIUS, RADIUS);
		ellipse(X_RIGHT, Y_TOP, RADIUS, RADIUS);
		ellipse(X_LEFT, Y_BOTTOM, RADIUS, RADIUS);
		ellipse(X_RIGHT, Y_BOTTOM, RADIUS, RADIUS);
	}

	private void drawHoverDot () {
		fill(255, 0, 0);
		final int X_LEFT = (width / 16) + width / 3;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 16;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 20;
		if (hoveredField != Field.NO_FIELD) {
			if (hoveredField == Field.TOP_LEFT) {
				ellipse(X_LEFT, Y_TOP, RADIUS, RADIUS);
			} else if (hoveredField == Field.TOP_RIGHT) {
				ellipse(X_RIGHT, Y_TOP, RADIUS, RADIUS);
			} else if (hoveredField == Field.BOTTOM_LEFT) {
				ellipse(X_LEFT, Y_BOTTOM, RADIUS, RADIUS);
			} else if (hoveredField == Field.BOTTOM_RIGHT) {
				ellipse(X_RIGHT, Y_BOTTOM, RADIUS, RADIUS);
			}
		}
	}
	
}