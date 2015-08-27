package toyrobotsimulator;

public interface Environment extends ReportsPosition {

	void placeObjectAtPosition(final EnvironmentObject environmentObject, final Position position);

	void moveInDirection(final Direction direction);
}