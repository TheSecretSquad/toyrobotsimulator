package toyrobotsimulator;

public interface Direction {
	
	Direction clockwise();
	
	Direction counterClockwise();

	Position moveFrom(final Position position);
}