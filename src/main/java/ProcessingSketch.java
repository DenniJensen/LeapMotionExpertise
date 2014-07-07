import processing.core.PApplet;
import processing.core.PFont;
import com.leapmotion.leap.Controller;

/**
 * Object to draw the logic in Processing.
 *
 * @author Dennis Hägler - dennis.haegler@gmail.com
 */
public class ProcessingSketch extends PApplet {
	private int width;
	private int height;
	private Field hoveredField;
	private Field lockedField;
	private int currentFingerCount;
	private Controller leapController;
	private ControllerListener controllerListener;
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
		width = displayWidth - 10;
		height = displayHeight - 60;
		size(width, height);
		model = new Herd(4, 5);
		controllerListener = new ControllerListener(model, this);
		leapController = new Controller(controllerListener);
	}

	public void setHoveredField(Field field) {
		this.hoveredField = field;
	}

	public void setLockedField(Field field) {
		this.lockedField = field;
	}

	public void setCurrentFingerCount(int fingerCount) {
		this.currentFingerCount = fingerCount;
	}

	public void draw() {
		background(0);
		fill(0);
		rect(0, 0, width, height);
		noFill();
		textFont(font, height / 16);
		stroke(255);
		drawHoverDot();
		drawHotplates();
	}

	private void drawHotplates() {
		stroke(255);
		noFill();
		final int X_LEFT = width / 4;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 4;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = (height / 5) * 2;
		drawPlate(X_LEFT, Y_TOP, RADIUS, model.getHeatLevel(0));
		drawPlate(X_RIGHT, Y_TOP, RADIUS, model.getHeatLevel(1));
		drawPlate(X_LEFT, Y_BOTTOM, RADIUS, model.getHeatLevel(2));
		drawPlate(X_RIGHT, Y_BOTTOM, RADIUS, model.getHeatLevel(3));
		if (keyPressed) {
			model.turnOff();
		}
		if (hoveredField != Field.NO_FIELD) {
			switch (hoveredField) {
				case TOP_LEFT:
					drawNewHeatLevelOnCurrentFingerCount(X_LEFT, Y_TOP, RADIUS);
					break;
				case TOP_RIGHT:
					drawNewHeatLevelOnCurrentFingerCount(X_RIGHT, Y_TOP, RADIUS);
					break;
				case BOTTOM_LEFT:
					drawNewHeatLevelOnCurrentFingerCount(X_LEFT, Y_BOTTOM, RADIUS);
					break;
				case BOTTOM_RIGHT:
					drawNewHeatLevelOnCurrentFingerCount(X_RIGHT, Y_BOTTOM, RADIUS);
					break;
			}
		}
	}

	private void drawPlate(int xPos, int yPos, int radius, int heatLevel) {
		stroke(255);
		noFill();
		ellipse(xPos, yPos, radius, radius);
		drawHeatRingInHotPlate(xPos, yPos, radius, heatLevel);
		drawHeatLevelInformationText(xPos + radius / 2, yPos + radius / 2, heatLevel);
	}

	private void drawHeatRingInHotPlate(int xPos, int yPos, int radius, int heatLevel) {
		int heatRadius = radius / 10;
		int distanceToNextHeat = radius / 5;
		stroke(255, 0, 0);
		strokeWeight(25);
		for (int i = 0;  i < heatLevel; i++) {
			ellipse(xPos, yPos, heatRadius, heatRadius);
			heatRadius += distanceToNextHeat;
		}
		strokeWeight(1);
		stroke(255);
	}

	private void drawHeatLevelInformationText(int xPos, int yPos, int heatLevel) {
		textSize(32);
		fill(255, 255, 255);
		text(heatLevel , xPos, yPos);
		noFill();
	}

	private void drawHoverDot () {
		stroke(255, 0, 0);
		noFill();
		final int X_LEFT = (width / 16) + width / 3;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 16;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 20;
		if (hoveredField == Field.TOP_LEFT) {
			drawTopLeftHoverDot(X_LEFT, Y_TOP, RADIUS);
		} else if (hoveredField == Field.TOP_RIGHT) {
			drawTopRightHoverDot(X_RIGHT, Y_TOP, RADIUS);
		} else if (hoveredField == Field.BOTTOM_LEFT) {
			drawBottomLeftHoverDot(X_LEFT, Y_BOTTOM, RADIUS);
		} else if (hoveredField == Field.BOTTOM_RIGHT) {
			drawBottomRightHoverDot(X_RIGHT, Y_BOTTOM, RADIUS);
		}
	}

	private void drawBottomRightHoverDot(int x_RIGHT, int y_BOTTOM, int RADIUS) {
		if (lockedField == Field.BOTTOM_RIGHT) {
			fill(255, 0, 0);
		}
		ellipse(x_RIGHT, y_BOTTOM, RADIUS, RADIUS);
		fill(0, 0, 0);
	}

	private void drawBottomLeftHoverDot(int x_LEFT, int y_BOTTOM, int RADIUS) {
		if (lockedField == Field.BOTTOM_LEFT) {
			fill(255, 0, 0);
		}
		ellipse(x_LEFT, y_BOTTOM, RADIUS, RADIUS);
		fill(0, 0, 0);
	}

	private void drawTopRightHoverDot(int x_RIGHT, int y_TOP, int RADIUS) {
		if (lockedField == Field.TOP_RIGHT) {
			fill(255, 0, 0);
		}
		ellipse(x_RIGHT, y_TOP, RADIUS, RADIUS);
		fill(0, 0, 0);
	}

	private void drawTopLeftHoverDot(int x_LEFT, int y_TOP, int RADIUS) {
		if (lockedField == Field.TOP_LEFT) {
			fill(255, 0, 0);
		}
		ellipse(x_LEFT, y_TOP, RADIUS, RADIUS);
		fill(0, 0, 0);
	}

	private boolean isTopLeftLocked() {
		return (hoveredField == Field.TOP_LEFT) &&
				(lockedField == Field.TOP_LEFT);
	}

	private void drawNewHeatLevelOnCurrentFingerCount(int xPos, int yPos, int radius) {
		int heatRadius = radius / 10;
		int distanceToNextHeat = radius / 5;
		stroke(255, 255, 255, 50);
		strokeWeight(10);
		for (int i = 0;  i < currentFingerCount; i++) {
			ellipse(xPos, yPos, heatRadius, heatRadius);
			heatRadius += distanceToNextHeat;
		}
		strokeWeight(1);
		stroke(255);
	}
}