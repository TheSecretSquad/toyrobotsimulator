package toyrobotsimulator;

import java.util.Scanner;

public class TestConsole implements Console {

	private Scanner scanner;
	
	public TestConsole() {
		 this.scanner = new Scanner(System.in);
	}
	
	@Override
	public void parseInputWithParserTo(final InputParser inputParser, final CommandReceiver commandReceiver) {
		prompt();
		loop(inputParser, commandReceiver);
	}

	private void loop(final InputParser inputParser, final CommandReceiver commandReceiver) {
		while(scanner.hasNextLine())
			processNextLine(inputParser, commandReceiver);
		
		scanner.close();
	}

	private void processNextLine(final InputParser inputParser, final CommandReceiver commandReceiver) {
		inputParser.parseTextTo(scanner.nextLine(), commandReceiver);
		prompt();
	}

	private void prompt() {
		System.out.print("Enter command: ");
	}
}