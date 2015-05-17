package toyrobotsimulator;

public class ToyRobotSimulationFactory implements SimulationFactory {

	public Simulation create() {
		return new ToyRobotSimulation();
	}
}