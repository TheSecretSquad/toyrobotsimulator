package toyrobotsimulator;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RealToyRobotTest {

	private RealToyRobot realToyRobot;
	private Position startingPosition;
	@Mock
	private Command command;
	@Mock
	private Direction startingDirection;
	@Mock
	private Environment environment;
	@Mock
	private EnvironmentObject environmentObject;
	@Mock
	private ReportStream reportStream;

	
	// Robot needs to filter commands before
	// they are reflected in the environment
	// Environment?
	@Before
	public void setUp() throws Exception {
		startingPosition = new Position(1, 1);
		realToyRobot = new RealToyRobot(environment, environmentObject, reportStream);
	}
	
	private <T> void verifyDiscardsActionWithMock(final Consumer<ToyRobot> toyRobotConsumer, final T mock) {
		toyRobotConsumer.accept(realToyRobot);
		verifyZeroInteractions(mock);
	}
	
	private void verifyDiscardsActionWithEnvironment(final Consumer<ToyRobot> toyRobotConsumer) {
		verifyDiscardsActionWithMock(toyRobotConsumer, environment);
	}
	
	private void verifyDiscardsActionWithReportStream(final Consumer<ToyRobot> toyRobotConsumer) {
		verifyDiscardsActionWithMock(toyRobotConsumer, reportStream);
	}
	
	private void placeAtStartingPositionAndDirection() {
		realToyRobot.place(startingPosition, startingDirection);
	}

	private void verifyMoveInDirection(final Direction direction) {
		verify(environment).moveInDirection(direction);
	}
	
	@Test
	public void WhenIssuedACommand_ShouldExecuteTheCommand() {
		realToyRobot.issueCommand(command);
		verify(command).execute();
	}
	
	@Test
	public void WhenMoving_IfNotYetPlaced_ShouldDiscardCommand() {
		// No call to place()
		verifyDiscardsActionWithEnvironment(toyRobot -> toyRobot.move());
	}
	
	@Test
	public void WhenTurningLeft_IfNotYetPlaced_ShouldDiscardCommand() {
		// No call to place()
		verifyDiscardsActionWithEnvironment(toyRobot -> toyRobot.turnLeft());
	}
	
	@Test
	public void WhenTurningRight_IfNotYetPlaced_ShouldDiscardCommand() {
		// No call to place()
		verifyDiscardsActionWithEnvironment(toyRobot -> toyRobot.turnRight());
	}
	
	@Test
	public void WhenReporting_IfNotYetPlaced_ShouldDiscardCommand() {
		// No call to place()
		verifyDiscardsActionWithReportStream(toyRobot -> toyRobot.report());
	}
	
	@Test
	public void WhenPlacing_ShouldPlaceAtPositionInEnvironment() {
		placeAtStartingPositionAndDirection();
		verify(environment).placeObjectAtPosition(environmentObject, startingPosition);
	}
	
	@Test
	public void WhenMoving_IfPlaced_ShouldMoveInFacingDirection() {
		placeAtStartingPositionAndDirection();
		realToyRobot.move();
		verifyMoveInDirection(startingDirection);
	}
	
	@Test
	public void WhenTurningRight_IfPlaced_ShouldTurnInClockwiseDirection() {
		placeAtStartingPositionAndDirection();
		realToyRobot.turnRight();
		verify(startingDirection).clockwise();
	}
	
	@Test
	public void WhenTurningLeft_IfPlaced_ShouldTurnInCounterClockwiseDirection() {
		placeAtStartingPositionAndDirection();
		realToyRobot.turnLeft();
		verify(startingDirection).counterClockwise();
	}
	
	@Test
	public void WhenMoving_IfPlacedAndTurned_ShouldMoveInFacingDirection() {
		placeAtStartingPositionAndDirection();
		realToyRobot.turnLeft();
		realToyRobot.move();
		verifyMoveInDirection(startingDirection.counterClockwise());
	}
	
	@Test
	public void WhenReporting_IfPlaced_ShouldPrintFacingDirection() {
		placeAtStartingPositionAndDirection();
		realToyRobot.report();
		verify(reportStream).report(startingDirection);
	}
	
	@Test
	public void WhenReporting_IfPlaced_ShouldReportEnvironment() {
		placeAtStartingPositionAndDirection();
		realToyRobot.report();
		verify(environment).reportTo(reportStream);
	}
}