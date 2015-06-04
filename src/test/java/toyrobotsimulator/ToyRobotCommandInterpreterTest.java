package toyrobotsimulator;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotCommandInterpreterTest {

	private ToyRobotCommandInterpreter toyRobotCommandInterpreter;
	@Mock
	private Commands commands;
	@Mock
	private InterpretedCommandReceiver interpretedCommandReceiver;
	
	@Before
	public void setUp() throws Exception {
		toyRobotCommandInterpreter = new ToyRobotCommandInterpreter();
	}

	@Test
	public void WhenInterpretingCommands_ReadsCommandsToReceiver() {
		toyRobotCommandInterpreter.interpretCommandsTo(commands, interpretedCommandReceiver);
		verify(commands).readTo(toyRobotCommandInterpreter);
	}
}