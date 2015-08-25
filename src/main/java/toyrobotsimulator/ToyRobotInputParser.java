package toyrobotsimulator;

import java.util.Arrays;
import java.util.stream.Stream;

public class ToyRobotInputParser implements InputParser {

	private final InputCommandParser inputCommandParser;
	
	public ToyRobotInputParser(final InputCommandParser inputCommandParser) {
		this.inputCommandParser = inputCommandParser;
	}

	@Override
	public void parseTextTo(final String text, final CommandReceiver commandReceiver) {
		if(text.isEmpty())
			return;
		
		parseAsCommandsTo(text, commandReceiver);
	}
	
	private void parseAsCommandsTo(final String text, final CommandReceiver commandReceiver) {
		parseCommandTokensTo(tokenStreamFrom(text), commandReceiver);
	}
	
	private Stream<String> tokenStreamFrom(final String text) {
		return Arrays.stream(text.split(System.lineSeparator()));
	}
	
	private void parseCommandTokensTo(final Stream<String> tokens, final CommandReceiver commandReceiver) {
		tokens.forEach((String commandText) -> inputCommandParser.parseTextTo(commandText, commandReceiver));
	}
}