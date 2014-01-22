import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

public class SampleListener2 extends Listener {

	@Override
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	@Override
	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}

	@Override
	public void onDisconnect(Controller controller) {
		//Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
	}

	@Override
	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	@Override
	public void onFrame(Controller controller) {
		// Get the most recent frame and report some basic information
		Frame frame = controller.frame();
		if (frame.id() % 60 != 0) {
			return;
		}
		System.out.println("Frame id: " + frame.id()
				+ ", timestamp: " + frame.timestamp()
				+ ", hands: " + frame.hands().count()
				+ ", fingers: " + frame.fingers().count()
				+ ", tools: " + frame.tools().count()
				+ ", gestures " + frame.gestures().count());

		if (!frame.hands().isEmpty()) {
			// Get the first hand
			Hand hand = frame.hands().get(0);

			// Check if the hand has any fingers
			FingerList fingers = hand.fingers();
			if (!fingers.isEmpty()) {
				// Calculate the hand's average finger tip position
				Vector avgPos = Vector.zero();
				for (Finger finger : fingers) {
					avgPos = avgPos.plus(finger.tipPosition());
				}
				avgPos = avgPos.divide(fingers.count());
				System.out.println("Hand has " + fingers.count()
						+ " fingers, average finger tip position: " + avgPos);
			}

			// Get the hand's sphere radius and palm position
			System.out.println("Hand sphere radius: " + hand.sphereRadius()
					+ " mm, palm position: " + hand.palmPosition());

			// Get the hand's normal vector and direction
			Vector normal = hand.palmNormal();
			Vector direction = hand.direction();

			// Calculate the hand's pitch, roll, and yaw angles
			System.out.println("Hand pitch: " + Math.toDegrees(direction.pitch()) + " degrees, "
					+ "roll: " + Math.toDegrees(normal.roll()) + " degrees, "
					+ "yaw: " + Math.toDegrees(direction.yaw()) + " degrees");
		}
	}
}
