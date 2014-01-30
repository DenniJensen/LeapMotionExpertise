import processing.core.PApplet;
import processing.core.PFont;
import com.leapmotion.leap.Controller;

/**
 * Object to draw the logic in Processing.
 */
public class ProcessingSketch extends PApplet {
	private int width;
	private int height;
	private Field hoveredField;
	private Field lockedField;
	private int trackLevel;
	private Controller leapController;
	private ControlListener controlListener;
	private Herd model;
	private PFont font;

	/**
	 * Initial all references need to draw information form the model and the leap
	 * motion controller
	 */
	public void setup() {
		font = createFont("Arial",16,true);
		hoveredField = Field.NO_FIELD;
		lockedField = Field.NO_FIELD;
		width = displayWidth;
		height = displayHeight;
		size(width, height);
		model = new Herd(4, 5);
		controlListener = new ControlListener(model, this);
		leapController = new Controller(controlListener);
	}

	public void setHoveredField(Field field) {
		this.hoveredField = field;
	}

	public void setLockedField(Field field) {
		this.lockedField = field;
	}

	public void setTrackLevel(int trackLevel) {
		this.trackLevel = trackLevel;
	}

	public void draw() {
		background(0);
		stroke(255);
		line(0, height / 2, width, height/2);
		line(width / 2, 0, width / 2, height);
		drawHoverDot();
		drawLockedField();
		drawHotplates();
		textFont(font, height / 16);
		//drawLoadingDot();
		drawHeatLevel();
	}

	private void drawHotplates() {
		stroke(255);
		final int X_LEFT = width / 4;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 4;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 3;
		drawPlate(X_LEFT, Y_TOP, RADIUS);
		drawPlate(X_RIGHT, Y_TOP, RADIUS);
		drawPlate(X_LEFT, Y_BOTTOM, RADIUS);
		drawPlate(X_RIGHT, Y_BOTTOM, RADIUS);
		if (keyPressed) {
			model.turnOff();
		}
	}

	private void drawPlate(int xPos, int yPos, int radius) {
		fill(0);
		ellipse(xPos, yPos, radius, radius);
	}

	private void drawHoverDot () {
		stroke(255, 0, 0);
		fill(0, 0, 0);
		final int X_LEFT = (width / 16) + width / 3;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 16;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 20;
		if (hoveredField != Field.NO_FIELD) {
			if (hoveredField == Field.TOP_LEFT) {
				if (lockedField == Field.TOP_LEFT) {
					fill(255, 0, 0);
				}
				ellipse(X_LEFT, Y_TOP, RADIUS, RADIUS);
				fill(0, 0, 0);
			} else if (hoveredField == Field.TOP_RIGHT) {
				if (lockedField == Field.TOP_RIGHT) {
					fill(255, 0, 0);
				}
				ellipse(X_RIGHT, Y_TOP, RADIUS, RADIUS);
				fill(0, 0, 0);
			} else if (hoveredField == Field.BOTTOM_LEFT) {
				if (lockedField == Field.BOTTOM_LEFT) {
					fill(255, 0, 0);
				}
				ellipse(X_LEFT, Y_BOTTOM, RADIUS, RADIUS);
				fill(0, 0, 0);
			} else if (hoveredField == Field.BOTTOM_RIGHT) {
				if (lockedField == Field.BOTTOM_RIGHT) {
					fill(255, 0, 0);
				}
				ellipse(X_RIGHT, Y_BOTTOM, RADIUS, RADIUS);
				fill(0, 0, 0);
			}
		}
	}

	private void drawLockedField() {
		stroke(255, 0, 0);
		fill(0, 0, 0);
		final int X_LEFT = width / 4;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 4;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = (height / 3) + 20;
		if (lockedField != Field.NO_FIELD) {
			if (lockedField == Field.TOP_LEFT) {
				ellipse(X_LEFT, Y_TOP, RADIUS, RADIUS);
			} else if (lockedField == Field.TOP_RIGHT) {
				ellipse(X_RIGHT, Y_TOP, RADIUS, RADIUS);
			} else if (lockedField == Field.BOTTOM_LEFT) {
				ellipse(X_LEFT, Y_BOTTOM, RADIUS, RADIUS);
			} else if (lockedField == Field.BOTTOM_RIGHT) {
				ellipse(X_RIGHT, Y_BOTTOM, RADIUS, RADIUS);
			}
		}
	}

	private void drawLoadingDot() {
		stroke(255);
		fill(0, 0, 0, 1);
		final int X_LEFT = height / 30;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 16 + height / 3;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 30;
		final int SPACE = RADIUS + 10;

		if (trackLevel > 0 && hoveredField == Field.TOP_LEFT) {
			fill(0, 255, 0, 1);
		}
		ellipse(X_LEFT, Y_TOP, RADIUS, RADIUS);
		ellipse(X_LEFT + SPACE, Y_TOP, RADIUS, RADIUS);
		ellipse(X_LEFT + 2 * SPACE, Y_TOP, RADIUS, RADIUS);

		if (trackLevel > 0 && hoveredField == Field.TOP_RIGHT) {
			fill(0, 255, 0, 1);
		}
		ellipse(X_RIGHT, Y_TOP, RADIUS, RADIUS);
		ellipse(X_RIGHT + SPACE, Y_TOP, RADIUS, RADIUS);
		ellipse(X_RIGHT + 2 * SPACE, Y_TOP, RADIUS, RADIUS);

		if (trackLevel > 0 && hoveredField == Field.BOTTOM_LEFT) {
			fill(0, 255, 0, 1);
		}
		ellipse(X_LEFT, Y_BOTTOM, RADIUS, RADIUS);
		ellipse(X_LEFT + SPACE, Y_BOTTOM, RADIUS, RADIUS);
		ellipse(X_LEFT + 2 * SPACE, Y_BOTTOM, RADIUS, RADIUS);

		if (trackLevel > 0 && hoveredField == Field.BOTTOM_RIGHT) {
			fill(0, 255, 0, 1);
		}

		ellipse(X_RIGHT, Y_BOTTOM, RADIUS, RADIUS);
		ellipse(X_RIGHT + SPACE, Y_BOTTOM, RADIUS, RADIUS);
		ellipse(X_RIGHT + 2 * SPACE, Y_BOTTOM, RADIUS, RADIUS);
	}

	private void drawHeatLevel() {
		textSize(32);
		fill(255);
		final int X_LEFT = height / 30;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 16 + height / 3 - 16;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 30;
		int heat;
		heat = model.getHeatLevel(0);
		text(heat , X_LEFT, Y_TOP);

		heat = model.getHeatLevel(1);
		text(heat , X_RIGHT, Y_TOP);

		heat = model.getHeatLevel(2);
		text(heat , X_LEFT, Y_BOTTOM);

		heat = model.getHeatLevel(3);
		text(heat , X_RIGHT, Y_BOTTOM);
	}
}