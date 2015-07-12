package toyrobotsimulator;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotInputCommandParserTest {

	private ToyRobotInputCommandParser toyRobotInputCommandParser;
	@Mock
	private CommandReceiver commandReceiver;
	private final String commandNameText = "COMMAND";
	private final String commandParameters = "PARAMETER1,PARAMETER2";
	private final String commandNameTextWithParameters = commandNameText + " " + commandParameters;
	private final String emptyCommandText = "";
	private final String spacesOnlyCommandText = "   ";
	private final CommandName commandName = new CommandName(commandNameText);

	@Before
	public void setUp() throws Exception {
		toyRobotInputCommandParser = new ToyRobotInputCommandParser();
	}
	
	private void verifyNeverSendsCommandNameWithOrWithoutParameters() {
		verify(commandReceiver, never()).sendCommandName(any(CommandName.class));
		verify(commandReceiver, never()).sendCommandNameWithParameters(any(CommandName.class), any(CommandParameters.class));
	}

	@Test
	public void WhenParsingCommand_WithNoParameters_ShouldSendCommandNameToReceiver() {
		toyRobotInputCommandParser.parseCommandTextTo(commandNameText, commandReceiver);
		verify(commandReceiver).sendCommandName(commandName);
	}
	
	@Test
	public void WhenParsingCommand_WithParameters_ShouldSendCommandNameWithParametersToReceiver() {
		toyRobotInputCommandParser.parseCommandTextTo(commandNameTextWithParameters, commandReceiver);
		verify(commandReceiver).sendCommandNameWithParameters(eq(commandName), isA(CommandParameters.class));
	}
	
	@Test
	public void WhenParsingCommand_ShouldIgnoreEmptyCommandText() {
		toyRobotInputCommandParser.parseCommandTextTo(emptyCommandText, commandReceiver);
		verifyNeverSendsCommandNameWithOrWithoutParameters();
	}
	
	@Test
	public void WhenParsingCommand_ShouldIgnoreCommandTextContainingOnlySpaces() {
		toyRobotInputCommandParser.parseCommandTextTo(spacesOnlyCommandText, commandReceiver);
		verifyNeverSendsCommandNameWithOrWithoutParameters();
	}
}
