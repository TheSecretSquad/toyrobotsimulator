package toyrobotsimulator;

public class Simulator {

	private final SimulationFactory simulationFactory;
	
	public Simulator(final SimulationFactory simulationFactory) {
		this.simulationFactory = simulationFactory;
	}
	
	public void simulate() {
		Simulation simulation = simulationFactory.createSimulation();
		simulation.run();
	}
}