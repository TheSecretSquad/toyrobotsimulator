package toyrobotsimulator;

public class TableTop implements Environment, OutOfBoundsDecision, Directable {

	private final int dimension;
	private final int dimensionLowBoundaryValue;
	private Position occupiedPosition;
	private EnvironmentObject environmentObject;
	
	public TableTop(int dimension) {
		this.dimension = dimension;
		this.dimensionLowBoundaryValue = 1;
		this.occupiedPosition = null;
		this.environmentObject = null;
	}
	
	@Override
	public void reportTo(final PositionReportStream positionReportStream) {
		positionReportStream.report(occupiedPosition);
	}

	@Override
	public void placeObjectAtPosition(final EnvironmentObject environmentObject, final Position position) {
		tryOccupyPositionUsingActionWithObject(position, () -> occupyPositionWith(position, environmentObject), environmentObject);
	}
	
	@Override
	public void moveInDirection(final Direction direction) {
		direction.directDirectableFrom(this, occupiedPosition);
	}

	@Override
	public void directTo(final Position position) {
		tryOccupyNewPosition(position);
	}
	
	private void tryOccupyNewPosition(final Position position) {
		tryOccupyPositionUsingActionWithObject(position, () -> occupyPosition(position), this.environmentObject);
	}
	
	@Override
	public void avoid() {
		// TODO Auto-generated method stub
		
	}
	
	private void tryOccupyPositionUsingActionWithObject(final Position position, final Runnable occupyPositionAction, final EnvironmentObject environmentObject) {
		verifyPlacedObject(environmentObject);
		
		if(position.isBetween(lowBoundPosition(), upperBoundPosition()))
			occupyPositionAction.run();
		else
			handleBoundary(environmentObject);
	}
	
	private void verifyPlacedObject(final EnvironmentObject environmentObject) {
		if(environmentObject == null)
			throw new NothingPlacedException();
	}
	
	private Position lowBoundPosition() {
		return new Position(dimensionLowBoundaryValue,dimensionLowBoundaryValue);
	}
	
	private Position upperBoundPosition() {
		return new Position(dimension,dimension);
	}

	private void occupyPositionWith(final Position position, final EnvironmentObject environmentObject) {
		occupyPosition(position);
		this.environmentObject = environmentObject;
	}
	
	private void occupyPosition(final Position position) {
		this.occupiedPosition = position;
	}
	
	private void handleBoundary(final EnvironmentObject environmentObject) {
		environmentObject.handleBoundaryWith(this);
	}
}