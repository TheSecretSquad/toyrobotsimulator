package toyrobotsimulator;

public interface Direction {
	
	Direction clockwise();
	
	Direction counterClockwise();
	
	void directDirectableFrom(final Directable directable, final Coordinate fromCoordinate);
}