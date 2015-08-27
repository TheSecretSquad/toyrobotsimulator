package toyrobotsimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardinalDirectionTest {

	@Mock
	private Directable directable;
	@Mock
	private Coordinate fromCoordinate;
	
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
	
	@Test
	public void WhenDirecting_TranslatesByCorrectPosition() {
		CardinalDirection.NORTH.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByPositionTo(new Position(0, 1), directable);
		CardinalDirection.SOUTH.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByPositionTo(new Position(0, -1), directable);
		CardinalDirection.EAST.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByPositionTo(new Position(1, 0), directable);
		CardinalDirection.WEST.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByPositionTo(new Position(-1, 0), directable);
	}
}