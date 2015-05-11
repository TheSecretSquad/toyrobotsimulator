package toyrobotsimulator;

public class Simulator {

	private final SimulationFactory simulationFactory;
	
	public Simulator(final SimulationFactory simulationFactory) {
		this.simulationFactory = simulationFactory;
	}
	
	public void simulate() {
		run(simulation());
	}
	
	private void run(Simulation simulation) {
		simulation.run();
	}
	
	private Simulation simulation() {
		return simulationFactory.create();
	}
}