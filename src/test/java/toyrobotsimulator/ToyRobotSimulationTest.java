package toyrobotsimulator;

import static org.junit.Assert.assertEquals;

import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulationTest {

	private ToyRobotSimulation toyRobotSimulation;
	private StringOutputStream stringOutputStream;
	private PrintStream printStream;
	
	private void verifySimulationReportsRunning() {
		String expectedContents = "Running" + System.lineSeparator();
		assertEquals(expectedContents, stringOutputStream.contents());
	}
	
	@Before
	public void setUp() throws Exception {
		stringOutputStream = new StringOutputStream();
		printStream = new PrintStream(stringOutputStream);
		toyRobotSimulation = new ToyRobotSimulation(printStream);
	}

	@Test
	public void WhenRunning_ReportsRunningStatus() {
		toyRobotSimulation.run();
		verifySimulationReportsRunning();
	}
}