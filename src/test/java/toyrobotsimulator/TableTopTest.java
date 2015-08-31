package toyrobotsimulator;

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
	private Position validPosition2;
	private Position invalidPosition;

	@Mock
	private EnvironmentObject environmentObject;
	@Mock
	private EnvironmentObject environmentObject2;
	@Mock
	private PlaceSuccessHandler placeSuccessHandler;
	
	@Before
	public void setUp() throws Exception {
		tableTop = new TableTop(5);
		validPosition = new Position(1,1);
		validPosition2 = new Position(2,2);
		invalidPosition = new Position(0,0);
	}

	@Test
	public void WhenPlacingAtPosition_IfPositionIsValid_ShouldPlaceObjectAtPosition() {
		tableTop.tryPlaceObjectAtPositionOnSuccess(environmentObject, validPosition, placeSuccessHandler);
		verify(placeSuccessHandler).placeSuccessful();
	}
	
	@Test
	public void WhenPlacingAtPosition_IfPositionIsInvalid_ShouldNotPlaceObjectAtPosition() {
		tableTop.tryPlaceObjectAtPositionOnSuccess(environmentObject, invalidPosition, placeSuccessHandler);
		verify(placeSuccessHandler, never()).placeSuccessful();
	}
	
	@Test
	public void WhenMovingToPosition_IfEnvironmentObjectNotFoundInEnvironment_ShouldNotMove() {
		tableTop.tryPlaceObjectAtPositionOnSuccess(environmentObject, validPosition, placeSuccessHandler);
		tableTop.tryMoveObjectTo(environmentObject2, validPosition2);
		verify(environmentObject, never()).moveTo(validPosition2);
	}
	
	@Test
	public void WhenMovingToPosition_IfPlacedAtValidPositionAndNewPositionIsValid_ShouldMoveToNewPosition() {
		tableTop.tryPlaceObjectAtPositionOnSuccess(environmentObject, validPosition, placeSuccessHandler);
		tableTop.tryMoveObjectTo(environmentObject, validPosition2);
		verify(environmentObject).moveTo(validPosition2);
	}
	
	@Test
	public void WhenMovingToPosition_IfPlacedAtValidPositionAndNewPositionIsInvalid_ShouldNotMoveToNewPosition() {
		tableTop.tryPlaceObjectAtPositionOnSuccess(environmentObject, validPosition, placeSuccessHandler);
		tableTop.tryMoveObjectTo(environmentObject, invalidPosition);
		verify(environmentObject, never()).moveTo(validPosition2);
	}
}