package toyrobotsimulator;

public class ToyRobotInputCommandParser implements InputCommandParser {

	private final String commandPartSeparator = " ";
	private final String commandParametersSeparator = ",";
	private final int commandNameIndex = 0;
	private final int commandParametersIndex = 1;
		
	@Override
	public void parseCommandTextTo(final String commandText, final CommandReceiver commandReceiver) {
		if(!commandText.isEmpty())
			sendExtractedNameAndParametersTo(commandText, commandReceiver);
	}
	
	private void sendExtractedNameAndParametersTo(final String commandName, final CommandReceiver commandReceiver) {
		sendCommandTokensTo(commandName.split(commandPartSeparator), commandReceiver);
	}
	
	private void sendCommandTokensTo(final String[] commandTokens, final CommandReceiver commandReceiver) {
		sendCommandsWithTokenCountTo(commandTokens, commandTokens.length, commandReceiver);
	}
	
	private void sendCommandsWithTokenCountTo(final String[] commandTokens, final int commandTokensCount, final CommandReceiver commandReceiver) {
		if(commandTokensCount == 1)
			commandReceiver.sendCommandName(createCommandNameFromTokens(commandTokens));
		else if(commandTokensCount > 1)
			commandReceiver.sendCommandNameWithParameters(createCommandNameFromTokens(commandTokens), createCommandParametersFromTokens(commandTokens));
	}
	
	private CommandName createCommandNameFromTokens(final String[] commandTokens) {
		return createCommandNameFromString(commandTokens[commandNameIndex]);
	}
	
	private CommandName createCommandNameFromString(final String commandName) {
		return new CommandName(commandName);
	}

	private CommandParameters createCommandParametersFromTokens(final String[] commandTokens) {
		return createCommandParameters(commandTokens[commandParametersIndex]);
	}

	private CommandParameters createCommandParameters(final String parametersString) {
		String[] parameterTokens = parametersString.split(commandParametersSeparator);
		
		return new OrderedCommandParameters(parameterTokens);
	}
}