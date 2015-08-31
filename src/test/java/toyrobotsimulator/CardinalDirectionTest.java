package toyrobotsimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

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
	@Mock
	private Turnable turnable;
	
	private void verifyCounterClockwiseTurnFromDirectionFaces(final Direction direction, final Direction newDirection) {
		verifyTurnActionDirectionFaces(() -> direction.turnCounterClockwise(turnable), newDirection);
	}
	
	private void verifyClockwiseTurnFromDirectionFaces(final Direction direction, final Direction newDirection) {
		verifyTurnActionDirectionFaces(() -> direction.turnClockwise(turnable), newDirection);
	}
	
	private void verifyTurnActionDirectionFaces(final Runnable turnAction, final Direction newDirection) {
		turnAction.run();
		verify(turnable).face(newDirection);
	}
	
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
	public void WhenTurningCounterClockwise_FacesDirectionInOneCounterClockwiseMovement() {
		verifyCounterClockwiseTurnFromDirectionFaces(CardinalDirection.NORTH, CardinalDirection.WEST);
		verifyCounterClockwiseTurnFromDirectionFaces(CardinalDirection.WEST, CardinalDirection.SOUTH);
		verifyCounterClockwiseTurnFromDirectionFaces(CardinalDirection.SOUTH, CardinalDirection.EAST);
		verifyCounterClockwiseTurnFromDirectionFaces(CardinalDirection.EAST, CardinalDirection.NORTH);
	}
	
	@Test
	public void WhenMovingClockwise_ReturnsCardinalDirectionInOneClockwiseMovement() {
		verifyClockwiseTurnFromDirectionFaces(CardinalDirection.NORTH, CardinalDirection.EAST);
		verifyClockwiseTurnFromDirectionFaces(CardinalDirection.EAST, CardinalDirection.SOUTH);
		verifyClockwiseTurnFromDirectionFaces(CardinalDirection.SOUTH, CardinalDirection.WEST);
		verifyClockwiseTurnFromDirectionFaces(CardinalDirection.WEST, CardinalDirection.NORTH);
	}
	
	@Test
	public void WhenDirecting_TranslatesByCorrectPosition() {
		CardinalDirection.NORTH.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByCoordinateTo(new Position(0, 1), directable);
		CardinalDirection.SOUTH.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByCoordinateTo(new Position(0, -1), directable);
		CardinalDirection.EAST.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByCoordinateTo(new Position(1, 0), directable);
		CardinalDirection.WEST.directDirectableFrom(directable, fromCoordinate);
		verify(fromCoordinate).translateByCoordinateTo(new Position(-1, 0), directable);
	}
}