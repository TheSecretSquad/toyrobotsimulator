package toyrobotsimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PositionTest {

	private Position lowerBound;
	private Position upperBound;
	private Position lowOutOfBoundsX;
	private Position lowOutOfBoundsY;
	private Position upperOutOfBoundsX;
	private Position upperOutOfBoundsY;
	private Position lowOutOfBoundsXY;
	private Position upperOutOfBoundsXY;
	private Position translatingPositionStart;
	private Position directionMoveFromTestPosition;
	@Mock
	private Direction direction;
	
	@Before
	public void setUp() throws Exception {
		lowerBound = new Position(1,1);
		upperBound = new Position(3,3);
		lowOutOfBoundsX = new Position(0,1);
		lowOutOfBoundsY = new Position(1,0);
		upperOutOfBoundsX = new Position(4,3);
		upperOutOfBoundsY = new Position(3,4);
		lowOutOfBoundsXY = new Position(0,0);
		upperOutOfBoundsXY = new Position(4,4);
		translatingPositionStart = new Position(1,1);
		directionMoveFromTestPosition = new Position(9,9);
		when(direction.moveFrom(any(Position.class))).thenReturn(directionMoveFromTestPosition);
	}

	private void assertIsBetweenIsFalseWithBoundsCommutativity(final Position position) {
		assertFalse(position.isBetween(lowerBound, upperBound));
		assertFalse(position.isBetween(upperBound, lowerBound));
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
	
	@Test
	public void WhenHorizontallyBetween_IfLowOutOfBoundsX_ShouldBeFalse() {
		assertIsBetweenIsFalseWithBoundsCommutativity(lowOutOfBoundsX);
	}
	
	@Test
	public void WhenHorizontallyBetween_IfLowOutOfBoundsY_ShouldBeFalse() {
		assertIsBetweenIsFalseWithBoundsCommutativity(lowOutOfBoundsY);
	}
	
	@Test
	public void WhenHorizontallyBetween_IfUpperOutOfBoundsX_ShouldBeFalse() {
		assertIsBetweenIsFalseWithBoundsCommutativity(upperOutOfBoundsX);
	}
	
	@Test
	public void WhenHorizontallyBetween_IfUpperOutOfBoundsY_ShouldBeFalse() {
		assertIsBetweenIsFalseWithBoundsCommutativity(upperOutOfBoundsY);
	}
	
	@Test
	public void WhenHorizontallyBetween_IfLowOutOfBoundsXY_ShouldBeFalse() {
		assertIsBetweenIsFalseWithBoundsCommutativity(lowOutOfBoundsXY);
	}
	
	@Test
	public void WhenHorizontallyBetween_IfUpperOutOfBoundsXY_ShouldBeFalse() {
		assertIsBetweenIsFalseWithBoundsCommutativity(upperOutOfBoundsXY);
	}
	
	@Test
	public void WhenTranslating_ShouldMovePositionInDirection() {
		translatingPositionStart.translateIn(direction);
		verify(direction).moveFrom(translatingPositionStart);
	}
	
	@Test
	public void WhenTranslating_ShouldReturnPositionFromMovingInDirection() {
		Position translatedPosition = translatingPositionStart.translateIn(direction);
		assertEquals(directionMoveFromTestPosition, translatedPosition);
	}
}