package toyrobotsimulator;

public interface Environment extends Reportable, Edge {

	void placeAtPositionWith(final Position position, final EdgeDetector edgeDetector);

	void moveInDirectionWith(final Direction direction, final EdgeDetector edgeDetector);
}