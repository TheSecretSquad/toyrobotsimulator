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
	@Mock
	private CommandParameters commandParameters;
	@Mock
	private ToyRobot toyRobot;
	private final CommandName moveCommandName = new CommandName("MOVE");
	private final CommandName leftCommandName = new CommandName("LEFT");
	private final CommandName rightCommandName = new CommandName("RIGHT");
	private final CommandName reportCommandName = new CommandName("REPORT");
	private final CommandName placeCommandName = new CommandName("PLACE");
	
	@Before
	public void setUp() throws Exception {
		toyRobotCommandInterpreter = new ToyRobotCommandInterpreter(interpretedCommandReceiver, toyRobot);
	}

	@Test
	public void WhenInterpretingCommands_ReadsCommandsToCommandReceiver() {
		toyRobotCommandInterpreter.interpretCommands(commands);
		verify(commands).readTo(toyRobotCommandInterpreter);
	}
	
	@Test
	public void WhenSentMoveCommandName_SendsMoveCommandToInterpetedCommandReceiver() {
		toyRobotCommandInterpreter.sendCommandName(moveCommandName);
		verify(interpretedCommandReceiver).issueCommand(isA(MoveCommand.class));
	}
	
	@Test
	public void WhenSentLeftCommandName_SendsLeftCommandToInterpetedCommandReceiver() {
		toyRobotCommandInterpreter.sendCommandName(leftCommandName);
		verify(interpretedCommandReceiver).issueCommand(isA(LeftCommand.class));
	}
	
	@Test
	public void WhenSentRightCommandName_SendsRightCommandToInterpetedCommandReceiver() {
		toyRobotCommandInterpreter.sendCommandName(rightCommandName);
		verify(interpretedCommandReceiver).issueCommand(isA(RightCommand.class));
	}
	
	@Test
	public void WhenSentReportCommandName_SendsReportCommandToInterpetedCommandReceiver() {
		toyRobotCommandInterpreter.sendCommandName(reportCommandName);
		verify(interpretedCommandReceiver).issueCommand(isA(ReportCommand.class));
	}
	
	@Test
	public void WhenSentPlaceCommandName_SendsPlaceCommandToInterpetedCommandReceiver() {
		toyRobotCommandInterpreter.sendCommandNameWithParameters(placeCommandName, commandParameters);
		verify(interpretedCommandReceiver).issueCommand(isA(PlaceCommand.class));
	}
}