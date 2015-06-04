package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotCommandsTest {

	private ToyRobotCommands toyRobotCommands;
	@Mock
	private CommandReceiver commandReceiver;
	@Mock
	private CommandSource commandSource;
	
	@Before
	public void setUp() throws Exception {
		toyRobotCommands = new ToyRobotCommands(commandSource);
	}

	@Test
	public void WhenReadingCommands_ParsesCommandSourceToCommandReceiver() {
		toyRobotCommands.readTo(commandReceiver);
		verify(commandSource).parseTo(commandReceiver);
	}
}