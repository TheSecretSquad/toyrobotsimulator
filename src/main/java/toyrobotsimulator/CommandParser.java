package toyrobotsimulator;

public interface CommandParser {

	void parseInputTo(final String input, final CommandReceiver commandReceiver);
}