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
	private final CommandName moveCommandName = new ToyRobotCommandName("MOVE");
	
	@Before
	public void setUp() throws Exception {
		toyRobotCommandInterpreter = new ToyRobotCommandInterpreter(interpretedCommandReceiver);
	}

	@Test
	public void WhenInterpretingCommands_ReadsCommandsToCommandReceiver() {
		toyRobotCommandInterpreter.interpretCommands(commands);
		verify(commands).readTo(toyRobotCommandInterpreter);
	}
	
	@Test
	public void WhenSentCommandName_SendsCommandToInterpetedCommandReceiver() {
		toyRobotCommandInterpreter.sendCommandName(moveCommandName);
		verify(interpretedCommandReceiver).issueCommand(isA(MoveCommand.class));
	}
}