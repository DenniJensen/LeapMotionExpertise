import com.leapmotion.leap.*;

/**
 *
 * @author Dennis Hägler - dennis.haegler@gmail.com
 */
public class ControllerListener extends Listener {
	private Herd model;
	private ProcessingSketch view;
	private int framesInSameField;
	private int framesWithSameFingerCount;
	private final int FRAMES_TO_LOCK = 60;
	private final int FRAMES_TO_LISTEN_TO_FINGERS = 30;

	public ControllerListener(Herd model, ProcessingSketch view) {
		this.model = model;
		this.view = view;
		this.framesInSameField = 0;
		this.framesWithSameFingerCount = 0;
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
		int fingerCount = countTrackedFinger(frame);
		int prevFingerCount = countTrackedFinger(prevFrame);
		if (fingerCount != prevFingerCount) {
			view.setCurrentFingerCount(fingerCount);
		}
		if (isHandLocked()) {
			view.setLockedField(pointedField);
			countFramesWithSameFingerCount(frame, prevFrame);
		} else {
			view.setLockedField(Field.NO_FIELD);
			framesWithSameFingerCount = 0;
		}
		if (framesWithSameFingerCount == FRAMES_TO_LISTEN_TO_FINGERS) {
			int p = pointedField.ordinal();
			model.setHeatLevel(p, fingerCount);
		}
		System.out.println(fingerCount);
	}

	private void countFramesOnSameField(Field pointedField, Field prevPointedField) {
		if (pointedField == prevPointedField && pointedField != Field.NO_FIELD) {
			framesInSameField++;
		} else {
			framesInSameField = 0;
		}
	}

	private boolean isHandLocked() {
		if (framesInSameField >= FRAMES_TO_LOCK) {
			return true;
		} else {
			return false;
		}
	}

	private void countFramesWithSameFingerCount(Frame frame, Frame prevFrame) {
		if (isSameFingerCount(frame, prevFrame)) {
			framesWithSameFingerCount++;
		} else {
			framesWithSameFingerCount = 0;
		}
	}

	private Field getPointedField(Frame frame) {
		if (frame.hands().isEmpty()) {
			return Field.NO_FIELD;
		} else {
			Hand hand = frame.hands().get(0);
			return getField(hand);
		}
	}

	private Field getField(Hand hand) {
		Vector vector = hand.palmPosition();
		float xPos = vector.getX();
		float zPos = vector.getZ();
		if (xPos < 0 && zPos < 0) {
			return Field.TOP_LEFT;
		}
		if (zPos < 0 && xPos > 0) {
			return Field.TOP_RIGHT;
		}
		if (zPos > 0 && xPos < 0) {
			return Field.BOTTOM_LEFT;
		}
		if (zPos > 0 && xPos > 0) {
			return Field.BOTTOM_RIGHT;
		}
		return Field.NO_FIELD;
	}

	private boolean isSameFingerCount(Frame firstFrame, Frame otherFrame) {
		return firstFrame.fingers().count() == otherFrame.fingers().count();
	}

	private int getTrackedFingerCount(Controller controller) {
		return countTrackedFinger(controller.frame());
	}

	private int countTrackedFinger(Frame frame) {
		return frame.fingers().count();
	}
}
