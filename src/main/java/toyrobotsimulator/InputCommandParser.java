package toyrobotsimulator;

public interface InputCommandParser {

	void parseTextTo(final String commandText, final CommandReceiver commandReceiver);
}