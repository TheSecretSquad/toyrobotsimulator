package toyrobotsimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CardinalDirectionTest {

	@Test(expected=InvalidDirectionException.class)
	public void WhenCreating_WithInvalidDirectionString_ThrowsException() {
		CardinalDirection.createFrom("invalid");
	}
	
	@Test
	public void WhenCreating_AcceptsCaseInsensitiveInput() {
		assertNotNull(CardinalDirection.createFrom("NORTH"));
		assertNotNull(CardinalDirection.createFrom("north"));
	}
	
	@Test
	public void WhenCreating_CreatesCorrectCardinalDirection() {
		assertEquals(CardinalDirection.NORTH, CardinalDirection.createFrom("NORTH"));
		assertEquals(CardinalDirection.SOUTH, CardinalDirection.createFrom("SOUTH"));
		assertEquals(CardinalDirection.EAST, CardinalDirection.createFrom("EAST"));
		assertEquals(CardinalDirection.WEST, CardinalDirection.createFrom("WEST"));
	}
	
	@Test
	public void WhenMovingCounterClockwise_ReturnsCardinalDirectionInOneCounterClockwiseMovement() {
		assertEquals(CardinalDirection.WEST, CardinalDirection.NORTH.counterClockwise());
		assertEquals(CardinalDirection.SOUTH, CardinalDirection.WEST.counterClockwise());
		assertEquals(CardinalDirection.EAST, CardinalDirection.SOUTH.counterClockwise());
		assertEquals(CardinalDirection.NORTH, CardinalDirection.EAST.counterClockwise());
	}
	
	@Test
	public void WhenMovingClockwise_ReturnsCardinalDirectionInOneClockwiseMovement() {
		assertEquals(CardinalDirection.EAST, CardinalDirection.NORTH.clockwise());
		assertEquals(CardinalDirection.SOUTH, CardinalDirection.EAST.clockwise());
		assertEquals(CardinalDirection.WEST, CardinalDirection.SOUTH.clockwise());
		assertEquals(CardinalDirection.NORTH, CardinalDirection.WEST.clockwise());
	}
}