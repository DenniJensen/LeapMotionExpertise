import com.leapmotion.leap.Controller;
import processing.core.PApplet;

import java.io.IOException;

public class LeapMotionMain {

	public static void main(String[] args) {
		MyProcessingSketch sketch = new MyProcessingSketch();
		ControlPanel ceranControl = new ControlPanel();
		MyLeapMotionController controller = new MyLeapMotionController(ceranControl);
		sketch.main(new String[]{"--present", "MyProcessingSketch"});
		ceranControl.field = Field.BOTTOM_RIGHT;
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.removeListener(ceranControl);
	}
}
