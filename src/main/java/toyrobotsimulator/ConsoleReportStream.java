package toyrobotsimulator;

import java.io.PrintStream;

public class ConsoleReportStream implements ReportStream {
	
	private final PrintStream printStream;
	
	public ConsoleReportStream(final PrintStream printStream) {
		this.printStream = printStream;
	}
	
	@Override
	public void report(final Position position, final Direction direction) {
		position.printOn(printStream);
		printStream.print(",");
		direction.printOn(printStream);
		printStream.println();
	}
}