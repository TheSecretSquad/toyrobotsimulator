package toyrobotsimulator;

public class CommandLineCommandSource implements CommandSource, CommandLineInputTarget {

	private CommandParser commandParser;
	private CommandLine commandLine;
	private CommandReceiver commandReceiver;

	public CommandLineCommandSource(final CommandParser commandParser, final CommandLine commandLine) {
		this.commandParser = commandParser;
		this.commandLine = commandLine;
	}

	public void parseTo(final CommandReceiver commandReceiver) {
		this.commandReceiver = commandReceiver;
		commandLine.receiveInputTo(this);
	}

	public void addLine(final String input) {
		commandParser.parseInputTo(input, commandReceiver);
	}
}