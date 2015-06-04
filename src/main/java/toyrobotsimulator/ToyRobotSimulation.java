package toyrobotsimulator;

import java.io.PrintStream;

public class ToyRobotSimulation implements Simulation {

	private final PrintStream printStream;
	private final InterpretedCommandReceiver interpretedCommandReceiver;
	private final CommandInterpreter commandInterpreter;
	private final Commands commands;

	public ToyRobotSimulation(final PrintStream printStream, final InterpretedCommandReceiver interpretedCommandReceiver,
			final CommandInterpreter commandInterpreter, final Commands commands) {
		this.printStream = printStream;
		this.interpretedCommandReceiver = interpretedCommandReceiver;
		this.commandInterpreter = commandInterpreter;
		this.commands = commands;
	}

	public void run() {
		printStream.println("Running");
		commandInterpreter.interpretCommandsTo(commands, interpretedCommandReceiver);
	}
}