package toyrobotsimulator;

public class ToyRobotCommandInterpreter implements CommandInterpreter, CommandReceiver {

	// PLACE X,Y,F
	// MOVE
	// LEFT
	// RIGHT
	// REPORT
	
	private InterpretedCommandReceiver interpretedCommandReceiver;
	
	public ToyRobotCommandInterpreter(final InterpretedCommandReceiver interpretedCommandReceiver) {
		this.interpretedCommandReceiver = interpretedCommandReceiver;
	}
	
	public void interpretCommands(final Commands commands) {
		commands.readTo(this);
	}

	@Override
	public void sendCommandName(final CommandName commandName) {
		
	}

	@Override
	public void sendCommandNameWithParameters(final CommandName commandName, final CommandParameters commandParameters) {

	}
}