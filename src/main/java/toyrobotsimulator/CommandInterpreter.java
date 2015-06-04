package toyrobotsimulator;

public interface CommandInterpreter {

	void interpretCommandsTo(final Commands commands, final InterpretedCommandReceiver interpretedCommandReceiver);
}