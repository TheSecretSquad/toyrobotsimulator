package toyrobotsimulator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
	private Position translatingByPosition;
	private Position translatedPosition;
	private Position equalsInstance;
	private Position equalsOtherInstance;

	@Mock
	private Direction direction;
	@Mock
	private Directable directable;
	@Mock
	private BetweenBoundsResult betweenBoundsResult;
	
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
		translatingByPosition = new Position(2,2);
		translatedPosition = new Position(3,3);
		equalsInstance = new Position(1,1);
		equalsOtherInstance = new Position(1,1);
	}

	private void verifyIsNotBetweenWithBoundsCommutativity(final Position position) {
		position.ifBetweenPoints(lowerBound, upperBound, betweenBoundsResult);
		verify(betweenBoundsResult, never()).between();
		position.ifBetweenPoints(upperBound, lowerBound, betweenBoundsResult);
		verify(betweenBoundsResult, never()).between();
	}
	
	@Test
	public void WhenComparingEqual_IsValueEqual() {
		assertEquals(new Position(1, 1), new Position(1, 1));
		assertEquals(new Position(1, 2), new Position(1, 2));
	}
	
	@Test
	public void WhenComparingEqual_AndDifferentXPositions_IsNotEqual() {
		assertNotEquals(new Position(1, 1), new Position(2, 1));
	}
	
	@Test
	public void WhenComparingEqual_AndDifferentYPositions_IsNotEqual() {
		assertNotEquals(new Position(1, 1), new Position(1, 2));
	}
	
	@Test
	public void WhenComparingEqual_AndSameObject_IsEqual() {
		assertEquals(equalsInstance, equalsInstance);
	}
	
	@Test
	public void WhenComparingEqual_AndNull_IsNotEqual() {
		assertNotEquals(equalsInstance, null);
	}
	
	@Test
	public void WhenComparingEqual_AndOtherType_IsNotEqual() {
		assertNotEquals(equalsInstance, new Object());
	}
	
	@Test
	public void WhenComparingEqual_IsSymmetric() {
		assertTrue(equalsInstance.equals(equalsOtherInstance));
		assertTrue(equalsOtherInstance.equals(equalsInstance));
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
	public void WhenIsBetween_IfLowOutOfBoundsX_ShouldBeFalse() {
		verifyIsNotBetweenWithBoundsCommutativity(lowOutOfBoundsX);
	}
	
	@Test
	public void WhenIsBetween_IfLowOutOfBoundsY_ShouldBeFalse() {
		verifyIsNotBetweenWithBoundsCommutativity(lowOutOfBoundsY);
	}
	
	@Test
	public void WhenIsBetween_IfUpperOutOfBoundsX_ShouldBeFalse() {
		verifyIsNotBetweenWithBoundsCommutativity(upperOutOfBoundsX);
	}
	
	@Test
	public void WhenIsBetween_IfUpperOutOfBoundsY_ShouldBeFalse() {
		verifyIsNotBetweenWithBoundsCommutativity(upperOutOfBoundsY);
	}
	
	@Test
	public void WhenIsBetween_IfLowOutOfBoundsXY_ShouldBeFalse() {
		verifyIsNotBetweenWithBoundsCommutativity(lowOutOfBoundsXY);
	}
	
	@Test
	public void WhenIsBetween_IfUpperOutOfBoundsXY_ShouldBeFalse() {
		verifyIsNotBetweenWithBoundsCommutativity(upperOutOfBoundsXY);
	}
	
	@Test
	public void WhenTranslatingByPosition_ShouldDirectToTranslatedPosition() {
		translatingPositionStart.translateByCoordinateTo(translatingByPosition, directable);
		verify(directable).directTo(translatedPosition);
	}
}