import java.io.IOException;

public class LeapMotionMain {

	public static void main(String[] args) {
		ProcessingSketch sketch = new ProcessingSketch();
		ControlPanel ceranControl = new ControlPanel();
		MyLeapMotionController controller = new MyLeapMotionController(ceranControl);
		sketch.main("ProcessingSketch");
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.removeListener(ceranControl);
	}
}
