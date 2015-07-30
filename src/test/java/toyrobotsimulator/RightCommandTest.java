package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RightCommandTest {

	private RightCommand rightCommand;
	@Mock
	private RightAction rightAction;	
	
	@Before
	public void setUp() throws Exception {
		rightCommand = new RightCommand(rightAction);
	}

	@Test
	public void WhenExecutingCommand_ShouldTurnRight() {
		rightCommand.execute();
		verify(rightAction).turnRight();
	}
}