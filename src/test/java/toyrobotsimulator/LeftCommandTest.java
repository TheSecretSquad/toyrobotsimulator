package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LeftCommandTest {

	private LeftCommand leftCommand;
	@Mock
	private LeftAction leftAction;	
	
	@Before
	public void setUp() throws Exception {
		leftCommand = new LeftCommand(leftAction);
	}

	@Test
	public void WhenExecutingCommand_ShouldTurnLeft() {
		leftCommand.execute();
		verify(leftAction).turnLeft();
	}
}