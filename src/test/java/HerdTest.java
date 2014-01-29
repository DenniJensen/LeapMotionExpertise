import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dennis on 1/29/14.
 */
public class HerdTest {
	Herd herd;

	@Before
	public void setUp() throws Exception {
		herd = new Herd(4, 5);
	}

	@Test
	public void testHotPlates() {
		assertEquals(4, herd.getHotPlates());
		herd.setHeatLevel(0, 4);
		herd.setHeatLevel(2, 3);
		herd.setHeatLevel(3, 3);
		herd.setHeatLevel(1, 6);
		assertEquals(4, herd.getHeatLevel(0));
		assertEquals(5, herd.getHeatLevel(1));
		assertEquals(3, herd.getHeatLevel(2));
		assertEquals(3, herd.getHeatLevel(3));
	}
}
