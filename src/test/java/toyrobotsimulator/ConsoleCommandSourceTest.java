package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleCommandSourceTest {

	private ConsoleCommandSource consoleCommandSource;
	@Mock
	private InputCommandParser inputCommandParser;
	@Mock
	private InputParser inputParser;
	@Mock
	private CommandReceiver commandReceiver;
	@Mock
	private Console console;
	
	@Before
	public void setUp() throws Exception {
		consoleCommandSource = new ConsoleCommandSource(console, inputParser);
	}
	
	@Test
	public void WhenParsing_SendsInputToInputParser() {
		consoleCommandSource.parseTo(commandReceiver);
		verify(console).parseInputWithParserTo(inputParser, commandReceiver);
	}
}