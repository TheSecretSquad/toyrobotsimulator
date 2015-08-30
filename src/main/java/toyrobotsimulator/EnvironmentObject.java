package toyrobotsimulator;

public interface EnvironmentObject {

	void placeAtPositionFacing(final Position position, final Direction direction);
	
	void moveTo(final Position position);
}