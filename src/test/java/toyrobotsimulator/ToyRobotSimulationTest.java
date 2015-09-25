package toyrobotsimulator;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulationTest {

	private ToyRobotSimulation toyRobotSimulation;
	private CapturingPrintStream capturingPrintStream;
	
	private void verifySimulationReportsRunning() {
		assertTrue(capturedContentsMatches(atBeginning(runningOutput())));
	}
	
	private String atBeginning(String value) {
		return "^" + value;
	}
	
	private String capturedContents() {
		return capturingPrintStream.contents();
	}
	
	private String runningOutput() {
		return outputLineWithString("Running");
	}
	
	private boolean capturedContentsMatches(final String regex) {
		return Pattern
				.compile(regex)
				.matcher(capturedContents())
				.find();
	}
	
	private void verifySimulationReportsNotPlaced() {
		assertTrue(capturedContentsMatches(notPlacedOutput()));
	}
	
	private String notPlacedOutput() {
		return outputLineWithString("Not placed");
	}
	
	private String outputLineWithString(final String string) {
		return string + System.lineSeparator();
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
	
	@Test
	public void WhenRunning_IfRobotIsNotPlaced_AndReportCommandEntered_ReportsNotPlaced() {
		// No place command entered
		toyRobotSimulation.enterCommand("REPORT");
		toyRobotSimulation.run();
		verifySimulationReportsNotPlaced();
	}
}