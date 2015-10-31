package toyrobotsimulator;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotCommandsTest {

	private RobotCommands robotCommands;
	@Mock
	private RobotCommand command1;
	@Mock
	private RobotCommand command2;
	@Mock
	private RobotCommandAction robotCommandAction;

	private void createCommandsWith(final RobotCommand... commands) {
		robotCommands = new RobotCommands(commands);
	}
	
	private void createEmptyCommands() {
		robotCommands = new RobotCommands();
	}
	
	@Before
	public void setUp() throws Exception {
	}

	private void verifyCommandsInOrder(final RobotCommand ... commands) {
		robotCommands.eachDo(robotCommandAction);
		InOrder inOrder = inOrder(robotCommandAction);
		for(RobotCommand command : commands)
			inOrder.verify(robotCommandAction).actionWith(command);
	}
	
	@Test
	public void WhenEachDo_With2Commands_RunsActionExactlyTwice() {
		createCommandsWith(command1, command2);
		robotCommands.eachDo(robotCommandAction);
		verify(robotCommandAction, times(2)).actionWith(isA(RobotCommand.class));
	}
	
	@Test
	public void WhenEachDo_RunsAction_InCorrectOrder() {
		createCommandsWith(command1, command2);
		verifyCommandsInOrder(command1, command2);
	}
	
	@Test
	public void WhenAdd_CommandsAreAddedInOrder() {
		createEmptyCommands();
		robotCommands.add(command1);
		robotCommands.add(command2);
		verifyCommandsInOrder(command1, command2);
	}
	
	@Test
	public void WhenAddCommands_CommandsAreAddedInOrder() {
		createEmptyCommands();
		robotCommands.add(new RobotCommands(command1, command2));
		verifyCommandsInOrder(command1, command2);
	}
}
