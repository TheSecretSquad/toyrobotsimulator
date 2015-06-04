package toyrobotsimulator;

public class ToyRobotCommandInterpreter implements CommandInterpreter, CommandReceiver {

	public void interpretCommandsTo(final Commands commands, final InterpretedCommandReceiver interpretedCommandReceiver) {
		commands.readTo(this);
	}
}