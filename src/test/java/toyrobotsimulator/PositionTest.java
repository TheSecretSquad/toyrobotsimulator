package toyrobotsimulator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void WhenComparingEqual_IsValueEqual() {
		assertEquals(new Position(1, 1), new Position(1, 1));
		assertEquals(new Position(1, 2), new Position(1, 2));
	}
	
	@Test(expected=PositionInitializationException.class)
	public void WhenCreatingFromStrings_ThrowsExceptionOnNonIntegerParsableValue() {
		new Position("a", "b");
	}
	
	@Test
	public void WhenToString_ReturnsStringFormattedAsCoordinate() {
		assertEquals("(1,2)", new Position(1,2).toString());
	}
}