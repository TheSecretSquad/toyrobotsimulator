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
	@Mock
	private Direction anyDirection;
	@Mock
	private EnvironmentObject environmentObject;
	
	@Before
	public void setUp() throws Exception {
		tableTop = new TableTop(5);
		validPosition = new Position(1,1);
		invalidPosition = new Position(0,0);
	}

	@Test
	public void WhenPlacingAtPosition_IfPositionIsValid_ShouldPlaceObjectAtPosition() {
		tableTop.tryPlaceObjectAtPositionFacing(environmentObject, validPosition, anyDirection);
		verify(environmentObject).placeAtPositionFacing(validPosition, anyDirection);
	}
	
	@Test
	public void WhenPlacingAtPosition_IfPositionIsInvalid_ShouldNotPlaceObjectAtPosition() {
		tableTop.tryPlaceObjectAtPositionFacing(environmentObject, invalidPosition, anyDirection);
		verify(environmentObject, never()).placeAtPositionFacing(invalidPosition, anyDirection);
	}
	
//	@Test
//	public void WhenReporting_IfPlacedAtValidPosition_ShouldReportPlacedPosition() {
//		tableTop.tryPlaceObjectAtPosition(environmentObject, validPosition);
//		tableTop.reportTo(positionReportStream);
//		verify(positionReportStream).report(validPosition);
//	}
//	
//	@Test
//	public void WhenMovedInDirection_IfPlacedAtPosition_ShouldDirectFromPosition() {
//		tableTop.tryPlaceObjectAtPosition(environmentObject, validPosition);
//		tableTop.moveInDirection(anyDirection);
//		verify(anyDirection).directDirectableFrom(tableTop, validPosition);
//	}
//	
//	@Test
//	public void WhenReporting_AfterDirectedToValidPosition_ShouldReportNewPosition() {
//		tableTop.tryPlaceObjectAtPosition(environmentObject, validPosition);
//		tableTop.directTo(validPosition);
//		tableTop.reportTo(positionReportStream);
//		verify(positionReportStream).report(validPosition);
//	}
//	
//	@Test(expected=NothingPlacedException.class)
//	public void WhenDirectingToPosition_IfNoObjectPlaced_ShouldThrowException() {
//		tableTop.directTo(validPosition);
//	}
//	
//	@Test(expected=NothingPlacedException.class)
//	public void WhenPlacing_IfNullObjectPlaced_ShouldThrowException() {
//		tableTop.tryPlaceObjectAtPosition(null, validPosition);
//	}
}