package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MoveCommandTest {

	private MoveCommand moveCommand;
	@Mock
	private MoveAction moveAction;	
	
	@Before
	public void setUp() throws Exception {
		moveCommand = new MoveCommand(moveAction);
	}

	@Test
	public void WhenExecutingCommand_ShouldMove() {
		moveCommand.execute();
		verify(moveAction).move();
	}
}