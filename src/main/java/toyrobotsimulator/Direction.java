package toyrobotsimulator;

public interface Direction {
	
	void turnClockwise(final Turnable turnable);
	
	void turnCounterClockwise(final Turnable turnable);
	
	void directDirectableFrom(final Directable directable, final Coordinate fromCoordinate);
}