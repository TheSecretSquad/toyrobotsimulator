package toyrobotsimulator;

import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulationTest {

	private ToyRobotSimulation toyRobotSimulation;
	@Mock
	private PrintStream printStream;
	@Mock
	private Commands commands;
	@Mock
	private CommandInterpreter commandInterpreter;
	@Mock
	private Board board;
	
	@Before
	public void setUp() throws Exception {
		toyRobotSimulation = new ToyRobotSimulation(printStream, board, commandInterpreter, commands);
	}

	@Test
	public void WhenRunning_ReportsRunningStatus() {
		toyRobotSimulation.run();
		verify(printStream).println("Running");
	}
	
	@Test
	public void WhenRun_SendsInterpretedCommandsToTheBoard() {
		toyRobotSimulation.run();
		verify(commandInterpreter).interpretCommandsTo(commands, board);
	}
}