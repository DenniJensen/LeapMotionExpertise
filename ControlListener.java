import com.leapmotion.leap.*;

/**
 * This class listener will listen to the leap motion controller.
 * It will be control an ceran panel by analyse the position of the hand
 * and the gesture made by the hand and finger on the leap motion controller.
 * 
 * @author Dennis Hägler - dennis.haegler@gmail.com
 */
public class ControlListener extends Listener {
	private final int NO_CHANGE = 0;
	private final int INCREASE = 1;
	private final int DECREASE = -1;
	boolean isHandLocked;
	private Herd model;
	//private ProcessingSketch view;

	public ControlListener() {
		model = new Herd(4, 3);
		isHandLocked = false;
	}

	@Override
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	@Override
	public void onConnect(Controller controller) {
		System.out.println("Connected");
		//controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
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
		//Frame prevFrame = controller.frame(1);
		Frame frame = controller.frame();
		Field pointedField = getPointedField(frame);
		//view.setHoveredField(pointedField);
		//showChange(prevFrame, frame);
		final int FRAMES_TO_LOCK = 10;

		//TODO if hand is locked, not needed to try to lock again
		if (isSameFieldForCertainFrames(controller, FRAMES_TO_LOCK)) {
			System.out.println(pointedField + " LOCKED");
			int trackedFinger = getCountTrackedFingers(controller);
			//TODO Add to Model
			System.out.println(trackedFinger + " Fingers tracked");
			model.setHeatLevel(pointedField.ordinal(), trackedFinger);
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
	 *
	 * @param hand
	 * @return
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

	private boolean isSameFieldForCertainFrames(Controller controller, int framesToLock) {
		Frame frame;
		Frame prevFrame;
		Field field;
		Field prevField;
		for (int i = 0; i < framesToLock; i++) {
			frame = controller.frame(i);
			prevFrame = controller.frame(i + 1);
			if (!isPointedSameField(prevFrame, frame)) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @param prevFrame
	 * @param frame
	 */
	private boolean isPointedSameField(Frame prevFrame, Frame frame) {
		boolean isSameField = false;
		Field pointedField = getPointedField(frame);
		Field prevField = getPointedField(prevFrame);
		if (pointedField == prevField && pointedField != Field.NO_FIELD) {
			isSameField = true;
		} else {
			isSameField = false;
		}
		return isSameField;
	}


	private int getCountTrackedFingers(Controller controller) {
		Hand hand = controller.frame().hand(0);
		return hand.fingers().count();
	}
}
