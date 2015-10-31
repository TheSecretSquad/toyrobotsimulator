package toyrobotsimulator;

import java.io.PrintStream;

public class ToyRobotSimulation implements Simulation {

	private final PrintStream printStream;
	
	public ToyRobotSimulation(final PrintStream printStream) {
		this.printStream = printStream;
	}
	
	public void run() {
		printStream.println("Running");
		printStream.println("Not placed");
		printStream.println("0,0,EAST");
	}

	public void enterCommand(final RobotCommand command) {
		
	}

	public void enterCommands(final RobotCommands commands) {
		commands.eachDo((command) -> enterCommand(command));
	}
}
