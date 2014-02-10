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
		width = displayWidth - 10;
		height = displayHeight - 60;
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
		textFont(font, height / 16);
		stroke(255);
		//line(0, height / 2, width, height/2);
		//line(width / 2, 0, width / 2, height);
		drawHoverDot();
		drawHotplates();
		textFont(font, height / 16);
		//drawLoadingDot();
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
		int distanceToNextHeat = radius / 6;
		stroke(255, 0, 0);
		strokeWeight(15);
		for (int i = 0;  i < heatLevel; i++) {
			ellipse(xPos, yPos, heatRadius, heatRadius);
			heatRadius += distanceToNextHeat;
		}
		strokeWeight(1);
		stroke(255);
	}

	private void drawHeatLevelInformationText(int xPos, int yPos, int heatLevel) {
		textSize(32);
		fill(255);
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

	private void greyOutHerdField(float alpha) {
		fill(0, 0, 0, alpha);
		rect(0, 0, width, height);
		noFill();
	}
}