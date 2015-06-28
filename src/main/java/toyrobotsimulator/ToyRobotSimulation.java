package toyrobotsimulator;

import java.io.PrintStream;

public class ToyRobotSimulation implements Simulation {

	private final PrintStream printStream;
	private final CommandInterpreter commandInterpreter;
	private final Commands commands;

	public ToyRobotSimulation(final PrintStream printStream,
							  final CommandInterpreter commandInterpreter,
							  final Commands commands) {
		this.printStream = printStream;
		this.commandInterpreter = commandInterpreter;
		this.commands = commands;
	}

	public void run() {
		printStream.println("Running");
		commandInterpreter.interpretCommands(commands);
	}
}