package toyrobotsimulator;

public class TableTop implements Environment {

	private final int dimension;
	private final int dimensionLowBoundaryValue;
	private Position occupiedPosition;
	
	public TableTop(int dimension) {
		this.dimension = dimension;
		this.dimensionLowBoundaryValue = 1;
	}
	
	@Override
	public void reportTo(final PositionReportStream positionReportStream) {
		positionReportStream.report(occupiedPosition);
	}

	@Override
	public void placeAtPositionWith(final Position position, final OutOfBoundsDetector outOfBoundsDetector) {
		if(isInBounds(position))
			occupy(position);
		else
			detectOutOfBounds(outOfBoundsDetector);
	}
	
	@Override
	public void moveInDirectionWith(final Direction direction, final OutOfBoundsDetector outOfBoundsDetector) {
		Position newPosition = occupiedPosition.translateIn(direction);
		
	}

	@Override
	public void avoid() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isInBounds(final Position position) {
		return position.isBetween(lowBoundPosition(), upperBoundPosition());
	}
	
	private Position lowBoundPosition() {
		return new Position(dimensionLowBoundaryValue,dimensionLowBoundaryValue);
	}
	
	private Position upperBoundPosition() {
		return new Position(dimension,dimension);
	}

	private void occupy(final Position position) {
		this.occupiedPosition = position;
	}
	
	private void detectOutOfBounds(final OutOfBoundsDetector outOfBoundsDetector) {
		outOfBoundsDetector.detect(this);
	}
}