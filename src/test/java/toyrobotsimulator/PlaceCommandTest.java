package toyrobotsimulator;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaceCommandTest {

	private PlaceCommand placeCommand;
	@Mock
	private PlaceAction placeAction;

	private CommandParameters commandParameters(final String ... parameters) {
		return new CommandParameters() {
			@Override
			public void listTo(final CommandParameterReceiver commandParameterReceiver) {
				for(String parameter : parameters)
					commandParameterReceiver.receive(parameter);
			}
		};
	}
	
	private void setupPlaceCommandWith(final CommandParameters commandParameters) {
		placeCommand = new PlaceCommand(commandParameters, placeAction);
	}
	
	@Test
	public void WhenExecutingCommand_WithCorrectParameters_ShouldCallPlaceWithPositionAndDirection() {
		setupPlaceCommandWith(commandParameters("1", "2", "NORTH"));
		placeCommand.execute();
		verify(placeAction).place(eq(new Position(1, 2)), eq(new Direction("NORTH")));
	}
}