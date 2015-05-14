package toyrobotsimulator;

public class ToyRobotSimulationFactory implements SimulationFactory {

	public Simulation create() {
		return new ToyRobotSimulation() {
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
	}
}