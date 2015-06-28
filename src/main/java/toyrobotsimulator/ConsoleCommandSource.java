package toyrobotsimulator;

public class ConsoleCommandSource implements CommandSource {

	private final Console console;
	private final InputParser inputParser;

	public ConsoleCommandSource(final Console console, final InputParser inputParser) {
		this.inputParser = inputParser;
		this.console = console;
	}

	public void parseTo(final CommandReceiver commandReceiver) {
		console.parseInputWithParserTo(inputParser, commandReceiver);
	}
}