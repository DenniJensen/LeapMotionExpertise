import processing.core.PApplet;
import processing.core.PFont;
import com.leapmotion.leap.Controller;

public class ProcessingSketch extends PApplet {
	private int width;
	private int height;
	private Field hoveredField;
	private Controller leapController;
	private ControlListener controlListener;
	private Herd model;
	private PFont font;


	public void setup() {
		font = createFont("Arial",16,true);
		hoveredField = Field.NO_FIELD;
		width = displayWidth;
		height = displayHeight;
		size(width, height);
		model = new Herd(4, 3);
		controlListener = new ControlListener(model, this);
		leapController = new Controller(controlListener);
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
		textFont(font, height / 16);
	}

	private void drawHotplates() {
		final int X_LEFT = width / 4;
		final int X_RIGHT = X_LEFT + width / 2;
		final int Y_TOP = height / 4;
		final int Y_BOTTOM = Y_TOP + height / 2;
		final int RADIUS = height / 3;
		int heatLevel = 0;
		fill(255);
		ellipse(X_LEFT, Y_TOP, RADIUS, RADIUS);
		fill(0);
		textAlign(CENTER);
		heatLevel = this.controlListener.getModel().getHeatLevel(0);
		text(heatLevel, X_LEFT, Y_TOP);

		fill(255);
		ellipse(X_RIGHT, Y_TOP, RADIUS, RADIUS);
		fill(0);
		textAlign(CENTER);
		heatLevel = this.controlListener.getModel().getHeatLevel(1);
		text(heatLevel, X_RIGHT, Y_TOP);

		fill(255);
		ellipse(X_LEFT, Y_BOTTOM, RADIUS, RADIUS);
		fill(0);
		textAlign(CENTER);
		heatLevel = this.controlListener.getModel().getHeatLevel(2);
		text(heatLevel, X_LEFT, Y_BOTTOM);

		fill(255);
		ellipse(X_RIGHT, Y_BOTTOM, RADIUS, RADIUS);
		fill(0);
		textAlign(CENTER);
		heatLevel = this.controlListener.getModel().getHeatLevel(3);
		text(heatLevel, X_RIGHT, Y_BOTTOM);
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