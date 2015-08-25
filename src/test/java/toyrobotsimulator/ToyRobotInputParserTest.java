package toyrobotsimulator;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
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
	
	private String createSampleCommandsText(String ... commands) {
		return commands.length == 0 ? "" : 
			Arrays.stream(commands).reduce((s, t) -> s + lineSeparator + t).get();
	}
	
	private String createEmptyCommandText() {
		return createSampleCommandsText();
	}
	
	@Before
	public void setUp() throws Exception {
		toyRobotInputParser = new ToyRobotInputParser(inputCommandParser);
	}

	@Test
	public void WhenSentText_ParsesCommandTextInOrderByNewLineDelimiter() {
		String someText = "some command";
		String otherText = "other command";
		String moreText = "another command";
		toyRobotInputParser.parseTextTo(createSampleCommandsText(someText, otherText, moreText), commandReceiver);
		InOrder inOrder = inOrder(inputCommandParser);
		inOrder.verify(inputCommandParser).parseTextTo(someText, commandReceiver);
		inOrder.verify(inputCommandParser).parseTextTo(otherText, commandReceiver);
		inOrder.verify(inputCommandParser).parseTextTo(moreText, commandReceiver);
	}
	
	@Test
	public void WhenSentEmptyText_DoesNothing() {
		toyRobotInputParser.parseTextTo(createEmptyCommandText(), commandReceiver);
		verifyZeroInteractions(inputCommandParser);
	}
}
