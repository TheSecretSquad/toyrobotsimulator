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
	public void tryPlaceObjectAtPositionFacing(final EnvironmentObject environmentObject, final Position position, final Direction direction) {
		occupyPositionWithObjectFacing(position, environmentObject, direction);
	}

	private void occupyPositionWithObjectFacing(final Position position, final EnvironmentObject environmentObject, final Direction direction) {
		position.ifBetweenPoints(lowBoundPosition(), upperBoundPosition(),
				() -> environmentObject.placeAtPositionFacing(position, direction));
	}
	
	private Position lowBoundPosition() {
		return new Position(dimensionLowBoundaryValue, dimensionLowBoundaryValue);
	}
	
	private Position upperBoundPosition() {
		return new Position(dimension, dimension);
	}

	@Override
	public void tryMoveObjectTo(final EnvironmentObject environmentObject, final Position position) {
		// TODO Auto-generated method stub
		
	}
}