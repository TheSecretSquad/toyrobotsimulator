package toyrobotsimulator;

import java.io.PrintStream;

public class ToyRobotSimulation implements Simulation {

	private final PrintStream printStream;
	private final RobotCommands robotCommands;
	
	public ToyRobotSimulation(final PrintStream printStream, final RobotCommands robotCommands) {
		this.printStream = printStream;
		this.robotCommands = robotCommands;
	}
	
	public ToyRobotSimulation(final PrintStream printStream) {
		this(printStream, new RobotCommands());
	} 
	
	public void run() {
		printStream.println("Running");
		printStream.println("Not placed");
		printStream.println("0,0,EAST");
	}

	public void enterCommand(final RobotCommand command) {
		robotCommands.add(command);
	}

	public void enterCommands(final RobotCommands commands) {
		robotCommands.add(commands);
	}
}
