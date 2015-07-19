package toyrobotsimulator;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaceCommandTest {

	private PlaceCommand placeCommandWithCorrectParameters;
	@Mock
	private CommandParameters correctCommandParameters;
	@Mock
	private PlaceAction placeAction;

	@Before
	public void setUp() throws Exception {
		correctCommandParameters = createCommandParameters("1", "2", "NORTH");
		placeCommandWithCorrectParameters = new PlaceCommand(correctCommandParameters, placeAction);
	}

	private CommandParameters createCommandParameters(final String ... parameters) {
		return new CommandParameters() {
			@Override
			public void listTo(final CommandParameterReceiver commandParameterReceiver) {
				for(String parameter : parameters)
					commandParameterReceiver.receive(parameter);
			}
		};
	}
	
	@Test
	public void WhenExecutingCommand_WithCorrectParameters_ShouldCallPlace() {
		placeCommandWithCorrectParameters.execute();
		verify(placeAction).place(eq(new Position(1, 2)), eq(new Direction("NORTH")));
	}
}