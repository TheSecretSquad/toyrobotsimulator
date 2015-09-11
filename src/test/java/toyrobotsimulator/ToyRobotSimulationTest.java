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
	
	private void verifySimulationReportsRunning() {
		verify(printStream).println("Running");
	}
	
	@Before
	public void setUp() throws Exception {
		toyRobotSimulation = new ToyRobotSimulation(printStream);
	}

	@Test
	public void WhenRunning_ReportsRunningStatus() {
		toyRobotSimulation.run();
		verifySimulationReportsRunning();
	}
}