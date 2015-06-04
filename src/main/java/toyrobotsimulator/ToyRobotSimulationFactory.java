package toyrobotsimulator;

public class ToyRobotSimulationFactory implements SimulationFactory {

	private static Commands cs = new Commands() {
		public void readTo(CommandReceiver commandReceiver) {}
	};
	
	private static InterpretedCommandReceiver icr = new InterpretedCommandReceiver() {};
	
	private static CommandInterpreter ci = new CommandInterpreter() {
		public void interpretCommandsTo(Commands commands, InterpretedCommandReceiver interpretedCommandReceiver) {}
	};
	
	public Simulation create() {
		return new ToyRobotSimulation(System.out, icr, ci, cs);
	}
}