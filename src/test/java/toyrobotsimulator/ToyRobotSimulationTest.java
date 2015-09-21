package toyrobotsimulator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulationTest {

	private ToyRobotSimulation toyRobotSimulation;
	private CapturingPrintStream capturingPrintStream;
	
	private void verifySimulationReportsRunning() {
		String expectedContents = "Running" + System.lineSeparator();
		assertEquals(expectedContents, capturingPrintStream.contents());
	}
	
	@Before
	public void setUp() throws Exception {
		capturingPrintStream = new CapturingPrintStream();
		toyRobotSimulation = new ToyRobotSimulation(capturingPrintStream);
	}

	@Test
	public void WhenRunning_ReportsRunningStatus() {
		toyRobotSimulation.run();
		verifySimulationReportsRunning();
	}
}