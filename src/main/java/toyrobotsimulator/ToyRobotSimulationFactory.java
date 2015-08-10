package toyrobotsimulator;

import java.util.Scanner;

public class ToyRobotSimulationFactory implements SimulationFactory {

	private static ToyRobot toyRobot = new RealToyRobot(new Environment() {

		@Override
		public void placeAt(final Position position) {
			System.out.println("Positioned at: " + position);
			
		}

		@Override
		public void moveIn(final Direction direction) {
			System.out.println("Moving in direction: " + direction);
			
		}
	});
		
	private static CommandInterpreter commandInterpreter = new ToyRobotCommandInterpreter(toyRobot, toyRobot);
	
	private static CommandSource commandSource = new ConsoleCommandSource(new TestConsole(), new ToyRobotInputParser(new ToyRobotInputCommandParser()));
	
	private static Commands commands = new ToyRobotCommands(commandSource);
	
	public Simulation create() {
		return new ToyRobotSimulation(System.out, commandInterpreter, commands);
	}
	
	private static class TestConsole implements Console {

		private Scanner scanner = new Scanner(System.in);
		@Override
		public void parseInputWithParserTo(final InputParser inputParser, final CommandReceiver commandReceiver) {
			System.out.print("Enter a test command: ");
			
			while(scanner.hasNextLine()) {
				inputParser.parseTextTo(scanner.nextLine(), commandReceiver);
				System.out.print("Enter a test command: ");
				
			}
			
			scanner.close();
		}
	}
}