package toyrobotsimulator;

public interface InputCommandParser {

	void parseCommandTextTo(final String commandText, final CommandReceiver commandReceiver);
}