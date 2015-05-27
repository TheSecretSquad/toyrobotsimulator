package toyrobotsimulator;

public class ToyRobotSimulationFactory implements SimulationFactory {

	private static Commands cs = new Commands() {};
	private static Board b = new Board() {};
	private static CommandInterpreter ci = new CommandInterpreter() {
		public void interpretCommandsTo(Commands commands, Board board) {}
	};
	
	public Simulation create() {
		return new ToyRobotSimulation(System.out, b, ci, cs);
	}
}