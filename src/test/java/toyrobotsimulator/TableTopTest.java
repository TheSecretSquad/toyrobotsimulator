package toyrobotsimulator;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TableTopTest {

	private TableTop tableTop;
	private Position validPosition;
	private Position invalidPosition;
	private Direction validDirection;
	private Position validPositionInValidDirection;
	@Mock
	private Direction anyDirection;
	@Mock
	private EnvironmentObject environmentObject;
	@Mock
	private PositionReportStream positionReportStream;
	
	@Before
	public void setUp() throws Exception {
		tableTop = new TableTop(5);
		validPosition = new Position(1,1);
		invalidPosition = new Position(0,0);
		validDirection = CardinalDirection.NORTH; // A valid direction is one that does not cause out of bounds
		validPositionInValidDirection = new Position(1,2);
	}

	@Test
	public void WhenPlacingAtPosition_IfPositionIsValid_ShouldNotDetectOutOfBounds() {
		tableTop.placeObjectAtPosition(environmentObject, validPosition);
		verify(environmentObject, never()).handleBoundaryWith(any(OutOfBoundsDecision.class));
	}
	
	@Test
	public void WhenPlacingAtPosition_IfPositionIsInvalid_ShouldDetectOutOfBounds() {
		tableTop.placeObjectAtPosition(environmentObject, invalidPosition);
		verify(environmentObject).handleBoundaryWith(tableTop);
	}
	
	@Test
	public void WhenReporting_IfPlacedAtValidPosition_ShouldReportPlacedPosition() {
		tableTop.placeObjectAtPosition(environmentObject, validPosition);
		tableTop.reportTo(positionReportStream);
		verify(positionReportStream).report(validPosition);
	}
	
	@Test
	public void WhenMovedInDirection_IfPlacedAtPosition_ShouldDirectFromPosition() {
		tableTop.placeObjectAtPosition(environmentObject, validPosition);
		tableTop.moveInDirection(anyDirection);
		verify(anyDirection).directDirectableFrom(tableTop, validPosition);
	}
	
	@Test
	public void WhenReporting_AfterDirectedToValidPosition_ShouldReportNewPosition() {
		tableTop.placeObjectAtPosition(environmentObject, validPosition);
		tableTop.directTo(validPosition);
		tableTop.reportTo(positionReportStream);
		verify(positionReportStream).report(validPosition);
	}
	
	@Test(expected=NothingPlacedException.class)
	public void WhenDirectingToPosition_IfNoObjectPlaced_ShouldThrowException() {
		tableTop.directTo(validPosition);
	}
	
	@Test(expected=NothingPlacedException.class)
	public void WhenPlacing_IfNullObjectPlaced_ShouldThrowException() {
		tableTop.placeObjectAtPosition(null, validPosition);
	}
}