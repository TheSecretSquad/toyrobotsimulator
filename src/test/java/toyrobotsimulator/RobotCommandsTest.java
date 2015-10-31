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
	private RobotCommand command1;
	private RobotCommand command2;
	@Mock
	private RobotCommandAction robotCommandAction;

	@Before
	public void setUp() throws Exception {
		command1 = new RobotCommand("command1");
		command2 = new RobotCommand("command2");
		robotCommands = new RobotCommands(command1, command2);
	}

	@Test
	public void WhenEachDo_With2Commands_RunsActionExactlyTwice() {
		robotCommands.eachDo(robotCommandAction);
		verify(robotCommandAction, times(2)).actionWith(isA(RobotCommand.class));
	}
	
	@Test
	public void WhenEachDo_RunsAction_InCorrectOrder() {
		robotCommands.eachDo(robotCommandAction);
		 InOrder inOrder = inOrder(robotCommandAction);
		 inOrder.verify(robotCommandAction).actionWith(command1);
		 inOrder.verify(robotCommandAction).actionWith(command2);
	}
}
