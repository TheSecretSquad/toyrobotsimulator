package toyrobotsimulator;

import java.io.PrintStream;

public interface Direction {
	
	void turnClockwise(final Turnable turnable);
	
	void turnCounterClockwise(final Turnable turnable);
	
	void directDirectableFrom(final Directable directable, final Coordinate fromCoordinate);

	void printOn(final PrintStream printStream);
}