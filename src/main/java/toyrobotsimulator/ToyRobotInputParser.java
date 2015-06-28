package toyrobotsimulator;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Default parsing for text input splits commands by new lines.
 * @author Pete
 *
 */
public class ToyRobotInputParser implements InputParser {

	private final InputCommandParser inputCommandParser;
	
	public ToyRobotInputParser(final InputCommandParser inputCommandParser) {
		this.inputCommandParser = inputCommandParser;
	}

	@Override
	public void parseTextTo(final String text, final CommandReceiver commandReceiver) {
		parseAsCommandsTo(tokenStreamFrom(text), commandReceiver);
	}
	
	private Stream<String> tokenStreamFrom(final String text) {
		return Arrays.stream(text.split(System.lineSeparator()));
	}
	
	private void parseAsCommandsTo(final Stream<String> tokens, final CommandReceiver commandReceiver) {
		tokens.forEach((String commandText) -> inputCommandParser.parseCommandTextTo(commandText, commandReceiver));
	}
}