package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulationUnitTest {

	private ToyRobotSimulation toyRobotSimulation;
	@Mock
	private PrintStream printStream;
	@Mock
	private RobotCommands simulationCommands;
	@Mock
	private RobotCommand aRobotCommand;
	@Mock
	private RobotCommands commands;
	
	@Before
	public void setUp() throws Exception {
		toyRobotSimulation = new ToyRobotSimulation(printStream, simulationCommands);
	}
	
	@Test
	public void EnteringACommand_AddsTheCommandToTheCommands() {
		toyRobotSimulation.enterCommand(aRobotCommand);
		verify(simulationCommands).add(aRobotCommand);
	}
	
	@Test
	public void EnteringCommands_AddsTheCommandsToTheCommands() {
		toyRobotSimulation.enterCommands(commands);
		verify(simulationCommands).add(commands);
	}
}
