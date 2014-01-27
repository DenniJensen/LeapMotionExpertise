import com.leapmotion.leap.*;

/**
 * This class listener will listen to the leap motion controller.
 * It will be control an ceran panel by analyse the position of the hand
 * and the gesture made by the hand and finger on the leap motion controller.
 * 
 * @author Dennis Hägler - dennis.haegler@gmail.com
 */
public class ControlPanel extends Listener {
	private final int NO_CHANGE = 0;
	private final int INCREASE = 1;
	private final int DECREASE = -1;
	private Herd herdModel;
	private ProcessingSketch processingView;

	public ControlPanel() {
		herdModel = new Herd(4, 3);
	}

	public ControlPanel(Herd herdModel, ProcessingSketch processingView) {
		this.herdModel = herdModel;
		this.processingView = processingView;
	}

	public void setHerdModel(Herd model) {
		this.herdModel = model;
	}

	public void setProcessingView(ProcessingSketch processingView) {
		this.processingView = processingView;
	}

	@Override
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	@Override
	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
	}

	@Override
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}

	@Override
	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	@Override
	public void onFrame(Controller controller) {
		Frame prevFrame = controller.frame(1);
		Frame frame = controller.frame();
		Field pointedField = Field.NO_FIELD;
		GestureList gestures = frame.gestures();
		int changeTemp = NO_CHANGE;
		showChange(prevFrame, frame);
		int countOfGestures = gestures.count();
		for (int i = 0; i < countOfGestures; i++) {
			Gesture gesture = gestures.get(i);
			switch (gesture.type()) {
				case TYPE_CIRCLE:
					changeTemp = circleGesture(gesture, frame, prevFrame);
					break;
				default:
					System.out.println("Unknown gesture type.");
					break;
			}
		}
		if (changeTemp == INCREASE) {
			herdModel.increaseHotplate(pointedField.ordinal());
		} else if (changeTemp == INCREASE) {
			herdModel.decreaseHotplate(pointedField.ordinal());
		}
		if (changeTemp != NO_CHANGE) {
			System.out.println("Changed Temp");
		}

		//TODO handle frame get the logic the control the herd and show on view
	}

	/**
	 *
	 * @param frame
	 * @return
	 */
	private Field getPointedField(Frame frame) {
		Field pointedField;
		if (frame.hands().isEmpty()) {
			pointedField = Field.NO_FIELD;
		} else {
			Hand hand = frame.hands().get(0);
			pointedField = getField(hand);
		}
		return pointedField;
	}

	/**
	 * Returns an integer of the pointed field.
	 * The number will be generated by the position of the given hand.
	 * Depending on the position in coordinate system of the given coordinate system
	 * of the leap motion controller, the function will return an integer.
	 *
	 * The value will be from one to four. One will be representing the top left position of the field.
	 * Four will be representing the bottom right position of the field.
	 *
	 * @param hand A Hand tracked by the leap motion contains tracked data like the position in the
	 *             coordinate system given from the leap motion controller.
	 * @return an integer of the pointed field.
	 */
	private Field getField(Hand hand) {
		Vector vector = hand.palmPosition();
		float xPos = vector.getX();
		float zPos = vector.getZ();
		Field field;
		if (zPos < 0) {
			if (xPos < 0) {
				field = Field.TOP_LEFT;
			} else {
				field = Field.TOP_RIGHT;
			}
		} else {
			if (xPos < 0) {
				field = Field.BOTTOM_LEFT;
			} else {
				field = Field.BOTTOM_RIGHT;
			}
		}
		return field;
	}

	/**
	 *
	 * @param prevFrame
	 * @param frame
	 */
	private void showChange(Frame prevFrame, Frame frame) {
		Field pointedField = getPointedField(frame);
		Field prevField = getPointedField(prevFrame);
		if (pointedField != prevField) {
			System.out.println("Position used: " + pointedField);
			//TODO change
		} else {
			System.out.println("Position used: " + pointedField);
			//TODO No Change
		}
	}

	/**
	 *
	 * @param gesture
	 */
	private int circleGesture(Gesture gesture, Frame frame, Frame prevFrame) {
		CircleGesture circle = new CircleGesture(gesture);
		int heatLevel;
		if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4) {
			heatLevel = INCREASE;
		} else {
			heatLevel = DECREASE;
		}
		return heatLevel;
	}
}
