package toyrobotsimulator;

public interface Environment {

	void tryPlaceObjectAtPositionFacing(final EnvironmentObject environmentObject, final Position position, final Direction facingDirection);
	
	void tryMoveObjectTo(final EnvironmentObject environmentObject, final Position position);
}