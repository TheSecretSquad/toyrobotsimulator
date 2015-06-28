package toyrobotsimulator;

public interface InputCommandParser {

	void parseCommandTo(final String commandText, final CommandReceiver commandReceiver);
}