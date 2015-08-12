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
	private EdgeDetector edgeDetector;
	
	@Before
	public void setUp() throws Exception {
		tableTop = new TableTop(5);
		validPosition = new Position(1,1);
		invalidPosition = new Position(0,0);
	}

	@Test
	public void WhenPlacingAtPosition_IfPositionIsValid_ShouldNotCallEdgeDetector() {
		tableTop.placeAtPositionWith(validPosition, edgeDetector);
		verify(edgeDetector, never()).detect(any(Edge.class));
	}
	
	@Test
	public void WhenPlacingAtPosition_IfPositionIsInvalid_ShouldCallEdgeDetector() {
		tableTop.placeAtPositionWith(invalidPosition, edgeDetector);
		verify(edgeDetector).detect(tableTop);
	}
}