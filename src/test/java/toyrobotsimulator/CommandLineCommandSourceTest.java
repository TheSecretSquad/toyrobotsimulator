package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandLineCommandSourceTest {

	private CommandLineCommandSource commandLineCommandSource;
	@Mock
	private CommandReceiver commandReceiver;
	@Mock
	private CommandParser commandParser;
	@Mock
	private CommandLine commandLine;
	
	@Before
	public void setUp() throws Exception {
		commandLineCommandSource = new CommandLineCommandSource(commandParser, commandLine);
	}

	@Test
	public void WhenParsing_BeginsReceivingFromCommandLineInput() {
		commandLineCommandSource.parseTo(commandReceiver);
		verify(commandLine).beginReceiving(commandLineCommandSource);
	}
	
	@Test
	public void WhenInputLineAdded_ParsesCommand() {
		// Is it bad that we have to call parseTo first or the commandReceiver will be null?
		commandLineCommandSource.parseTo(commandReceiver);
		String inputCommand = "command";
		commandLineCommandSource.addLine(inputCommand);
		verify(commandParser).parseInputTo(inputCommand, commandReceiver);
	}
}