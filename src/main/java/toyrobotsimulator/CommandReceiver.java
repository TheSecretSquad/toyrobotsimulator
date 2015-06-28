package toyrobotsimulator;

public interface CommandReceiver {

	void sendCommandName(final CommandName commandName);

	void sendCommandNameWithParameters(final CommandName commandName, final CommandParameters commandParameters);
}