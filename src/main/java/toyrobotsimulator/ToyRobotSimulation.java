package toyrobotsimulator;

import java.io.PrintStream;

public class ToyRobotSimulation implements Simulation {

	public final PrintStream printStream;
	
	public ToyRobotSimulation(final PrintStream printStream) {
		this.printStream = printStream;
	}
	
	public void run() {
		printStream.println("Running");
	}
}