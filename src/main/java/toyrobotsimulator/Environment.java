package toyrobotsimulator;

public interface Environment {

	void placeAt(final Position position);

	void moveIn(final Direction direction);
}