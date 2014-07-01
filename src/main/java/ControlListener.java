import com.leapmotion.leap.*;

/**
 *
 * @author Dennis Hägler - dennis.haegler@gmail.com
 */
public class ControlListener extends Listener {
	private Herd model;
	private ProcessingSketch view;
	private int framesInSameField;
	private int framesWithSameFingerCount;
	private boolean isHandLocked;
	private final int FRAMES_TO_LOCK = 60;
	private final int FRAMES_TO_LISTEN_TO_FINGERS = 30;

	public ControlListener(Herd model, ProcessingSketch view) {
		this.model = model;
		this.view = view;
		this.framesInSameField = 0;
		this.framesWithSameFingerCount = 0;
		this.isHandLocked = false;
	}

	public Herd getModel() {
		return this.model;
	}

	@Override
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	@Override
	public void onConnect(Controller controller) {
		System.out.println("Connected");
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
		Frame frame = controller.frame();
		Frame prevFrame = controller.frame(1);
		Field pointedField = getPointedField(frame);
		Field prevPointedField = getPointedField(prevFrame);
		view.setHoveredField(pointedField);
		countFramesOnSameField(pointedField, prevPointedField);
		int fingerCount = getTrackedFingerCount(frame);
		int prevFingerCount = getTrackedFingerCount(prevFrame);
		if (fingerCount == prevFingerCount) {
			view.setCurrentFingerCount(fingerCount);
		}
		if (framesInSameField >= FRAMES_TO_LOCK) {
			isHandLocked = true;
		} else {
			isHandLocked = false;
		}
		if (isHandLocked) {
			view.setLockedField(pointedField);
			countFramesWithSameFingerCount(frame, prevFrame);
		} else {
			view.setLockedField(Field.NO_FIELD);
			framesWithSameFingerCount = 0;
		}
		if (framesWithSameFingerCount >= FRAMES_TO_LISTEN_TO_FINGERS / 3) {

		}
		if (framesWithSameFingerCount >= (FRAMES_TO_LISTEN_TO_FINGERS * 2) / 3) {

		}
		if (framesWithSameFingerCount == FRAMES_TO_LISTEN_TO_FINGERS) {
			int p = pointedField.ordinal();
			model.setHeatLevel(p, fingerCount);
		}
	}

	private void countFramesOnSameField(Field pointedField, Field prevPointedField) {
		if (pointedField == prevPointedField && pointedField != Field.NO_FIELD) {
			framesInSameField++;
		} else {
			framesInSameField = 0;
		}
	}

	private void countFramesWithSameFingerCount(Frame frame, Frame prevFrame) {
		if (isSameFingerCount(frame, prevFrame)) {
			framesWithSameFingerCount++;
		} else {
			framesWithSameFingerCount = 0;
		}
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

	private boolean isSameFingerCount(Frame firstFrame, Frame otherFrame) {
		return firstFrame.fingers().count() == otherFrame.fingers().count();
	}

	private int getTrackedFingerCount(Controller controller) {
		return getTrackedFingerCount(controller.frame());
	}

	private int getTrackedFingerCount(Frame frame) {
		return frame.fingers().count();
	}
}
