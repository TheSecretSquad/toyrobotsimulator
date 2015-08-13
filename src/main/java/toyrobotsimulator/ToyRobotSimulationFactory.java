package toyrobotsimulator;

import java.util.Scanner;

public class ToyRobotSimulationFactory implements SimulationFactory {

	private static ToyRobot toyRobot = new RealToyRobot(new TableTop(0), new OutOfBoundsDetector() {
		@Override
		public void detect(OutOfBoundsHandler outOfBoundsHandler) {
			// TODO Auto-generated method stub
		}
	}, new ReportStream() {
		@Override
		public void report(Position position) {
			// TODO Auto-generated method stub
		}

		@Override
		public void report(Direction direction) {
			// TODO Auto-generated method stub
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