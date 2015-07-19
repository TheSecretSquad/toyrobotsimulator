package toyrobotsimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void AreValueEqual() {
		assertEquals(new Position(1, 1), new Position(1, 1));
		assertEquals(new Position(1, 2), new Position(1, 2));
	}
}