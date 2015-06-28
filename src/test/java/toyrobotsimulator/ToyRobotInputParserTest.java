package toyrobotsimulator;

import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotInputParserTest {

	private ToyRobotInputParser toyRobotInputParser;
	@Mock
	private InputCommandParser inputCommandParser;
	@Mock
	private CommandReceiver commandReceiver;
	private final String lineSeparator = System.lineSeparator();
	
	private String createSampleCommandsInput(String ... commands) {
		if(commands.length == 0)
			return "";
		return Arrays.stream(commands).reduce((s, t) -> s + lineSeparator + t).get();
	}
	
	@Before
	public void setUp() throws Exception {
		toyRobotInputParser = new ToyRobotInputParser(inputCommandParser);
	}

	@Test
	public void WhenSentInput_ParsesCommandsByNewLineDelimiter() {
		String someCommand = "some command";
		String otherCommand = "other command";
		String anotherCommand = "another command";
		toyRobotInputParser.parseTextTo(createSampleCommandsInput(someCommand, otherCommand, anotherCommand), commandReceiver);
		verify(inputCommandParser).parseCommandTo(someCommand, commandReceiver);
		verify(inputCommandParser).parseCommandTo(otherCommand, commandReceiver);
		verify(inputCommandParser).parseCommandTo(anotherCommand, commandReceiver);
	}
}
