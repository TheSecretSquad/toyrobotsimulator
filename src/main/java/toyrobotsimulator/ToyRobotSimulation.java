package toyrobotsimulator;

import java.io.PrintStream;

public class ToyRobotSimulation implements Simulation {

	private final PrintStream printStream;
	private final Board board;
	private final CommandInterpreter commandInterpreter;
	private final Commands commands;

	public ToyRobotSimulation(final PrintStream printStream, final Board board, 
			final CommandInterpreter commandInterpreter, final Commands commands) {
		this.printStream = printStream;
		this.board = board;
		this.commandInterpreter = commandInterpreter;
		this.commands = commands;
	}
	
	public void run() {
		printStream.println("Running");
		commandInterpreter.interpretCommandsTo(commands, board);
	}
}