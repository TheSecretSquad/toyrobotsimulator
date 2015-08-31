package toyrobotsimulator;

public class TableTop implements Environment {

	private final int dimension;
	private final int dimensionLowBoundaryValue;
	private EnvironmentObject environmentObject;
	
	public TableTop(int dimension) {
		this.dimension = dimension;
		this.dimensionLowBoundaryValue = 1;
		this.environmentObject = null;
	}

	@Override
	public void tryPlaceObjectAtPositionOnSuccess(final EnvironmentObject environmentObject, final Position position, final PlaceSuccessHandler placeSuccessHandler) {
		occupyPositionWithObjectFacing(position, environmentObject, placeSuccessHandler);
	}

	private void occupyPositionWithObjectFacing(final Position position, final EnvironmentObject environmentObject, final PlaceSuccessHandler placeSuccessHandler) {
		position.ifBetweenPoints(lowBoundPosition(), upperBoundPosition(),() -> {
			this.environmentObject = environmentObject;
			placeSuccessHandler.placeSuccessful();
		});
	}
	
	private Position lowBoundPosition() {
		return new Position(dimensionLowBoundaryValue, dimensionLowBoundaryValue);
	}
	
	private Position upperBoundPosition() {
		return new Position(dimension, dimension);
	}

	@Override
	public void tryMoveObjectTo(final EnvironmentObject environmentObject, final Position position) {
		if(environmentObject.equals(this.environmentObject))
			ifPositionInBoundsDo(position, () -> this.environmentObject.moveTo(position));
	}
	
	private void ifPositionInBoundsDo(final Position position, final Runnable action) {
		position.ifBetweenPoints(lowBoundPosition(), upperBoundPosition(), () -> action.run());
	}
}