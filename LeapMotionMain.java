import com.leapmotion.leap.Controller;
import java.io.IOException;

public class LeapMotionMain {

	public static void main(String[] args) {
		CeranControlPanel ceranControl = new CeranControlPanel();
		Controller controller = new Controller(ceranControl);
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller.removeListener(ceranControl);
	}
}
