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
}
