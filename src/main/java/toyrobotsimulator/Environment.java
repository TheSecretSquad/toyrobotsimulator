package toyrobotsimulator;

public interface Environment extends ReportsPosition, OutOfBoundsHandler {

	void placeAtPositionWith(final Position position, final OutOfBoundsDetector outOfBoundsDetector);

	void moveInDirectionWith(final Direction direction, final OutOfBoundsDetector outOfBoundsDetector);
}