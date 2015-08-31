package toyrobotsimulator;

public interface Environment {

	void tryPlaceObjectAtPositionOnSuccess(final EnvironmentObject environmentObject, final Position position, final PlaceSuccessHandler placesuccessHandler);
	
	void tryMoveObjectTo(final EnvironmentObject environmentObject, final Position position);
}