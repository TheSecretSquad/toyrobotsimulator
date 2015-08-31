package toyrobotsimulator;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
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
	private Position aPosition;
	private Position movedToPosition;
	@Mock
	private Command command;
	@Mock
	private Direction aDirection;
	@Mock
	private Direction facedInDirection;
	@Mock
	private Environment environment;
	@Mock
	private ReportStream reportStream;

	
	// Robot needs to filter commands before
	// they are reflected in the environment
	// Environment?
	@Before
	public void setUp() throws Exception {
		aPosition = new Position(1, 1);
		movedToPosition = new Position(2, 2);
		realToyRobot = new RealToyRobot(environment, reportStream);
	}
	
	private <T> void verifyDiscardsActionWithMock(final Consumer<ToyRobot> toyRobotConsumer, final T mock) {
		toyRobotConsumer.accept(realToyRobot);
		verifyZeroInteractions(mock);
	}
	
	private void verifyDiscardsActionWithEnvironment(final Consumer<ToyRobot> toyRobotConsumer) {
		verifyDiscardsActionWithMock(toyRobotConsumer, environment);
	}
	
	private void verifyDiscardsActionWithDirection(final Consumer<ToyRobot> toyRobotConsumer) {
		verifyDiscardsActionWithMock(toyRobotConsumer, aDirection);
	}
	
	private void verifyDiscardsActionWithReportStream(final Consumer<ToyRobot> toyRobotConsumer) {
		verifyDiscardsActionWithMock(toyRobotConsumer, reportStream);
	}
	
	private void issueCommand() {
		realToyRobot.issueCommand(command);
	}
	
	private void doPlaceActionAtPositionFacingADirection() {
		realToyRobot.place(aPosition, aDirection);
	}
	
	private void placeAtAPositionAndFacingADirection() {
		realToyRobot.placeAtPositionFacing(aPosition, aDirection);
	}
	
	@Test
	public void WhenIssuedCommand_ShouldExecuteCommand() {
		issueCommand();
		verify(command).execute();
	}
	
	@Test
	public void WhenPlacing_ShouldTryToPlaceSelfAtPosition() {
		doPlaceActionAtPositionFacingADirection();
		verify(environment).tryPlaceObjectAtPositionOnSuccess(eq(realToyRobot), eq(aPosition), isA(ToyRobotPlaceSuccessfulHandler.class));
	}
	
	@Test
	public void WhenMoving_IfPlaced_ShouldDirectToNewPosition() {
		placeAtAPositionAndFacingADirection();
		realToyRobot.move();
		verify(aDirection).directDirectableFrom(realToyRobot, aPosition);
	}
	
	@Test
	public void WhenMoving_IfNotPlaced_ShouldDiscardAction() {
		verifyDiscardsActionWithDirection((robot) -> robot.move());
	}
	
	@Test
	public void WhenDirectedToPosition_ShouldTryToMoveToPosition() {
		realToyRobot.directTo(aPosition);
		verify(environment).tryMoveObjectTo(realToyRobot, aPosition);
	}
	
	@Test
	public void WhenMoving_IfPlacedAndMovedToNewPosition_ShouldDirectFromMovedToPosition() {
		placeAtAPositionAndFacingADirection();
		realToyRobot.moveTo(movedToPosition);
		realToyRobot.move();
		verify(aDirection).directDirectableFrom(realToyRobot, movedToPosition);
	}
	
	@Test
	public void WhenMoving_IfPlacedAndFacedInDirection_ShouldDirectFromFacedDirection() {
		placeAtAPositionAndFacingADirection();
		realToyRobot.face(facedInDirection);
		realToyRobot.move();
		verify(facedInDirection).directDirectableFrom(realToyRobot, aPosition);
	}
	
	@Test
	public void WhenTurningLeft_IfPlaced_ShouldTurnCounterClockwise() {
		placeAtAPositionAndFacingADirection();
		realToyRobot.turnLeft();
		verify(aDirection).turnCounterClockwise(realToyRobot);
	}
	
	@Test
	public void WhenTurningLeft_IfNotPlaced_ShouldDiscardAction() {
		verifyDiscardsActionWithDirection((robot) -> robot.turnLeft()); 
	}
	
	@Test
	public void WhenTurningRight_IfPlaced_ShouldTurnLeft() {
		placeAtAPositionAndFacingADirection();
		realToyRobot.turnRight();
		verify(aDirection).turnClockwise(realToyRobot);
	}
	
	@Test
	public void WhenTurningRight_IfNotPlaced_ShouldDiscardAction() {
		verifyDiscardsActionWithDirection((robot) -> robot.turnRight());
	}
	
	@Test
	public void WhenReporting_IfPlaced_ShouldReportPositionAndDirection() {
		placeAtAPositionAndFacingADirection();
		realToyRobot.report();
		verify(reportStream).report(aPosition, aDirection);
	}
	
	@Test
	public void WhenReporting_IfNotPlaced_ShouldDiscardAction() {
		verifyDiscardsActionWithReportStream((robot) -> robot.report());
	}
	
	@Test
	public void WhenPlacing_IfPlaced_ShouldDiscardAction() {
		placeAtAPositionAndFacingADirection();
		verifyDiscardsActionWithEnvironment((robot) -> robot.place(aPosition, aDirection));
	}
}