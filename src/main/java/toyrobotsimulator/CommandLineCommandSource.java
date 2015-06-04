package toyrobotsimulator;

public class CommandLineCommandSource implements CommandSource, CommandLineInputTarget {

	private CommandParser commandParser;
	private CommandLineInput commandLineInput;
	private CommandReceiver commandReceiver;

	public CommandLineCommandSource(final CommandParser commandParser, final CommandLineInput commandLineInput) {
		this.commandParser = commandParser;
		this.commandLineInput = commandLineInput;
	}

	public void parseTo(final CommandReceiver commandReceiver) {
		this.commandReceiver = commandReceiver;
		commandLineInput.beginReceiving(this);
	}

	public void addLine(final String input) {
		commandParser.parseInputTo(input, commandReceiver);
	}
}