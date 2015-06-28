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
	public void parseTextTo(final String input, final CommandReceiver commandReceiver) {
		parseAsCommandsTo(tokenStreamFrom(input), commandReceiver);
	}
	
	private Stream<String> tokenStreamFrom(final String input) {
		return Arrays.stream(input.split(System.lineSeparator()));
	}
	
	private void parseAsCommandsTo(final Stream<String> tokens, final CommandReceiver commandReceiver) {
		tokens.forEach((String inputCommandString) -> inputCommandParser.parseCommandTextTo(inputCommandString, commandReceiver));
	}
}