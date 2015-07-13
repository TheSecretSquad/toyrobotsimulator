package toyrobotsimulator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaceCommandTest {

	private PlaceCommand placeCommand;
	@Mock
	private CommandParameters commandParameters;
	@Mock
	private PlaceAction placeAction;

	@Before
	public void setUp() throws Exception {
		placeCommand = new PlaceCommand(commandParameters, placeAction);
	}

	@Test
	public void test() {
		
		
	}
}