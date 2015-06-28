package toyrobotsimulator;

public class SimulatorStart {

	public static void main(String[] args) {
		start(simulator());
	}
	
	private static void start(final Simulator simulator) {
		simulator.simulate();
	}
	
	private static Simulator simulator() {
		return new Simulator(simulationFactory());
	}
	
	private static SimulationFactory simulationFactory() {
		return new ToyRobotSimulationFactory();
	}
	
/*
 * The CommandSource parses itself, so if it's a text file,
 * or console, or xml file it will break itself up into
 * command strings. So we only need the InputCommandParser
 * and not the InputParser, because the input parsing is
 * performedby the CommandSource.
 * 
 * Furthermore, we can't have a ToyRobotInputParser, because
 * the input will be formatted differently depending on the CommandSource.
 * 
 */
}