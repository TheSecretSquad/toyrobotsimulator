package toyrobotsimulator;

public class ToyRobotSimulationFactory implements SimulationFactory {

	private ToyRobot toyRobot = new RealToyRobot(new TableTop(5), new ConsoleReportStream(System.out));
		
	private CommandInterpreter commandInterpreter = new ToyRobotCommandInterpreter(toyRobot, toyRobot);
	
	private CommandSource commandSource = new ConsoleCommandSource(new TestConsole(), new ToyRobotInputParser(new ToyRobotInputCommandParser()));
	
	private Commands commands = new ToyRobotCommands(commandSource);
	
	public Simulation create() {
		return new ToyRobotSimulation(System.out, commandInterpreter, commands);
	}
}