import com.leapmotion.leap.*;

/**
 * 
 * @author Dennis Hägler - dennis.haegler@gmail.com
 */
public class ControlListener extends Listener {
	private Herd model;
	private ProcessingSketch view;
	private int frameCount;
	boolean isHandLocked;

	public ControlListener(Herd model, ProcessingSketch view) {
		this.model = model;
		this.view = view;
		this.frameCount = 0;
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
		final int FRAMES_TO_LOCK = 120;
		final int FRAMES_TO_LISTEN_TO_FINGERS = 60;
		if (pointedField == prevPointedField && pointedField != Field.NO_FIELD) {
			frameCount++;
			System.out.println("SAME!!!");
		} else {
			frameCount = 0;
		}
		//TODO if hand is locked, not needed to try to lock again
		if (frameCount >= FRAMES_TO_LOCK) {
			isHandLocked = true;
			//int trackedFinger = getCountTrackedFingers(controller);
			//int p = pointedField.ordinal();
			//model.setHeatLevel(p, trackedFinger);
		} else {
			isHandLocked = false;
		}
		if (isHandLocked) {
			view.setLockedField(pointedField);
		} else {
			view.setLockedField(Field.NO_FIELD);
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

	private int getCountTrackedFingers(Controller controller) {
		int fingerCount = controller.frame().fingers().count();
		return fingerCount;
	}
}
