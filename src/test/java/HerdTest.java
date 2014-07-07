import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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
		assertSame(4, herd.getHeatLevel(0));
		assertSame(5, herd.getHeatLevel(1));
		assertSame(3, herd.getHeatLevel(2));
		assertSame(3, herd.getHeatLevel(3));
		herd.turnOff();
		assertSame(0, herd.getHeatLevel(0));
		assertSame(0, herd.getHeatLevel(1));
		assertSame(0, herd.getHeatLevel(2));
		assertSame(0, herd.getHeatLevel(3));
	}
}
