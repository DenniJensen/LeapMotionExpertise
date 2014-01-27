import java.io.IOException;

public class LeapMotionMain {
	public static void main(String[] args) {
		//PApplet.main("ProcessingSketch");
		ControlListener controlListener = new ControlListener();
		MyLeapMotionController leapMotionController = new MyLeapMotionController(controlListener);
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.removeListener(ceranControl);
	}
}
