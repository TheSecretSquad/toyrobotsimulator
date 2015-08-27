package toyrobotsimulator;

public interface Coordinate {

	void translateByPositionTo(final Coordinate coordinate, final Directable directable);
	
	void translateFromXYTo(final int x, final int y, final Directable directable);
}