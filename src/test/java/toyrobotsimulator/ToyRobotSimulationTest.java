package toyrobotsimulator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulationTest {

	private ToyRobotSimulation toyRobotSimulation;
	private CapturingPrintStream capturingPrintStream;
	private LineMatcher lineMatcher;
	
	private void verifySimulationReportsRunning() {
		assertTrue(lineMatcher.isFirstIn(runningOutput(), capturedContents()));
	}
	
	private String runningOutput() {
		return "Running";
	}
	
	private void verifySimulationReportsNotPlaced() {
		verifyContentsContainsLine(notPlacedOutput());
	}
	
	private String notPlacedOutput() {
		return "Not placed";
	}

	private void verifySimulationReportsPosition(final int x, final int y, final String facing) {
		verifyContentsContainsLine(positionOutput(x, y, facing));
	}
	
	private String positionOutput(final int x, final int y, final String facing) {
		return String.format("%s,%s,%s", x, y, facing);
	}
	
	private String capturedContents() {
		return capturingPrintStream.contents();
	}
	
	private void verifyContentsContainsLine(final String line) {
		assertTrue(lineMatcher.containsIn(line, capturedContents()));
	}
	
	@Before
	public void setUp() throws Exception {
		capturingPrintStream = new CapturingPrintStream();
		lineMatcher = new LineMatcher();
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
		toyRobotSimulation.enterCommand(new RobotCommand("REPORT"));
		toyRobotSimulation.run();
		verifySimulationReportsNotPlaced();
	}
	
	@Test
	public void WhenRunning_IfRobotIsPlacedAt00FacingEast_Reports00East() {
		RobotCommands commands = new RobotCommands("PLACE 0,0,EAST", "REPORT");
		toyRobotSimulation.enterCommands(commands);
		toyRobotSimulation.run();
		verifySimulationReportsPosition(0, 0, "EAST");
	}
}