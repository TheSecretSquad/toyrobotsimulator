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
	private OutOfBoundsDetector outOfBoundsDetector;
	@Mock
	private PositionReportStream positionReportStream;
	
	@Before
	public void setUp() throws Exception {
		tableTop = new TableTop(5);
		validPosition = new Position(1,1);
		invalidPosition = new Position(0,0);
		validDirection = CardinalDirection.NORTH;
		validPositionInValidDirection = new Position(1,2);
	}

	@Test
	public void WhenPlacingAtPosition_IfPositionIsValid_ShouldNotDetectOutOfBounds() {
		tableTop.placeAtPositionWith(validPosition, outOfBoundsDetector);
		verify(outOfBoundsDetector, never()).detect(any(OutOfBoundsHandler.class));
	}
	
	@Test
	public void WhenPlacingAtPosition_IfPositionIsInvalid_ShouldDetectOutOfBounds() {
		tableTop.placeAtPositionWith(invalidPosition, outOfBoundsDetector);
		verify(outOfBoundsDetector).detect(tableTop);
	}
	
	@Test
	public void WhenReporting_IfPlacedAtValidPosition_ShouldReportPlacedPosition() {
		tableTop.placeAtPositionWith(validPosition, outOfBoundsDetector);
		tableTop.reportTo(positionReportStream);
		verify(positionReportStream).report(validPosition);
	}
	
	@Test
	public void WhenReporting_IfPlacedAtValidPositionAndMovedInValidDirection_ShouldReportPositionMovedOnceInDirection() {
		// A valid direction is one that does not cause out of bounds
		tableTop.placeAtPositionWith(validPosition, outOfBoundsDetector);
		tableTop.moveInDirectionWith(validDirection, outOfBoundsDetector);
		tableTop.reportTo(positionReportStream);
		verify(positionReportStream).report(validPositionInValidDirection);
	}
}